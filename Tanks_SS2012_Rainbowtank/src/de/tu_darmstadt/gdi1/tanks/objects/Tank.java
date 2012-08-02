package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;
import eea.engine.component.Component;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
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
		
		setScale(getScale()+0.05f); //Gr��e	
		
		//set Default Variables
		health = 100;
		schaden = 30;
		
		Minen = 3;
		Munition = 10;
		Full = 500;
		
		speed=0.05f;
		
		//Tank (Erweiterung)
    	ce.addAction(ceas); // f�gt dem panzer schaden des getriebes bei treffer hinzu
    	ce.addAction(ceat); //stoppt panzer aus wenn er objekt ber�hrt 
    	//Erweiterung Physics -> ausbremsen und anderes objekt verschieben
    	
    	}

	public void setShootKey(int shootkey) {
		KeyPressedEvent shoot = new KeyPressedEvent(shootkey);
		System.out.println("Schuss von Tank");
		shoot.addAction(shootaction);
		addComponent(shoot);
	}
	
	//set navigation Bewegung vom Panzer
	public void setNavKey(int left,int right,int forward,int backward){

		KeyDownEvent turn_left = new KeyDownEvent(left);
		KeyDownEvent turn_right = new KeyDownEvent(right);
		KeyDownEvent move_forward = new KeyDownEvent(forward);
		KeyDownEvent move_backward = new KeyDownEvent(backward);
		
		//Bewegung des Panzers
		turn_left.addAction(new RotateLeftAction(speed));	
		turn_right.addAction(new RotateRightAction(speed));
		move_forward.addAction(new MoveForwardAction(speed));
		move_backward.addAction(new MoveBackwardAction(speed));
				
		this.addComponent(turn_left);
		this.addComponent(turn_right);
		this.addComponent(move_forward);
		this.addComponent(move_backward);
		
	}

	
	//CollisionEvent Action
	Action ceas = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {

		if(Global.isdebug()) System.err.println("Getriebeschaden?");
		//Wenn speed > 0.02 f�ge in 20% der Collisionen einen Schaden hinzu
		if(speed>=0.02f){
			if (Global.likelihood(100)){
				float getrschaden = (float) Global.rand(2, 7) / 1000;
				speed-=getrschaden;
				System.out.println("Getriebe hat "+schaden+" Schaden genommen\n" +
				"Repariere es bevor du zum leichten Ziel wirst"); //TODO: Meldung Textfeld Bottom
			}
		}
		
	}};
	
	//CollisionEvent Action
	Action ceat = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			
		speed = 0;

	}};
    
}