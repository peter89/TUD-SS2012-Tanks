package de.tu_darmstadt.gdi1.tanks.ui;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.StateBasedEntityManager;

import global.Debug;
import global.Global;
import global.Settings;

/**
 * @author Peter Kilian
 *
 * Klasse Startet das Spiel "Tanks".
 * Enthaelt zwei State's für das Menue und das eigentliche Spiel.
 */
public class Tanks extends StateBasedGame {
	
	// Jeder State wird durch einen Integer-Wert gekennzeichnet
    public static final int 
	    MAINMENUSTATE = 0,
	    GAMEPLAYSTATE = 1,
	    SETTINGSESTATE = 2,
	    HIGHSCORESTATE = 3,
	    LOADGAMESTATE = 4;
 
    /**
     * Konstruktor des Spiel Tanks.
     * @param debug : falls true, dann soll Tanks ohne UI gestartet werden
     */
    public Tanks(boolean debug)
    {
        super("Tanks");	//Window Title
        Debug.debug = debug;
    }
 
    public static void main(String[] args) throws SlickException
    {
    	 Global.regLibs(); //Registriert libs für lwjgl
    	
    	// Setze dieses StateBasedGame in einen App Container (= Spielfenster)
        AppGameContainer app = new AppGameContainer(new Tanks(false));
 
        // Lege die Einstellungen des Fensters fest und starte das Fenster
        app.setShowFPS(false); // ohne Anzeige der FPS-Rate

        // Lege die Einstellungen des Fensters fest und starte das Fenster
        // (nicht aber im Vollbildmodus)
        

        //Aktuelle Auflösung 800x600
        app.setDisplayMode(800, 600, false);
        
        /**/        
    	//Vollbildmodus aktivieren/deaktivieren
    	app.setFullscreen(Settings.fullscreen);
        
    	//Maximale FPS Anzahl setzen definieren
    	app.setTargetFrameRate(Settings.framerate);
    	
    	//VSync aktivieren/deaktivieren
        //Synchronisiert die Framerate der GrafikkartenGPU mit der Bildwiederholrate des Monitors
    	//Reduzieren der benötigte Leistung und damit auch die Stromaufnahme der GPU
    	//und somit auch die Abwärme.
    	app.setVSync(true);
        /**/
                
        // Starte das Spiel Tank
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

        // Fuege dem StateBasedGame die State's hinzu
		addState(new MainMenuState(MAINMENUSTATE));
        addState(new GameplayState(GAMEPLAYSTATE));
        addState(new HighScoreState(HIGHSCORESTATE));
        addState(new SettingState(SETTINGSESTATE));
        //TODO: States Erstellen und einbinden
        //addState(new LoadGameState(LOADGAMESTATE));
       
        
        // Fuege dem StateBasedEntityManager die State's hinzu
        StateBasedEntityManager.getInstance().addState(MAINMENUSTATE);
        StateBasedEntityManager.getInstance().addState(GAMEPLAYSTATE);
        StateBasedEntityManager.getInstance().addState(HIGHSCORESTATE);
        StateBasedEntityManager.getInstance().addState(SETTINGSESTATE);
        
    }
}