package global;

import javazoom.jl.player.Player;  
import java.io.FileInputStream;
import java.io.BufferedInputStream;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import java.io.BufferedInputStream; 
import java.io.FileInputStream; 
import javazoom.jl.player.advanced.*; 
import java.io.*; 
import javazoom.jl.decoder.Bitstream; 
import javazoom.jl.decoder.Header; 
import javazoom.jl.decoder.Equalizer; 

@SuppressWarnings("unused")
public class playsound{
	
    private Player player;
    File soundFile;
    AudioInputStream audioInputStream = null;
    
	private static final int	EXTERNAL_BUFFER_SIZE = 128000;
	
	public void close() { if (player != null) player.close(); }

			// play the file to the sound card
		    public playsound(String filename) {
		    	
		    	soundFile = new File(filename);
		    	this.start(filename);
		    	
		    	System.out.println("Spielt: "+filename);
		    }
		    
		    
		    public void start(String filename){
		    	
				

				try
				{
					audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				}
				catch (Exception e){System.err.println("audioInputStream");}
				AudioFormat	audioFormat = audioInputStream.getFormat();
				SourceDataLine	line = null;
				DataLine.Info	info = new DataLine.Info(SourceDataLine.class,audioFormat);
				try
				{
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(audioFormat);
				}
				catch (LineUnavailableException e){System.err.println("LineUnavailableException");}
				line.start();
				int	nBytesRead = 0;
				byte[]	abData = new byte[EXTERNAL_BUFFER_SIZE];
				while (nBytesRead != -1)
				{
					try
					{
						nBytesRead = audioInputStream.read(abData, 0, abData.length);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}

					//   MEIN CODE ANFANG

					for(int i=0;i<abData.length;i++){
						abData[i]=(byte)(Settings.volume*(double)abData[i]);
					}

					//   MEIN CODE ENDE
					if (nBytesRead >= 0) line.write(abData, 0, nBytesRead);

				}

		    	
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