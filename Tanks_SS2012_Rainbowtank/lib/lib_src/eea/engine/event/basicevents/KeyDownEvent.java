package eea.engine.event.basicevents;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * Eine beliebige Tastemenge ist gerade nach unten gedrueckt
 */
public class KeyDownEvent extends Event {
	
	private final Integer[] keyList;
	
	public KeyDownEvent(Integer... key) {
		super("KeyDownEvent");
		keyList = key;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		
		for(Integer k : keyList){
			if(!gc.getInput().isKeyDown(k)) return false;
		}
		
		return true;
	}

}
