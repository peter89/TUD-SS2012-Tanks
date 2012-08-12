package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class MouseClickedEvent extends Event {

	public MouseClickedEvent() {
		super("MouseClickedEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		return gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
	}

}
