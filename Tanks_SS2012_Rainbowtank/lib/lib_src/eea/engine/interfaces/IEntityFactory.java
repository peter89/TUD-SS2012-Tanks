package eea.engine.interfaces;

import eea.engine.entity.Entity;

/**
 * Jede EntityFactory muss einen createEntity()-Konstruktor enthalten.
 */
public interface IEntityFactory {
	
	public Entity createEntity();
	
}
