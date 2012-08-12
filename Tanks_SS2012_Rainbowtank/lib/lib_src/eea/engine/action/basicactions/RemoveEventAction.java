package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

public class RemoveEventAction implements Action {

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		event.getOwnerEntity().removeComponent(event);

	}

}
