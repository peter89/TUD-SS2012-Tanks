package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class MouseEnteredEvent extends Event {

	public MouseEnteredEvent() {
		super("MouseEnteredEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Vector2f position = this.getOwnerEntity().getPosition();
		Vector2f size = this.getOwnerEntity().getSize();
		float roation = this.getOwnerEntity().getRotation();
		
		Transform transform = Transform.createRotateTransform((float)Math.toRadians(roation), position.x, position.y);
		Shape shape = (new Rectangle(position.x-(size.x/2), position.y-(size.y/2), size.x, size.y)).transform(transform);
		
		Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		return (shape.contains(mousePosition.x, mousePosition.y));
	}

}
