package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;
import eea.engine.interfaces.IMovement;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Die Entitaet wird nach oben bewegt.
 */
public class MoveForwardAction extends Movement implements IMovement{
	
	/**
	 * @param speed Geschwindigkeit der Vorwaertsbewegung
	 */
	public MoveForwardAction(float speed){
		super(speed);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		
		
		Vector2f position = getNextPosition(event.getOwnerEntity().getPosition(), speed, event.getOwnerEntity().getRotation(), delta);//event.getOwnerEntity().getPosition();
		event.getOwnerEntity().setPosition(position);
	}

	@Override
	public Vector2f getNextPosition(Vector2f position, float speed,
			float rotation, int delta) {
		float hip = speed * delta;
		Vector2f pos = new Vector2f(position);
        pos.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
        pos.y -= hip *java.lang.Math.cos(java.lang.Math.toRadians(rotation));

		return pos;
	}

}
