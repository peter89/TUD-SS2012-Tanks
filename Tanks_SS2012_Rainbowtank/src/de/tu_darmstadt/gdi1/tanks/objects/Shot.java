package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import global.Debug;
import global.Global;

import java.util.Random;
import java.math.*;

import de.tu_darmstadt.gdi1.tanks.ui.GameplayState;

@SuppressWarnings("unused")
public class Shot extends Mine {

	public Shot(String id) {
		super(id);
	
		setVisible(false);
		
		//Bild:
		setPicture("shoot.png");
		//addComponent(Global.getAimation("expl"));

		float speed = 0.25f; //set speed of object Bullet
		float ablenkung=0.01f; // Ablenkung
	
		setScale(0.05f); //Größe des Objects
		
		if(Debug.isdebug(this)) System.err.println("Bullet created with speed: "+speed);
    	
		// Move forward with speed / cycle
		LoopEvent loop = new LoopEvent();
    	loop.addAction(new MoveForwardAction(speed));
    	addComponent(loop);  // Loop Event

		//Ablenkung bei Schießen random  0.0f - 1.0f /20 / cycle		
		LoopEvent loop_abweichung = new LoopEvent();
    	loop_abweichung.addAction(new RotateLeftAction( new Random().nextFloat() * ablenkung ));
    	loop_abweichung.addAction(new RotateRightAction( new Random().nextFloat() * ablenkung ));
    	
    	addComponent(loop_abweichung);  // Loop Event
	}
}