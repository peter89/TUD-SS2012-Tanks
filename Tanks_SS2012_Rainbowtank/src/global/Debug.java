package global;

public class Debug {
	
	public static boolean debug = true; //Default
	
	public static boolean isdebug(Object obj){
		String classname = obj.getClass().getSimpleName();
		
		//an / abschalten des debugging f�r einzelne Entityklassen
		if (classname.equals("")) debug = false;
		if (classname.equals("")) debug = false;
		
	return debug & Settings.debug; //Boolische & Verkn�pfung - Settings Debug & Debug Obj
	}
}
