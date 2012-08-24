package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MovementDoesntCollideEvent;
import global.Debug;
import global.Global;




/**
// Panzer wird erzeugt Example
TanksObjects tank = new Entity("a tank");
tank.setPicture("tank.png"); //Bild: wird durch Aufruf bestimmt
**/
public class Tank extends Tower {
	
	int Minen;
	int Full; 	//Benzin wird duch fahren verbraucht
	
	public Tank(String id) {
		super(id);
		setScale(getScale()+0.05f); //Größe	
		
		//set Default Variables
		health = 100;
		strength = 30;
		
		Minen = 3;
		Munition = 10;
		Full = 500;
		
		speed=0.05f; // speed of the object - used to set/remove the speed of an
		
		//Tank (Erweiterung)
    	ce.addAction(ceas); // fügt dem panzer schaden des getriebes bei treffer hinzu
    	ce.addAction(ceat); //stoppt panzer aus wenn er objekt berührt 
    	//Erweiterung Physics -> ausbremsen und anderes objekt verschieben
    	
    	}

	public void setShootKey(int shootkey) {
		KeyPressedEvent shoot = new KeyPressedEvent(shootkey);
		shoot.addAction(shoota);
		addComponent(shoot);
	}
	
	//set navigation Bewegung vom Panzer
	public void setNavKey(int left,int right,int forward,int backward){
		
		//Key Down Events für Bewegung des Panzers
		KeyDownEvent kde_move_forward = new KeyDownEvent(forward);	
		KeyDownEvent kde_move_backward = new KeyDownEvent(backward);
		KeyDownEvent kde_turn_left = new KeyDownEvent(left);
		KeyDownEvent kde_turn_right = new KeyDownEvent(right);

		MovementDoesntCollideEvent mdce_mf = new MovementDoesntCollideEvent( speed, new MoveForwardAction(speed) );
		MovementDoesntCollideEvent mdce_mb = new MovementDoesntCollideEvent( speed, new MoveBackwardAction(speed) );
		MovementDoesntCollideEvent mdce_rl = new MovementDoesntCollideEvent( speed, new RotateLeftAction(speed) );
		MovementDoesntCollideEvent mdce_rr = new MovementDoesntCollideEvent( speed, new RotateRightAction(speed) );
		
		ANDEvent move_forward = new ANDEvent(mdce_mf, kde_move_forward);
		ANDEvent move_backward = new ANDEvent(mdce_mb, kde_move_backward);
		ANDEvent turn_left = new ANDEvent(mdce_rl, kde_turn_left);
		ANDEvent turn_right = new ANDEvent(mdce_rr, kde_turn_right);

		move_forward.addAction(new MoveForwardAction(speed));
		move_backward.addAction(new MoveBackwardAction(speed));
		turn_left.addAction(new RotateLeftAction(speed));
		turn_right.addAction(new RotateRightAction(speed));
		
		addComponent(move_forward);
		addComponent(move_backward);
		addComponent(turn_left);
		addComponent(turn_right);
		
		//Componente < Event < Action

	}
	
	//CollisionEvent Action
	Action ceat = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			
		speed = 0;

	}};
	
	
	//CollisionEvent Action Schaden
	Action ceas = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
	
			
		if (Debug.isdebug(this)) System.err.println("Getriebeschaden?");
		//Wenn speed > 0.02 füge in 20% der Collisionen einen Schaden hinzu
		if(speed>=0.02f){
			if (Global.likelihood(100)){
				float getrschaden = (float) Global.rand(2, 7) / 1000;
				speed-=getrschaden;
				System.out.println("Getriebe hat "+strength+" Schaden genommen\n" +
				"Repariere es bevor du zum leichten Ziel wirst"); //TODO: Meldung Textfeld Bottom
			}
		}
		
	}};
    
}
