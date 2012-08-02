package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sun.misc.GC;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

//class für Actions
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;

import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;


//class für Events
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import global.Global;

import de.tu_darmstadt.gdi1.tanks.objects.*;

import java.util.Random;

/**
 * @author Timo BÃ¤hr
 *
 * Diese Klasse repraesentiert das Spielfenster, indem ein Wassertropfen
 * erscheint und nach unten faellt.
 */
@SuppressWarnings("unused")
public class GameplayState extends BasicGameState {


	private int stateID; 							// Identifier dieses BasicGameState
	private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
	
	Tank tankPlayer;
    
    GameplayState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
	Action shoot = new Action() {
   		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
   			
			Bullet mybullet = new Bullet("a Bullet");
			mybullet.setPosition(new Vector2f(200,200)); //Position
			mybullet.setRotation(95.4f); //Rotation
			entityManager.addEntity(stateID, mybullet );
	}};
	
    /**
     * Wird vor dem (erstmaligen) Starten dieses States ausgefuehrt
     */
    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

    	// Hintergrund laden
	    	Entity background = new Entity("background");	// Entitaet fuer Hintergrund
	    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
	    	background.addComponent(new ImageRenderComponent(Global.getImage("background.jpg"))); // Bildkomponente

	    // Hintergrund-Entitaet an StateBasedEntityManager uebergeben
	    	entityManager.addEntity(stateID, background);

    	// Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
	    	Entity esc_Listener = new Entity("ESC_Listener");
	    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
	    	esc_pressed.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));
	    	esc_Listener.addComponent(esc_pressed);    	
	    	entityManager.addEntity(stateID, esc_Listener);
    	

    	// Bei Mausklick sollen Objekte erscheinen
    	Entity mouse_Clicked_Listener = new Entity("Mouse_Clicked_Listener");
    	MouseClickedEvent mouse_Clicked = new MouseClickedEvent();
    	    	
    	// Bei Drücken der LeerTaste Schießen
    	Entity shoot_Listener = new Entity("Shoot_Listener");    	
    	KeyPressedEvent space_pressed = new KeyPressedEvent(Input.KEY_SPACE);
    	space_pressed.addAction(shoot);
    	shoot_Listener.addComponent(space_pressed);
    	entityManager.addEntity(stateID, shoot_Listener);
    	
    	
    	Entity autoshoot = shoot_Listener;
    
    	//Bullet Einstellungen
		//Schießen...
    	/*'test abweichung *//*
		LoopEvent loop_shoot = new LoopEvent();
		try {
			loop_shoot.wait(9999999);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loop_shoot.addAction(shoot);
		autoshoot.addComponent(loop_shoot);
		
		entityManager.addEntity(stateID, autoshoot);
		*/
		
    	
    	Action add_tanks = new Action() {
    		@Override
    		public void update(GameContainer gc, StateBasedGame sb, int delta,
    				Component event) {

    			// Panzer wird erzeugt
    			tankPlayer = new Tank("player1 tank");
    			tankPlayer.setNavKey(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
    			tankPlayer.setPosition(new Vector2f(150,200)); //Position
    			tankPlayer.setRotation(95.4f); //Rotation
    			tankPlayer.setPicture("tankPlayer.png"); //png bild
    			tankPlayer.setShootKey(Input.KEY_SPACE); //shootkey

    			entityManager.addEntity(stateID, tankPlayer);

    			
    			
    			// Panzer wird erzeugt
    			Tank tankPlayer2 = new Tank("player2 tank");
    			tankPlayer2.setNavKey(Input.KEY_A, Input.KEY_D, Input.KEY_W, Input.KEY_S);
    			tankPlayer2.setPosition(new Vector2f(400,100)); //Position
    			tankPlayer2.setRotation(-95.4f); //Rotation
    			tankPlayer2.setPicture("tankPlayer2.png"); //png bild
    			tankPlayer.setShootKey(Input.KEY_E); //shootkey
    			
    			entityManager.addEntity(stateID, tankPlayer2);
    			
    			
    			// Panzer wird erzeugt
    			Tank tankOppenent = new Tank("oponend tank");
    			tankOppenent.setNavKey(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
    			tankOppenent.setPosition(new Vector2f(200,100)); //Position
    			tankOppenent.setRotation(95.4f); //Rotation
    			tankOppenent.setPicture("tankOppenent.png"); //png bild
    			tankOppenent.setShootKey(Input.KEY_SPACE); //shootkey
    			
    			entityManager.addEntity(stateID, tankOppenent);
    			
    			    			
    			
    			// Tower wird erzeugt
    			Tower tower = new Tower("new tower");
    			tower.setPosition(new Vector2f(100,100)); //Position
    			tower.setRotation(75.4f); //Rotation
    			tower.setPicture("flac.png"); //png bild
    			
    			entityManager.addEntity(stateID, tower);

    		}};
    	
    		
    	mouse_Clicked.addAction(add_tanks);
    	mouse_Clicked_Listener.addComponent(mouse_Clicked);    	
    	entityManager.addEntity(stateID, mouse_Clicked_Listener);
    }

    /**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// StatedBasedEntityManager soll alle Entities aktualisieren
    	Input input = gc.getInput();
		
    	//if (input.isKeyDown(Input.KEY_0)){}
    	entityManager.updateEntities(gc, game, delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// StatedBasedEntityManager soll alle Entities rendern
		entityManager.renderEntities(gc, game, g);		
	}

	@Override
	public int getID() {
		return stateID;
	}
}