package de.tu_darmstadt.gdi1.tanks.logic;

import java.util.Timer;
import java.util.TimerTask;

public class countdown {

    private int count = 10;
    
    public countdown() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(count);
                if (count > 0)
                    count--;
                
                if (count == 0)
                    System.exit(0);
            }
        };
        timer.schedule(task, 0, 1000);
    }
    
    public static void main(String[] args) {
        new countdown();
    }
}