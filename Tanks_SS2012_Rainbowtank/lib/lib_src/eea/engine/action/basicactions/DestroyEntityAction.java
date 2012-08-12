package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Die DestroyEntityAction entfernt die Entity aus der Liste der Entitaeten im Entity-Manager.
 */
public class DestroyEntityAction implements Action {

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), event.getOwnerEntity());

	}

}
