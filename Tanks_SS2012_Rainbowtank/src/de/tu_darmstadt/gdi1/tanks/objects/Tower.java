package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LoopEvent;

public class Tower extends TanksObjects{

	int Munition;
	int Heat;   //Hitze wird durch schieﬂen verursacht
	public Shot mybullet;
	
	public Tower(String id) {
		super(id);
		
		setPicture("flac.png"); //png bild
		setScale(0.15f); //Grˆﬂe
		//TODO: Tower der sich richtung maus dreht und bei klick schieﬂt
		//TODO automated shooting if enemy is in range
		//setAutoShoot();
		Munition = 100;
	}
	
	public void setAutoShoot() {
    	//Auto Schieﬂen Tower...
		LoopEvent loop_shoot = new LoopEvent();
		loop_shoot.addAction(shoota);
		addComponent(loop_shoot);
	}
	
	public Action shoota = new Action() {
   		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
   			
			mybullet = new Shot("a Bullet");
			
			//abstand generierung vor kanonenrohr abh‰ngig von tankgrˆﬂe
			Vector2f offset = new Vector2f(0,(int) (getScale()*-250));
			Vector2f bullet_position = offset.add(getRotation());
			bullet_position.add(getPosition());
			mybullet.setPosition( bullet_position ); //Position
			mybullet.setRotation(getRotation() ); //Rotation
			mybullet.setVisible(true);
			
			try{
			// Temp-Entitaet an StateBasedEntityManager uebergeben
	    	StateBasedEntityManager.getInstance().addEntity(Tanks.GAMEPLAYSTATE, mybullet);
			}
			catch(Exception e){
				System.out.println("Warte auf Objekt tankPlayer");
			}
		}};	
}
