package eea.engine.interfaces;

import org.newdawn.slick.geom.Vector2f;

public interface IMovement {
	
	public Vector2f getNextPosition(final Vector2f position, float speed,float rotation,int delta);

}
