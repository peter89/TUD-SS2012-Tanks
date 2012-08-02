package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * Dauerhafte Ausfuehrung der Actions dieses Events bei jedem Frame
 */
public class LoopEvent extends Event {

	public LoopEvent() {
		super("LoopEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		return true;
	}

}
