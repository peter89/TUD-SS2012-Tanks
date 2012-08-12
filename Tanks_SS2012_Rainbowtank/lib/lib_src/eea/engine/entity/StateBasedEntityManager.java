package eea.engine.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;



public class StateBasedEntityManager{
	
	private static final StateBasedEntityManager entityManager = new StateBasedEntityManager();
	private Map<Integer, List<Entity>> entities;
	
	public List<Entity> getEntitiesByState(int stateID){
		return entities.get(Integer.valueOf(stateID));
	}
	
	public void setEntitieListByState(int stateID, List<Entity> entities){
		this.entities.put(Integer.valueOf(stateID), entities);
	}
	
	private StateBasedEntityManager(){
		entities = new HashMap<Integer, List<Entity>>();
	}
	
	public void clearEntitiesFromState(int stateID){
		entities.get(Integer.valueOf(stateID)).clear();
	}
	
	public void addEntity(int state, Entity entity){
		entities.get(Integer.valueOf(state)).add(entity);
	}
	
	public Entity getEntity(int state, String name){
		Iterator<Entity> iterator = entities.get(Integer.valueOf(state)).iterator();
		while(iterator.hasNext()){
			Entity entity = iterator.next();
			if(entity.getId().equals(name)) return entity;
		}
		
		return null;
	}
	
	/**
	 * Schaut nach ob eine Entity existiert, dessen ID mit dem Prefix Existiert
	 * @param state
	 * @param name
	 * @return
	 */
	public boolean hasEntity(int state, String prefix){
		Iterator<Entity> iterator = entities.get(Integer.valueOf(state)).iterator();
		while(iterator.hasNext()){
			Entity entity = iterator.next();
			if(entity.getId().startsWith(prefix)) return true;
		}
		
		return false;
	}
	
	public void removeEntity(int state, Entity entity){
		entities.get(Integer.valueOf(state)).remove(entity);
	}
	
	public void updateEntities(GameContainer gc, StateBasedGame sb, int delta){
		Iterator<Entity> iterator = entities.get(Integer.valueOf(sb.getCurrentStateID())).iterator();
		while(iterator.hasNext()){
			iterator.next().update(gc, sb, delta);
		}
    }
 
    /**
     * Führe die render Funktion vom Zeichenbaren Objekt aus falls vorhanden und 
     * auf Sichtbar gestellt
     * @param gc
     * @param sb
     * @param gr
     */
    public void renderEntities(GameContainer gc, StateBasedGame sb, Graphics gr){
		Iterator<Entity> iterator = entities.get(Integer.valueOf(sb.getCurrentStateID())).iterator();
		while(iterator.hasNext()){
			iterator.next().render(gc, sb, gr);
		}
    }
    
    public Entity colides(int State, Entity entity){
    	Iterator<Entity> iterator = entities.get(Integer.valueOf(State)).iterator();
    	while(iterator.hasNext()){
    		Entity tempEntity = iterator.next();
    		if(tempEntity.colides(entity)) return tempEntity;
    	}
    	return null;
    }
    
    public static StateBasedEntityManager getInstance() {
    	return entityManager;
    }
    
    public void addState(int State){
    	entities.put(State, new CopyOnWriteArrayList<Entity>());
    }

}
