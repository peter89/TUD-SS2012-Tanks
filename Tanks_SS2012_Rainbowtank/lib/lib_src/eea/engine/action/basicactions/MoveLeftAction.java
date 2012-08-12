package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Die Entitaet wird nach links verschoben.
 */
public class MoveLeftAction extends Movement implements Action{

	/**
	 * @param speed Geschwindigkeit der Linksbewegung
	 */
	public MoveLeftAction(float speed){
		super(speed);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		/*
		Vector2f position = event.getOwnerEntity().getPosition();
		float hip = 0.4f * delta;
		position.x -= hip;
		*/
		event.getOwnerEntity().setPosition(getNextPosition(event.getOwnerEntity().getPosition(), speed, event.getOwnerEntity().getRotation(), delta));
		
	}

	@Override
	public Vector2f getNextPosition(Vector2f position, float speed,
			float rotation, int delta) {
		Vector2f pos = new Vector2f(position);
		pos.x -= speed * delta;
		return pos;
	}

}
