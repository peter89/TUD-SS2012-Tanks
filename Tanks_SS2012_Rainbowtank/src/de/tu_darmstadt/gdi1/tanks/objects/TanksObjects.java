package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import global.Debug;
import global.Global;
import global.Settings;

public class TanksObjects extends Entity {
	
	/**
	 * Standards von Objekten - Objekte können im god-modus nicht zerstört
	 * werden
	 * */
	public int health = 1; 	// Standard Leben (e.g. bullet, mine, packs)
	int munition = 0;	//standard munition
	float speed = 0;    //speed
	
	// object
	int strength = Global.rand(0, 3); // verursacht schaden bei aufprall

	/**
	 * Events
	 * */
	// trifft objekt -> Collision Event
	CollisionEvent ce = new CollisionEvent();
	// verlasse Bildschirm -> LeavingScreenEvent
	LeavingScreenEvent lse = new LeavingScreenEvent();
	// Zerstöre Objekt -> DestroyEntityAction
	DestroyEntityAction da = new DestroyEntityAction();

	public TanksObjects(String id) {
		super(id);
	    
		// Collision Event
		ce.addAction(ca); // ADD Action cea to CollisionEventAction
		addComponent(ce); // ADD CollisionEvent to Object

		// Leving Sceen Event
		lse.addAction(lsa); // ADD Action lsa to LeavingScreenEvent
		addComponent(lse); // ADD LeavingScreenEvent to Object

		lse.addAction(new DestroyEntityAction());
	}

	// funktion ermögicht schaden und reperatur durch [healtpack]
	public void health(int health) {
		this.health -= health;
	}

	// LeavingScreenEvent Action
	Action lsa = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			Entity obj = ce.getOwnerEntity();

			if (lse.getId() == "a Bullet") {
				System.err.println(obj.getId() + " Verlässt Aufenthalsbereich");
				lse.addAction(da); // ... zerstoere Objekt
				System.err.println(obj.getId() + " wird Zerstört");
			}
		}
	};

	// CollisionEvent Action
	Action ca = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {

			// Owner of Event
			String EventOwner = ce.getOwnerEntity().getId();

			// Wenn bereits Zerstört
			if (isdestroid()) {
				// <Owner> ist bereits zerstört worden
				if (Debug.isdebug(this))
					System.out.println(EventOwner+ " ist bereits zerstört worden");
			} else {

				//Prüft ob unzerstörbarkeit ist an
				if (! Settings.godmod ) {
					try {
						// collisionsobject -> Tanksobject
						TanksObjects tanksobj = (TanksObjects) ce.getColidedEntity();// Typenconvertierung						
						health(-tanksobj.strength); // schaden hinzufügen
					} catch (Exception e) {
					}// TODO: Exception: Fehler bei Convertierung nach
						// TanksObjects
				}

				// Prüfe ob Object vom Typ Tank
				// if( ce.getColidedEntity().getClass().getSimpleName() ==
				// "Tank") { }

				if (isdestroid()) {
					if (Debug.isdebug(this))
						System.out.println(ce.getOwnerEntity() + " wurde zerstört");
					speed = 0;
					strength = 0;
					// TODO: Drop Items e.g. Munition Health
				}

			}

			if (ce.getColidedEntity().getId() != "a Bullet")
				if (Debug.isdebug(this))
					System.err.println(ce.getColidedEntity().getId() + ">> <<" + ce.getId());
		}
	};

	public void setPicture(String picture) {

			// Falls object zerstört ändere bild zu...
			if (isdestroid()) picture = "destroid_" + picture;
			
			// Bild Zuweisen
			addComponent(new ImageRenderComponent(Global.getImage(picture)));
	}

	// gibt zurück ob object zerstört wurde
	public boolean isdestroid() {
		return (health <= 0) ? true : false;
	}

}