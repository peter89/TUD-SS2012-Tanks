package eea.engine.action.basicactions;

import eea.engine.action.Action;
import eea.engine.interfaces.IMovement;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Eine Bewegung erfolgt immer mit einer angegebenen Geschwindigkeit.
 */
public abstract class Movement implements Action , IMovement{
	
	protected float speed;
	
	/**
	 * @param speed Geschwindigkeit der auszufuehrenden Bewegung
	 */
	public Movement(float speed){
		this.speed = speed;
	}


}
