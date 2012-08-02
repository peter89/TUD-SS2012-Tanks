package global;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Animate extends Animation implements Renderable {
	
	public int duration; //Anzeige Frames

	Animate(String obj) {

		this.duration=20; //Anzeige Frames
		this.setLooping(false);
		this.setSpeed(1.7f);
		this.start();
		
		String path = Global.getPath("animation")+obj;
		
		File dir = new File(path);
		String[] fileList = dir.list(new FilenameFilter() {
		    public boolean accept(File d, String name) {
		       return name.endsWith(".png");
		    }
		});
		
		Arrays.sort(fileList); //sortieren
		
		for (String item: fileList) {
			String img = item;
			System.out.println(item);

		try{
			// addFrame: Frame , ShowTime			
			addFrame(new Image(path+img), duration); // Add animation frame to the animation
		}catch (SlickException e){
				System.err.println(
					"Animation Dir " + obj + " not found in " + Global.getPath("animation") +
					" be shure you your animation files ends with '.png'"
					);
				}
		}
		System.out.println("Animation: " + obj + " Gestartet");
	}
}
