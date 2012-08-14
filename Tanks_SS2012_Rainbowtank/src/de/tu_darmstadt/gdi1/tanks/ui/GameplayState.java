package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.RoundedRectangle;
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

import de.tu_darmstadt.gdi1.tanks.logic.FarbigeStatusAnzeige;
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

	int health = 0;

	private int stateID; 							// Identifier dieses BasicGameState
	public StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
	
	Tank tankPlayer=null;
	boolean test_bar = false;
    
    GameplayState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
	Action shoot = new Action() {
   		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
   			
			Shot mybullet = new Shot("a Bullet");
			
			//abstand generierung vor kanonenrohr abhängig von tankgröße
			int offset = (int) (tankPlayer.getScale()*-250);
			Vector2f bullet_position = new Vector2f(0,offset).add(tankPlayer.getRotation());
			bullet_position.add(tankPlayer.getPosition());
			mybullet.setPosition( bullet_position ); //Position
			mybullet.setRotation( tankPlayer.getRotation() ); //Rotation

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
			loop_shoot.wait();
			
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
		
		//g.drawAnimation(Global.animate("expl"), 200, 200);

		float healthX=10, healthY=10;
		
		try{
			healthX = tankPlayer.getPosition().x;
			healthY = tankPlayer.getPosition().y;
		}
		catch(Exception e){
			
		}
		float maxHealth=100;
		
		float healthWidth=maxHealth, healthHeight=10;
		float radius=healthHeight/2;

		

		
		//FarbigeStatusAnzeige bar1 = new FarbigeStatusAnzeige(95);
		//FarbigeStatusAnzeige bar2 = new FarbigeStatusAnzeige(50);
		//FarbigeStatusAnzeige bar3 = new FarbigeStatusAnzeige(1);
		
		

		
		
		RoundedRectangle healthBG = new RoundedRectangle(healthX, healthY, healthWidth+(1.4f*2), healthHeight+1.4f, radius);
		
        float healthGAWidth = ((float) health / (float) maxHealth) * (float) healthWidth;
        RoundedRectangle healthGA = new RoundedRectangle(healthX+0.7f, healthY+0.7f, healthGAWidth, healthHeight,radius);
        
        g.setColor(Color.gray);
        g.fill(healthBG);
        g.setColor(Color.gray);
        g.draw(healthBG);
        
        

        FarbigeStatusAnzeige bar = new FarbigeStatusAnzeige("mytank health");
        if (tankPlayer!=null){
        	health=tankPlayer.health;
        	
        }
        
        
        
        	
        	if(health==100 || health==0){
        		test_bar=test_bar?false:true;  //Umschalten       	
        	}
        	
        	if(test_bar) health++; else health--;
        
        g.setColor(bar.getColor(health));
        g.fill(healthGA);
        g.draw(healthGA);

		g.flush();//IMPORTANT!!!
		
		
		

        /*
        AngelCodeFont font = null;
        font.drawString(50, 50, "Spielname");
        font.drawString(50, 75, "Nutze space zum starten.");
       */
	}

	@Override
	public int getID() {
		return stateID;
	}
}