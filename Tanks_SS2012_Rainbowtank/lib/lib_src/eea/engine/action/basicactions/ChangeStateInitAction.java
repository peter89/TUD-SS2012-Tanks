package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

public class ChangeStateInitAction implements Action {
	
	private final int state;
	
	public ChangeStateInitAction(int state){
		this.state = state;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		sb.enterState(state);
		StateBasedEntityManager.getInstance().clearEntitiesFromState(state);
		try {
			gc.getInput().clearKeyPressedRecord();
			gc.getInput().clearControlPressedRecord();
			gc.getInput().clearMousePressedRecord();
			gc.reinit();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		if(gc.isPaused()) gc.resume();

	}

}
