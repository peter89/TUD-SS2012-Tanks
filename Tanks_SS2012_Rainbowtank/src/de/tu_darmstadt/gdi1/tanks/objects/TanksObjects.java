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
	 * Standards von Objekten - Objekte k�nnen im god-modus nicht zerst�rt
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
	// Zerst�re Objekt -> DestroyEntityAction
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

	// funktion erm�gicht schaden und reperatur durch [healtpack]
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
				System.err.println(obj.getId() + " Verl�sst Aufenthalsbereich");
				lse.addAction(da); // ... zerstoere Objekt
				System.err.println(obj.getId() + " wird Zerst�rt");
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

			// Wenn bereits Zerst�rt
			if (isdestroid()) {
				// <Owner> ist bereits zerst�rt worden
				if (Debug.isdebug(this))
					System.out.println(EventOwner+ " ist bereits zerst�rt worden");
			} else {

				//Pr�ft ob unzerst�rbarkeit ist an
				if (! Settings.godmod ) {
					try {
						// collisionsobject -> Tanksobject
						TanksObjects tanksobj = (TanksObjects) ce.getColidedEntity();// Typenconvertierung						
						health(-tanksobj.strength); // schaden hinzuf�gen
					} catch (Exception e) {
					}// TODO: Exception: Fehler bei Convertierung nach
						// TanksObjects
				}

				// Pr�fe ob Object vom Typ Tank
				// if( ce.getColidedEntity().getClass().getSimpleName() ==
				// "Tank") { }

				if (isdestroid()) {
					if (Debug.isdebug(this))
						System.out.println(ce.getOwnerEntity() + " wurde zerst�rt");
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

			// Falls object zerst�rt �ndere bild zu...
			if (isdestroid()) picture = "destroid_" + picture;
			
			// Bild Zuweisen
			addComponent(new ImageRenderComponent(Global.getImage(picture)));
	}

	// gibt zur�ck ob object zerst�rt wurde
	public boolean isdestroid() {
		return (health <= 0) ? true : false;
	}

}