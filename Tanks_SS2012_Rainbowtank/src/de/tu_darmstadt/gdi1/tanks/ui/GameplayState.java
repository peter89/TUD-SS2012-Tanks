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
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.*;

import sun.misc.GC;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.component.render.AnimationRenderComponent;

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


import eea.engine.event.Event;
//class für Events
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import global.Animate;
import global.Global;
import global.Parse;

import de.tu_darmstadt.gdi1.tanks.logic.CountdownEvent;
import de.tu_darmstadt.gdi1.tanks.objects.*;

import java.io.IOException;
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
	ParticleSystem partikelSystem = null;			// Partikelsystem
	
	Tank tankPlayer=null, tankPlayer2=null, tankOppenent=null;
	Tower tower=null;
	boolean test_bar = false; //test variable für anzeige von healthstatus
    
    GameplayState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
    
	Action add_tanks = new Action() {
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta,
				Component event) {
			
			Global.loadMapFromFile();
			
			// Tower wird erzeugt
			tower = new Tower("new tower");
			tower.setPosition(new Vector2f(300,100)); //Position
			tower.setRotation(75.4f); //Rotation
			tower.setAutoShoot();
			entityManager.addEntity(stateID, tower);
			
			// Panzer wird erzeugt
			tankPlayer = new Tank("player1 tank");
			tankPlayer.setNavKey(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			tankPlayer.setShootKey(Input.KEY_SPACE); //shootkey
			tankPlayer.setPosition(new Vector2f(150,200)); //Position
			tankPlayer.setRotation(95.4f); //Rotation
			tankPlayer.setPicture("tankPlayer.png"); //png bild
			entityManager.addEntity(stateID, tankPlayer);
			
			// Panzer wird erzeugt
			tankPlayer2 = new Tank("player2 tank");
			tankPlayer2.setNavKey(Input.KEY_A, Input.KEY_D, Input.KEY_W, Input.KEY_S);
			tankPlayer2.setShootKey(Input.KEY_E); //shootkey
			tankPlayer2.setPosition(new Vector2f(400,100)); //Position
			tankPlayer2.setRotation(-95.4f); //Rotation
			tankPlayer2.setPicture("tankPlayer2.png"); //png bild
			entityManager.addEntity(stateID, tankPlayer2);
			
			// Panzer wird erzeugt
			tankOppenent = new Tank("oponend tank");
			//tankOppenent.setNavKey(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			tankOppenent.setPosition(new Vector2f(200,100)); //Position
			tankOppenent.setRotation(95.4f); //Rotation
			tankOppenent.setPicture("tankOppenent.png"); //png bild
			tankOppenent.setShootKey(Input.KEY_SPACE); //shootkey
			entityManager.addEntity(stateID, tankOppenent);
		}};
 	
    /**
     * Wird vor dem (erstmaligen) Starten dieses States ausgefuehrt
     */
    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

    	// Hintergrund laden
	    	Entity background = new Entity("background");	// Entitaet fuer Hintergrund
	    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
	    	background.addComponent(Global.getImage("background.jpg")); // Bildkomponente
			
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
    	
    	//Setzt Partikelsystem Feuer an Possition 300,270
		try {
			partikelSystem = ParticleIO.loadConfiguredSystem(Global.getPath("skins")+"firewithsmoke.xml");
		} catch (IOException e1) {
			System.err.println("Partikel System konnte nicht geladen werden: "+e1);
		}
		partikelSystem.setPosition(300, 270);
		
		
		
		//Countdown Event
		Entity CountDownListener = new Entity("CountDownListener");
		CountdownEvent cde = new CountdownEvent();
		cde.addAction(add_tanks);
		cde.setTimer(5);
		CountDownListener.addComponent(cde);
		entityManager.addEntity(stateID, CountDownListener);
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
    	partikelSystem.update(delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// StatedBasedEntityManager soll alle Entities rendern
		entityManager.renderEntities(gc, game, g);
		partikelSystem.render();
		//g.drawAnimation(Global.animate("expl"), 200, 200);


		float healthX=10, healthY=10;
		
		
		try{
			Vector2f pos = entityManager.getEntity(stateID, "tankPlayer").getPosition();
			healthX = pos.x;
			healthY = pos.y;
			
			g.drawString("string x:"+ pos.x + "|y:"+pos.x, pos.x, pos.y);
		}
		catch(Exception e){
			
		}
		float maxHealth=100;
		
		float healthWidth=maxHealth, healthHeight=10;
		float radius=healthHeight/2;		
		
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