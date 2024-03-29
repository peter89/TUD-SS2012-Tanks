package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import global.Debug;
import global.Global;

/**
 * @author Peter Kilian
 *
 * SettingState Liefert alle Einstellungsmöglichkeiten
 */
public class SettingState extends BasicGameState implements ComponentListener {

	public String[] stringname;

	private int stateID; 								// Identifier von diesem BasicGameState
	private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
    
	public final int distance = 50; //abstand zwischen einträgen
	
    public final int start_text_x = -120, start_text_y = -10;
    public final int start_x = 350, start_y = 130;

	/** The field taking the name */
	private TextField name;
	/** The name value */
	private String nameValue = "none";
	/** The saved state */
	private SavedState state;
	/** The status message to display */
	private String message = "Enter a name";
	
	
	SettingState(int sid) {
	stateID = sid;
    entityManager = StateBasedEntityManager.getInstance();
    
    stringname = new String[1];//TODO: Get from File
	stringname[0] = "Hauptmenü";
	
	if (Debug.isdebug(this)) System.out.println(getID() + " wurde gestartet");
	}
    /**
     * Wird vor dem (erstmaligen) Starten dieses State's ausgefuehrt
     */
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	Entity test = new Entity("textfeld");
    	test.setPosition(new Vector2f(100,100));
    	
    	name = new TextField(container,container.getDefaultFont(),100,100,300,20,this);
    	name.setText("testtext");
    	
    	
    	//test.addComponent(name);
    	//Event myEvent = new Event();
    	
    	
    	//test.addComponent()
    	   	
    	
    	
    	//Hintergrund laden
    	Entity background = new Entity("menu");	// Entitaet fuer Hintergrund
    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
    	background.addComponent(Global.getImage("highscore_menu.png")); // Bildkomponente Hintergrundmenü
    	entityManager.addEntity(stateID, background); // Hintergrund-Entitaet an StateBasedEntityManager uebergeben


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

       		if(item.equals("Hauptmenü"))
       			new_Game_Action = new ChangeStateInitAction(Tanks.MAINMENUSTATE);       	    	

       		mainEvents.addAction(new_Game_Action);
       		Menu_Entity.addComponent(mainEvents);
        	

        	// Fuege die Entity zum StateBasedEntityManager hinzu
        	entityManager.addEntity(this.stateID, Menu_Entity);
    	}	

    }
	/**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
	
		entityManager.renderEntities(gc, game, g);
		int i=0;
		//Erstellen der Strings
		for(String item: stringname){ g.drawString(item, start_x+start_text_x, start_y+start_text_y+i*distance); i++; }
		
		
	}

	@Override
	public int getID() {
		return stateID;
	}
	@Override
	/**
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		if (source == name) {
			nameValue = name.getText();
			state.setString("name", nameValue);
		}
		/*
		if (source == age) {
			try {
				ageValue = Integer.parseInt(age.getText());
				state.setNumber("age", ageValue);
			} catch (NumberFormatException e) {
				// ignone
			}
		}
		*/

		try {
			state.save();
		} catch (Exception e) {
			message = System.currentTimeMillis() + " : Failed to save state";
		}
	}
}