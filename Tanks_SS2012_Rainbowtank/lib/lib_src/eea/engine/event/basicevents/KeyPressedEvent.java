package eea.engine.event.basicevents;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * Eine beliebige Tastenmenge wurde losgelassen
 */
public class KeyPressedEvent extends Event {


	private final Integer[] keyList;
	
	public KeyPressedEvent(Integer... key) {
		super("KeyPressedEvent");
		keyList = key;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		for(Integer k : keyList){
			if(!gc.getInput().isKeyPressed(k)) return false;
		}
		
		return true;
	}

}
