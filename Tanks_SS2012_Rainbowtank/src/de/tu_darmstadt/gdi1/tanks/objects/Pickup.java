package de.tu_darmstadt.gdi1.tanks.objects;

import eea.engine.action.basicactions.DestroyEntityAction;
import global.Global;

public class Pickup extends TanksObjects {

	public Pickup(String id) {
		super(id);
		
		//ADD CollisionsEvent (Eigenes Objekt Zerstören)
		ce.addAction(new DestroyEntityAction());
	}
	
	public void addHealth(){
		health(+30);
		setPicture("healthpack.png");
	}
	
	public void addMuniPack(){
		health(-30);
		setPicture("munipack.png");
		munition += Global.rand(20,40); 	//zufallsmunition von 20 bis 40
	}

	public void addToolPack(){
		//getriebe(Global.rand(7,13)/10f); 	//Reperatur und Tuning von Getriebe von +1.3 bis +1.7;
		setPicture("werkzeug.png");
	}

	
}
