package global;

public class Settings {

	//Debug
	public static boolean debug = false;
	
	//God
	public static boolean godmod = true; //Default
	
	//Anzahl Spieler
	public static int maxplayer;
	public static boolean bot; //Mit Computer Gegnern Auff�llen
	
	//Netzwerk
	public static String Host="";
	public static String Port="";
	
	//Grafik
	public static boolean fullscreen = false;
	public static int framerate = 60;
	
	//Sound
	public static boolean sound; //ton an/aus
	public static double volume=3.0; //Lautst�rkereglung Default 2 max 3.0

	
	//Steuerung
	//TODO: Navigation
	//setNavKey(int left,int right,int forward,int backward);
	
}