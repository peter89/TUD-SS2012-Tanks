package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

public class ChangeStateAction implements Action {
	
	private final int state;
	
	public ChangeStateAction(int state){
		this.state = state;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		sb.enterState(state);
		if(gc.isPaused()) gc.resume();

	}

}
