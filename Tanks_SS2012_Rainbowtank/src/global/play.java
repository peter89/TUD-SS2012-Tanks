package global;

import javazoom.jl.player.Player;  
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class play{	 
    private Player player;

		    public void close() { if (player != null) player.close(); }


		    // play the MP3 file to the sound card
		    public play(String filename) {
		    	
		    	System.out.println("Spielt: "+filename);
		    	
		        try {
		            FileInputStream fis     = new FileInputStream(filename);
		            BufferedInputStream bis = new BufferedInputStream(fis);
		            player = new Player(bis);
		        }
		        catch (Exception e) {
		            System.out.println("Problem playing file " + filename);
		            System.out.println(e);
		        }

		    // run in new thread to play in background
		    new Thread() {
		        public void run() {
		            try { player.play(); }
		            catch (Exception e) { System.out.println(e); }
		        }
		    }.start();


		    }
		    
	    }
