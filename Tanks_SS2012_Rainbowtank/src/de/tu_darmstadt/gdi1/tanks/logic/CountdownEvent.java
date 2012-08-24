package de.tu_darmstadt.gdi1.tanks.logic;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class CountdownEvent extends Event{

    private int count = 10;
    boolean finished=false;
    
    TimerTask task;
    
    public CountdownEvent() {
    	super("CountdownEvent");
    	
        Timer timer = new Timer();
        
        task = new TimerTask() {
            @Override
            public void run() {
                if (count > 0)
                    count--;
                
                if (count == 0){ finished=true; }
            }
        };
        
        timer.schedule(task, 0, 1000);
    }
   
    public String getCurrentTime(){return Integer.toString(count); }
    public void setTimer(int sec){ count=sec; }
    
	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		// TODO Auto-generated method stub
		if(finished){
			finished=false;
			task.cancel();
			return true;
		}
	return false;
	}
}