package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.objects.Tank;
import de.tu_darmstadt.gdi1.tanks.objects.Tower;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import global.Global;

public class MapEditorState extends BasicGameState {

	public String[] stringname;
	Input input;

	private int stateID; 								// Identifier von diesem BasicGameState
	private StateBasedEntityManager entityManager, entityManagerForMap; 	// zugehoeriger entityManager
	
    
	public final int distance = 50; //abstand zwischen einträgen
	
    public final int start_text_x = -120, start_text_y = -10;
    public final int start_x = 350, start_y = 130;

    MapEditorState(int sid) {
	stateID = sid;
    entityManager = StateBasedEntityManager.getInstance();
    entityManagerForMap = StateBasedEntityManager.getInstance();
    
    stringname = new String[1];//TODO: Get from File
	stringname[0] = "Hauptmenü";
	}
    
    /**
     * Wird vor dem (erstmaligen) Starten dieses State's ausgefuehrt
     */   	
        @Override
    	public void init(GameContainer container, StateBasedGame game) throws SlickException {

        	// Hintergrund laden und an StateBasedEntityManager uebergeben
    	    	Entity background = new Entity("background");	// Entitaet fuer Hintergrund
    	    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
    	    	background.addComponent(Global.getImage("background.jpg")); // Bildkomponente
    	    	entityManager.addEntity(stateID, background);

        	// Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
    	    	Entity esc_Listener = new Entity("ESC_Listener");
    	    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
    	    	esc_pressed.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));
    	    	esc_Listener.addComponent(esc_pressed);    	
    	    	entityManager.addEntity(stateID, esc_Listener);
    	    	
    	int i=0;
    	for(String item : stringname){
    		
    		Entity Menu_Entity;    		
    		
    		
        	Menu_Entity = new Entity(item); //neue identität
        	
        	// Setze Position und Bildkomponente
        	Menu_Entity.setPosition(new Vector2f(start_x, start_y+distance*i)); i++;
        	Menu_Entity.setScale(0.28f);
       		Menu_Entity.addComponent(Global.getImage("entry.png"));
       		
       		// Erstelle das Ausloese-Event und die zugehoerige Action
       		ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
        	
        	//Zuweißen der Actionen
       		Action new_Game_Action = null;

       		if(item.equals("Hauptmenü")) new_Game_Action = new ChangeStateInitAction(Tanks.MAINMENUSTATE);   	    	    		
    		
       		mainEvents.addAction(new_Game_Action);
       		Menu_Entity.addComponent(mainEvents);
        	

        	// Fuege die Entity zum StateBasedEntityManager hinzu
        	entityManager.addEntity(this.stateID, Menu_Entity);
        	
        	Entity test = new Entity("mausaction");
        	
        	MouseClickedEvent mouseClicked = new MouseClickedEvent();
        	mouseClicked.addAction(new Action(){
				@Override
				public void update(GameContainer gc, StateBasedGame sb, int delta,
						Component event) {
					try{
			    	Tank test = new Tank("Tower");
		        	test.setPosition(new Vector2f((int) input.getMouseX(),(int) input.getMouseY()));
		        	
		        	entityManager.addEntity(getID(), test);
		        	System.out.println("Objekt an possition "+input.getMouseX()+"|"+input.getMouseY()+" erzeugt");
		        		
		        	//TODO: mouseweel to rotate objects
		        	//test.setRotation(float rotate);
		        	}catch(Exception e){
		        		System.err.println("Objekt an possition "+input.getMouseX()+"|"+input.getMouseY()+" nicht erzeugt");
		        	}
				}
        	});
        	test.addComponent(mouseClicked);
    	}	

    }
	/**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
		entityManagerForMap.updateEntities(container, game, delta);	    	
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {		
		entityManager.renderEntities(gc, game, g);
		entityManagerForMap.renderEntities(gc, game, g);
		
		input = gc.getInput();
		
		String x = Integer.toString((int) input.getMouseX());
		String y = Integer.toString((int) input.getMouseY());
		
		g.drawString("x="+x, 10, 10);
		g.drawString("y="+y, 10, 25);
		
		int i=0;
		//Erstellen der Strings
		for(String item: stringname){ g.drawString(item, start_x+start_text_x, start_y+start_text_y+i*distance); i++; }
	}

	@Override
	public int getID() {
		return stateID;
	}
}