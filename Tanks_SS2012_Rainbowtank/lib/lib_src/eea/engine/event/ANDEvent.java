package eea.engine.event;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;

/**
 * Mehrere Events lassen sich zu einem MultiEvent zusammenfuegen.
 * Treffen alle Events ein (Konjunktion aller Event's), so werden
 * die registrierten Aktionen ausgefuehrt.
 */
public class ANDEvent extends Event {
	
	//(Konkatenation oder Verundung)
	
	private final Event[] events;
	
	/**
	 * Mehrere Events lassen sich zu einem MultiEvent zusammenfuegen.
	 * Treffen alle Events ein (Konjunktion aller Event's), so werden
	 * die registrierten Aktionen ausgefuehrt.
	 * In diesen Konstruktor koennen beliebig viele Event's eingefuegt werden.
	 */
	public ANDEvent(Event... events){
		super("MultiEvent");
		this.events = events;
	}
	
	public void setOwnerEntity(Entity owner){
		super.setOwnerEntity(owner);
		for(Event event : events){
			event.setOwnerEntity(owner);
		}
		
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		for(Event event : events){
			if(!event.performAction(gc, sb, delta)) return false;
		}
		return true;
	}

}
