package global;

public class Debug {
	
	public static boolean debug = true; //Default
	
	public static boolean isdebug(Object obj){
		String classname = obj.getClass().getSimpleName();
		
		//an / abschalten des debugging für einzelne Entityklassen
		if (classname.equals("")) debug = false;
		if (classname.equals("")) debug = false;
		
	return debug & Settings.debug; //Boolische & Verknüpfung - Settings Debug & Debug Obj
	}
}
