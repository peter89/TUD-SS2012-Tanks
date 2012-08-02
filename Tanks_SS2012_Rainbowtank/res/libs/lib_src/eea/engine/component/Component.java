package eea.engine.component;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;

public abstract class Component {
	
	protected String id;
    protected Entity owner;
    
    public Component(String id){
    	this.id = id;
    }
	
    public String getId()
    {
        return id;
    }
 
    public void setOwnerEntity(Entity owner)
    {
    	this.owner = owner;
    }
    
    public Entity getOwnerEntity(){
    	return owner;
    }
	
	
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta);

}
