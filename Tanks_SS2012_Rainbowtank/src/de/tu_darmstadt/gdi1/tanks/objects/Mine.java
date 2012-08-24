package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import global.Debug;
import global.Global;

import java.util.Random;


@SuppressWarnings("unused")
public class Mine extends Pickup {
	
	
	/** Events: CollisionEvent, LeavingScreenEvent
	 * 	   CollisionEvent: Kugel wird Zerstört, Schaden(i)-> Collisions Objekt
	 * LeavingScreenEvent: Kugel wird zerstört
	 */

	public Mine(String id) {
		super(id);
		
		setPicture("mine.png");
		health(-30);
		strength = Global.rand(20,40); 	//zufallsschaden von 20 bis 40
		
		if(Debug.isdebug(this))
			System.err.println("Mine Gelegt");
		
    	//TODO: Possitioniere hinter Panzer
    	
    	try{
    	//setPosition(ce.getOwnerEntity().getPosition());
    	//setRotation(ce.getOwnerEntity().getRotation());
    	//setScale(ce.getOwnerEntity().getScale());
    	}
    	catch(NullPointerException e){
    		if(Debug.isdebug(this))
    			System.out.println(this.getId()+" Owner hat keine Ausrichtung");
    	}
    	
    	//Entity test = cte.getColidedEntity();
	}
	
	//@Override
	Action cea = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			
			new DestroyEntityAction();
			if(Debug.isdebug(this))
				System.out.println(this.getClass().getName()+" Kollidiert mit Objekt und wird Zerstört");
		}};
}