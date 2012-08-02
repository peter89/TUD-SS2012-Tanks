package global;

import eea.engine.entity.Entity;

public class Debuging {
	
	public static boolean debug = true; //Default
	public static boolean godmod = true; //Default
	
	public static boolean isdebug(){ return debug; }
	
	public static boolean isdebug(Entity obj){
		String classname = obj.getClass().getSimpleName();
		
		//an / abschalten des debugging für einzelne classen
		if (classname.equals("")) debug = false;
		if (classname.equals("")) debug = false;
		
	return debug;
	}
	
	public static boolean isgod(){ return godmod; } // kann beschädigt werden?
}
