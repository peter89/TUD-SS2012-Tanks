package global;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Animate {
	
	public Image[] frames;

	Animate(String obj) {		
		String path = Global.getPath("animation")+obj+File.separator;
		
		File dir = new File(path);
		String[] fileList = dir.list(new FilenameFilter() {
		    public boolean accept(File d, String name) {
		       return name.endsWith(".png");
		    }
		});
		
		Arrays.sort(fileList); //sortieren
		
		//Image Array Anlegen mit anzahl der Bilder in Verzeichnis 
		Image[] frames = new Image[fileList.length];
		
		for (int i=0; i<fileList.length; i++) {
			String img = fileList[i];
				
				try{
					// addFrame: Frame , ShowTime
					frames[i]= new Image(path+img);
				}
				catch (SlickException e){
						System.err.println(
							"Animation Dir " + obj + " not found in " + Global.getPath("animation") +
							" be shure you your animation files ends with '.png'"
							);
				}
			}
		if (Debug.isdebug(this)) System.out.println("Animation: " + obj + " Gestartet");
		}
}