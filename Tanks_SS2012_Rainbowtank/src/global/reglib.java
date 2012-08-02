package global;

public class reglib {
	reglib(String pack, String lib){
	// set library path depended from os
	String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {//Windows
			System.setProperty(pack,lib+"windows");
		} else if (os.contains("mac")) {//Mac
			System.setProperty(pack,lib+"macosx");
		} else if (os.contains("nix")|| os.contains("nux")){//Unix & Linux
			System.setProperty(pack,lib+"linux");
		} else if (os.contains("sunos")){ //Solaris
			System.setProperty(pack,lib+"solaris");
		} else {
			System.err.print("OS not Supportet");
		}
	}
}
