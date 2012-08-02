package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * Actions können zu einem Event hinzugefügt werden. Tritt das Event ein, so werden alle 
 * auf diesem Event registrierten Actions ausgefuehrt.
 * 
 * Die Entitaet wird an eine spezifizierte Position gesetzt.
 */
public class SetEntityPositionAction implements Action{
	
	private Vector2f pos;
	
	public SetEntityPositionAction(Vector2f pos){
		this.pos = pos;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		event.getOwnerEntity().setPosition(new Vector2f(pos));
		
	}

}
