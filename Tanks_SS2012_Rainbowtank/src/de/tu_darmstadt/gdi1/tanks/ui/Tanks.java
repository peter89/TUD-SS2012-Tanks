package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.StateBasedEntityManager;

import global.Global;

/**
 * @author Peter Kilian
 *
 * Klasse Startet das Spiel "Tanks".
 * Enthaelt zwei State's für das Menue und das eigentliche Spiel.
 */
public class Tanks extends StateBasedGame {

	// Jeder State wird durch einen Integer-Wert gekennzeichnet
    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
	public static final int SETTINGSESTATE = 2;
	public static final int HIGHSCORESTATE = 3;
    public static final int LOADGAMESTATE = 4;
    //TODO Weitere Konstanten, die einen State identifizieren, einfuegen
    
    // Fuer die automatisierten Tests muessen Sie sicher stellen, dass ihr
    // Spiel auch ohne UI starten kann. Ist debug auf true gesetzt, so soll
    // das geschehen. Nutzen Sie diese Variable zur Abfrage in den anderen
    // Klassen, wenn Sie moechten.
    
    public static boolean debug = false;
    public static void setDebug(boolean debuging){ debug = debuging; }
 
    /**
     * Konstruktor des Spiel Tanks.
     * @param debug : falls true, dann soll Tanks ohne UI gestartet werden
     */
    public Tanks(boolean debug)
    {
        super("Tanks");	//Window Title
        setDebug(debug);
    }
 
    public static void main(String[] args) throws SlickException
    {
    	 Global.regLibs(); //Registriert libs für lwjgl
    	
    	// Setze dieses StateBasedGame in einen App Container (= Spielfenster)
        AppGameContainer app = new AppGameContainer(new Tanks(false));
 
        // Lege die Einstellungen des Fensters fest und starte das Fenster
        app.setShowFPS(false); // ohne Anzeige der FPS-Rate
        
        // Fuehren Sie hier (falls noetig), weitere Initialisierungen ein
        
        // Lege die Einstellungen des Fensters fest und starte das Fenster
        // (nicht aber im Vollbildmodus)
        app.setDisplayMode(800, 600, false);
        
        // Starte das Spiel Tanks
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
 
        // Fuege dem StateBasedGame die State's hinzu
		addState(new MainMenuState(MAINMENUSTATE));
        addState(new GameplayState(GAMEPLAYSTATE));
        addState(new HighScoreState(HIGHSCORESTATE));
        
        //TODO: States Erstellen und einbinden
        //addState(new LoadGameState(LOADGAMESTATE));
        //addState(new SettingState(SETTINGSESTATE));
        
        // Fuege dem StateBasedEntityManager die State's hinzu
        StateBasedEntityManager.getInstance().addState(MAINMENUSTATE);
        StateBasedEntityManager.getInstance().addState(GAMEPLAYSTATE);
        StateBasedEntityManager.getInstance().addState(HIGHSCORESTATE);
    }
}