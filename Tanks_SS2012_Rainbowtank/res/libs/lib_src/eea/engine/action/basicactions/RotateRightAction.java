package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Die Entitaet wird nach rechts gedreht.
 */
public class RotateRightAction extends Movement{

	public RotateRightAction(float speed) {
		super(speed);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		
		
		float rotation = event.getOwnerEntity().getRotation();
		rotation += speed * delta;
		rotation = (rotation +360) % 360;
		
		event.getOwnerEntity().setRotation(rotation);
		
	}

	@Override
	public Vector2f getNextPosition(Vector2f position, float speed,
			float rotation, int delta) {
		return position;
	}

}
