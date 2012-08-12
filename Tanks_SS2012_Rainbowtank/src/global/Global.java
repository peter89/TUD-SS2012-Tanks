package global;
import java.io.File;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import global.reglib;

/**
 * Autor: Peter Kilian
 * Stellt grundlegende Funktionen und Pfade zur Verfügung
 *
 ***
 * getPath(String res) //res: skin image audio animation
 ***
 # Sound
 * Void getSound(String file)
 ***
 # Image
 * Image getImage(String image)
 ***
 # Animation
 * Animate expl = new Animate("expl"); //Verzeichnis mit png's die Animiert werden sollen
 * expl.draw(x,y);
 *** Register Lib's
 * regLibs() // register Libs for GL
 * */
public class Global {
	
	static String[] myskin = { "defaultskin", "NewSkin"};
	static int cur_skin = 0;
	static String skin = myskin[cur_skin];
	

	//Change SkinsPath to res/skins/ ***
	public static void setSkin(String Skin){skin = Skin;} //extends UI Manager
	
	public static String getPath(String res){
		//RessourcePath (do not change please)
		String path = null;
		if (res == "root") 	path = System.getProperty("user.dir")+File.separator;
		if (res == "res")  	path = getPath("root")+res+File.separator;
		if (res == "lib")  	path = getPath("root")+res+File.separator;
		if (res == "lwjgl") path = getPath("lib")+res+"-2.8.3"+File.separator+"native"+File.separator;
		if (res == "skins") path = getPath("res")+res+File.separator;
		if (res == "skin") 	path = getPath("skins")+skin+File.separator;  //Current Skin
		
		//MediaPath (Current Skin)
		if (res == "images")	path = getPath("skin")+res+File.separator;
		if (res == "audio")		path = getPath("skin")+res+File.separator;
		if (res == "animation") path = getPath("skin")+res+File.separator;
		
		//Link
		if (res == "img")   	path = getPath("images");

		//prüfe ob pfad existiert
		File f = new File(path);
		if (!f.exists()){
			System.err.println("Path: "+path+" does not exist changing to default skinpath");
			skin = myskin[0]; //change to default skin
			path = getPath(res);
			skin = myskin[cur_skin]; //change to current skin
		}
		return path;
	}
	
	public static Image getImage(String image){ 
		Image img = null;
		try { img = new Image(Global.getPath("img")+image);	}
		catch (SlickException e) {
			System.err.println("Bild: "+Global.getPath("img")+image+" wurde nicht gefunden");
		}
		return img; 
	}
	
	public static void getSound(String file){
		new playsound(getPath("audio")+file);
	}
	
	
	public static Animation animate(String folder){ return new Animate(folder); }
	//Zufallszahl von ... bis ...
	public static int rand(int start, int end){ return new Random( end-start ).nextInt()+start; }

	//Wahrscheinlichkeitsfunktion
	public static boolean likelihood(int a){
		int percent = rand( 0, 100);
		return (percent<=a)? true : false;
		}

	public static void regLibs(){
		new reglib("org.lwjgl.librarypath", getPath("lwjgl"));
		}
}
