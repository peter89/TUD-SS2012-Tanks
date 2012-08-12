package eea.engine.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;


/**
 * Actions (Aktionen) können zu einem Event (Ereignis) hinzugefuegt werden. 
 * Tritt das Event ein, so werden alle auf diesem Event registrierten Actions
 * ausgefuehrt.
 */
public interface Action {

	/**
	 * Eine Action muss die update-Methode enthalten.
	 * Diese aktualisiert die Entity's (Entitaeten), gegeben durch das Event (Event.getOwner()).
	 *  
	 * @see GameContainer
	 * @param gc 
	 * @param sb 
	 * @param delta Vergangene Zeit in ns
	 * @param event Action ist registriert auf diesem Event
	 */
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta, Component event);

}
