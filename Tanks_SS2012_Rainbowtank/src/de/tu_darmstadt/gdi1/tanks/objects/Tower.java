package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

public class Tower extends TanksObjects{

	int Munition;
	int Heat;   //Hitze wird durch schie�en verursacht
	
	public Tower(String id) {
		super(id);
		
		setScale(0.15f); //Gr��e
		//TODO: Tower der sich richtung maus dreht und bei klick schie�t
		//TODO automated shooting if enemy is in range
		
		Munition = 10;
	}
	
	//CollisionEvent Action
	Action shootaction = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			
		//TODO: Aktion f�r Schuss --> ADD Bullet

	}};
	
}
