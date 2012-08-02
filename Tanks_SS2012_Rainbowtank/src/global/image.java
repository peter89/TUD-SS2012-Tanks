package global;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class image {
	public Image getImage(String image) throws SlickException{ 
	try {
		return new Image(Global.getPath("img")+image);
	} catch (SlickException e) {
		System.err.println("Bild: "+Global.getPath("img")+image+" wurde nicht gefunden");
		e.printStackTrace();
	}
	return null;
	}
}
