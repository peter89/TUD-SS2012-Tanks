package eea.engine.event;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
 
import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * Ein Event beschreibt ein Ereignis. Zu einem Event lassen sich beliebig viele 
 * Actions hinzufuegen. Ein Event wird einer Entity zugeordnet und besitzt einen
 * Identifier.
 */
public abstract class Event extends Component{
 

    private ArrayList<Action> Actions = null;
    
    public Event(String id){
    	super(id);
    	Actions = new ArrayList<Action>();
    }
    
    public void addAction(Action ac){
    	Actions.add(ac);
    }
    
    public void removeAction(Action ac){
    	Actions.remove(ac);
    }
    
    public void removeAction(int index){
    	Actions.remove(index);
    }
    
    public void clearActions(){
    	Actions.clear();
    }    
 
 
    protected abstract boolean performAction(GameContainer gc, StateBasedGame sb, int delta);
    
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	if(performAction(gc, sb, delta)){
    		for(Action action : Actions)
            {
                action.update(gc, sb, delta, this);
            }
    	}
    }
}
