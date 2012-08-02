package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * Ist die Entity ausserhalb des Bildschirms
 */
public class LeavingScreenEvent extends Event {

	public LeavingScreenEvent() {
		super("LeavingScreenEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Vector2f pos = owner.getPosition();
		//System.out.println("( " +pos.x +" | " +pos.y +" )");
		if(pos.x < 0 || pos.y < 0){
			return true;
		}
		if(pos.x > gc.getWidth() || pos.y > gc.getHeight()) {
			return true;
		}
		return false;
	}

}
