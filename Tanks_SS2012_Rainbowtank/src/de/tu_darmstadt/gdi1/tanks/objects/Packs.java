package de.tu_darmstadt.gdi1.tanks.objects;

import eea.engine.action.basicactions.DestroyEntityAction;
import global.Global;

public class Packs extends TanksObjects {

	public Packs(String id) {
		super(id);
		
		//ADD CollisionsEvent (Eigenes Objekt Zerstören)
		ce.addAction(new DestroyEntityAction());
	}
	
	void addHealth(){
		health(+30);
		setPicture("healthpack.png");
	}
		
	void addMine(){
		health(-30);
		setPicture("mine.png");
		schaden = Global.rand(20,40); 	//zufallsschaden von 20 bis 40
	}
	
	void addMuniPack(){
		health(-30);
		setPicture("munipack.png");
		munition += Global.rand(20,40); 	//zufallsmunition von 20 bis 40
	}

}
