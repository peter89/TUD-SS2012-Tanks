Tanks
=====

TUD-Projekt SS2012

Menü
„Spiel starten“
„Bestenliste“
„Optionen“ - Spielsteuerung - Skin
„Beenden“

Entitäten


Libs
	Audio
		jl1.0.1.jar // JLayer - MP3 Library javazoom.net
	
	Grafik
		lwjgl 		// Lightweight Java Game Library (LWJGL) lwjgl.org
		slick		// 2D Game Library based on LWJGL slick.cokeandcode.com
		
		Modern Java 3D JMONKEYENGINE 3.0 JAVA OPENGL GAME ENGINE http://jmonkeyengine.com
		
		acm			// ACM Java Task Force jtf.acm.org
		eea			// Entity Event Action
		translator  //   



# Events					# Action
#############################################################
ce ColisionsEvent 			ca ColisionsAction
lse LeavingScreenEvent 		lsa LeavingScreenAction



Hallo zu Tanks...

Objekt Tank;
Objket Tower;

Tank < Turm  				//tank extends Turm
Tank < Turm + Navigation	//tank add Navigation

Objekt Bullet;
Objekt Wall;