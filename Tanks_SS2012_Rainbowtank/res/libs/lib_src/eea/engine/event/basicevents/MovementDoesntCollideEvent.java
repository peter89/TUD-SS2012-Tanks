package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.interfaces.IMovement;

public class MovementDoesntCollideEvent extends Event {
	
	private float speed;
	private IMovement move;

	public MovementDoesntCollideEvent(float speed, IMovement move) {
		super("MoveDoesntCollideEvent");
		this.speed = speed;
		this.move = move;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		
		/*
		float rotation = this.getOwnerEntity().getRotation();
		Vector2f position = new Vector2f(this.getOwnerEntity().getPosition());
 
        float hip = speed * delta;
        
        position.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
        position.y -= hip *java.lang.Math.cos(java.lang.Math.toRadians(rotation));
		 */
		Vector2f position = move.getNextPosition(owner.getPosition(), speed, owner.getRotation(), delta);
		
        Entity entity = new Entity(this.getOwnerEntity().getId());
        entity.setPosition(position);
        entity.setSize(new Vector2f(this.getOwnerEntity().getSize()));
        Entity colider = StateBasedEntityManager.getInstance().colides(sb.getCurrentStateID(),entity);
        
        
        if(colider == null){
        	return true;
        }else{
        	return (colider.isPassable());
        }
        
        
	}

}
