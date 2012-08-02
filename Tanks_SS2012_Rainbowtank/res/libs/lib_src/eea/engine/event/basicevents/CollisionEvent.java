package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * Pruefung, ob die Besitzer-Entity mit einer anderen Entity zusammengestossen ist
 */
public class CollisionEvent extends Event {
	
	private Entity colidedEntity;

	public CollisionEvent() {
		super("CollisionEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Entity entity = StateBasedEntityManager.getInstance().colides(sb.getCurrentStateID(), owner);
		if(entity != null){
			this.colidedEntity = entity;
			return true;
		}
		return false;
	}

	public Entity getColidedEntity() {
		return colidedEntity;
	}

}
