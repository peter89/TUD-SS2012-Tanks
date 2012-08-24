package de.tu_darmstadt.gdi1.tanks.objects;
import global.Global;

public class Border extends TanksObjects{

	int life, maxlife;
	
	public Border(String id) {
		super(id);
		setPicture("thematrixer-net_stahlwand.jpg");
		this.setScale(0.7f);
	}
	
	public void setLife(int life){ this.life=life; }
	public void setMaxLife(int maxlife){ this.maxlife=maxlife; }
	
}