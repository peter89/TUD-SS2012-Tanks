package global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tests.xml.Item;

import de.tu_darmstadt.gdi1.tanks.model.exceptions.SemanticException;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SyntaxException;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

import de.tu_darmstadt.gdi1.tanks.objects.*;

import eea.engine.entity.*;
public class Parse {
	
	Parse(){
		
	}
	void saveMapToFile(File file){
	  	//TODO:

		this.toString();
		//entities.parse >> Write to file
	}
	
	@Override
	public String toString(){
		//Fill List<Entity> with Entities from Tanks.GAMEPLAYSTATE
		List<Entity> entities = new ArrayList<Entity>();//Leere Entity Liste
		entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		

		return entities.toString();
	}
	
	String getToken(String tmp){
		return tmp.substring( 1, tmp.length()-1);
	}
	
	/**
	 * Lade eine Karte von einem File
	 * @param file
	 * @throws SyntaxException
	 * @throws SemanticException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void loadMapFromFileWithExceptions(File file) throws SyntaxException, SemanticException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			BufferedReader in = new BufferedReader(new FileReader(Global.getPath("maps")+"map00"));
			String zeile = null;
			int i=0;
			while ((zeile = in.readLine()) != null) {

				if( zeile.length()>=1){
				zeile = zeile.replaceAll("\t", " ");
				zeile = zeile.replaceAll("\n", " ");
				zeile = zeile.replaceAll("\r", " ");
				zeile = zeile.replaceAll("  ", " ");

				
				char[] data=zeile.toCharArray();
				//Lehrzeichen am anfang entfernen sofern vorhanden
				if (data[0]==' ') zeile = zeile.substring(1);
				if (data[0]==' ') zeile = zeile.substring(1);
				}
				
				//Zeile enthält mehr wie ein zeichen...  ...und ist keine Komentarzeile
				if( zeile.length()>=1 && (! zeile.contains("#")) && (! zeile.contains("//")) && (! zeile.startsWith("\n")) ) {

System.out.println("Zeile"+(++i));

					
					System.out.println("Gelesene Zeile: " + zeile);
					boolean aMap = zeile.contains("Map");
					boolean aBorder = zeile.contains("Border");
					boolean aTank = zeile.contains("Tank");
					boolean aTower = zeile.contains("Tower");
					boolean aWall = zeile.contains("Wall");
					boolean aShot = zeile.contains("Shot");
					boolean aPickup = zeile.contains("Pickup");
					boolean aExplosion = zeile.contains("Explosion");
					boolean aMine = zeile.contains("Mine");
					boolean aScattershot = zeile.contains("Scattershot");
					
					String delimiter = " ";
					String[] temp = zeile.split(delimiter);
					  
					if( (aMap||aBorder||aTank||aTower||aWall||aShot||aPickup||aExplosion||aMine||aScattershot) == false ){
						System.out.println("Beim Parsen von Objekt "+temp[0]+" trat ein fehler auf dieses scheint dem Parser nicht bekannt zu sein");					
					}
					
			    	float strength=0, speed=0, rotate=0, scale=0, x=0, y=0;
			    	int maxlife=0, life = 0, maxshot, shot;

			    	
			    	/*
			    	 Class<? extends TanksObjects> myclass = (Class<? extends TanksObjects>) Class.forName(temp[0]);
			    	 
						    	
			    	if(Debug.isdebug(this)){
			    	System.out.println("CanonicalName "	+ myclass.getCanonicalName());
					System.out.println("SimpleName "	+ myclass.getSimpleName());
					System.out.println("Name "			+ myclass.getName());
			    	}
			    	
					
					TanksObjects parse_entity = myclass.newInstance();
					*/
					
					

						    	//Tank mytank = (Tank) myclass.newInstance();
						    	
								//Bild Hinzufügen
						    	//parse_entity.addComponent(new ImageRenderComponent(Global.getImage(temp[1])));
						    	
						    	/*
						    	try{
						    	parse_entity.addComponent(Global.getImage( temp[1] ) );
						    	}
						    	catch(Exception e){
						    	 	try{
								    	parse_entity.addComponent(Global.getAimation( temp[1] ) );
								    	}
						    	 	catch(Exception f){
						    	 		System.err.println("Bild oder Animation an stelle 0 wurde nicht gefunden");
						    	 	}
						    	}
						    	*/
						    	
						    	
						    	//Entity superb = (Entity) classz.newInstance(); //Vorraussetzung der Klassenname Existiert						    	
				
					TanksObjects tmp_entity = new TanksObjects("");
					
					
						if (aMap) {
							/**
							 * Map background actualMap nextMap maxDuration elapsedTime shots
							 * Map "/assets/background.png" "map00" "map01" 0 0 0						 * 
							 */
							//Variablen einlesen
							String image = temp[1].substring( temp[1].lastIndexOf('/')+1 , temp[1].lastIndexOf('"'));
							String actualMap = getToken(temp[2]);
							String nextMap = getToken(temp[3]);
							 
							tmp_entity = new Map("background");
							tmp_entity.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
					    	tmp_entity.setPicture(image); 					// Bildkomponente
					    	((Map) tmp_entity).setNextMap(nextMap);
					    	((Map) tmp_entity).setActualMap(actualMap);
					    	((Map) tmp_entity).setMaxDuration(new Integer(temp[4]));
					    	((Map) tmp_entity).setElapsedTime(new Integer(temp[5]));
					    	((Map) tmp_entity).setShots(new Integer(temp[6]));
						}
						
						if (aBorder) {
							/**
							 * Border x0 y0 width height
							 * Border 400 0 800 0
							 */
							tmp_entity = new TanksObjects("Border");
							tmp_entity.setPicture("thematrixer-net_stahlwand.jpg");
							tmp_entity.setPosition( new Vector2f(new Float(temp[1]), new Float(temp[2])) );
							tmp_entity.setSize(	new Vector2f(new Float(temp[3]),new Float(temp[4]))	);
						}						
						

						
						if( aTank || aTower ||aWall ){	
	//Werte einlesen
	//... speed rotation scale x y
		speed = new Float(temp[temp.length-5]); //14 - 6

		
		maxlife = new Integer(temp[2]);
		life = new Integer(temp[3]);
		maxshot = new Integer(temp[4]);
		shot = new Integer(temp[5]);
		strength = new Float(temp[6]);
		

		/**						Wird durch Default Abgedeckt
		 * 
		* Shot strength 		rotation	scale 	x 	y
		* 
		* Wall					maxlife life
		* 					 	rotation	scale	x	y
		* 
		* Tank name 			maxlife life
		* 						maxshot shot maxmmine mine
		* 						strength	speed
		* 						rotation 	scale 	x 	y
		* 
		* Tower 				maxlife life maxshot shot
		* 						strength 	speed
		* 						rotation	scale	x	y
		*/
		
		
		if (aTank) {
							/**
							 * Tank name maxlife life maxshot shot maxmmine mine strength speed rotation scale x y
							 * Tank "Player1" 1000 1000 10 10 3 3 30 0 10 300 200
							 * Tank "Opponent1" 30 30 1 1 0 0 1 270 10 100 200
							 */
							tmp_entity = new Tank("a Tank"); //Objekt anlegen
					    	
					    	if (temp[1].equals("Player1")){
					    		tmp_entity.setPicture("tankPlayer.png");
					    	}
					    	if (temp[1].equals("Player2")){
					    		tmp_entity.setPicture("tankPlayer2.png");
					    	}
					    	if (temp[1].equals("Opponent1")){
					    		tmp_entity.setPicture("tankOppenent.png");
					    	}

					    	tmp_entity.setSpeed(speed);
						}
						
						if (aTower) {
						/**
						 * Tower maxlife life maxshot shot strength speed rotation scale x y
						 * maxlife: int - Maximal mögliche Lebenspunkte 
						 * Tower 30 30  1 1 10 5  75 10 400 400 
						 * 
						 */
							tmp_entity = new Tower("Tower");
							tmp_entity.setPicture("flac.png");
					    	tmp_entity.setSpeed(speed);
							}
						}
						if (aWall) {
						/**
						 * Wall maxlife life rotation scale x y
						 * Wall 30 30 0 10 25 25
						 * Wall 100 100 0 10 100 100
						 * */
							tmp_entity = new Border("Wall");
							((Border) tmp_entity).setMaxLife(new Integer(temp[1]));
							((Border) tmp_entity).setLife(new Integer(temp[2]));
						}
						if (aShot) {
						/**						Wird durch Default Abgedeckt
						* Shot strength 		rotation	scale 	x 	y
						* Shot	1				5			14		292	132
						* Shot 	5				280			10		50	50
						*/
							tmp_entity = new Shot("a Bullet");
							tmp_entity.setStrength(new Integer(temp[1])); //strength
						}
						if (aPickup) {
						/**
						* Pickup "Healthpack" 10 0 30 100 100
						* Pickup "Ammopack" 10 0 30 200 100
						*/
							String packtype = getToken(temp[1]);
							tmp_entity = new Pickup(packtype);
							
							if (packtype=="Healthpack")
								((Pickup) tmp_entity).addHealth();
							if (packtype=="Ammopack")
								((Pickup) tmp_entity).addMuniPack();
							if (packtype=="Toolpack")
								((Pickup) tmp_entity).addToolPack();
						}
						if (aExplosion) {
						/**
						 * Explosion width height speed x y
						 * Explosion 25 25 1 10 10
						 */
							tmp_entity = new TanksObjects("Explosion");
							tmp_entity.addComponent(Global.getAimation("expl"));
						}
						if (aMine) {
						/**
						 * Mine 60 20 303 168
						 * Mine 40 30 112 30
						 */
							tmp_entity = new Mine("Mine");
							tmp_entity.setSize(new Vector2f( new Integer(temp[1]), new Integer( temp[2] ) ));
						}
						if (aScattershot) {
							/**
							 * Scattershot 10 5 231 10 112 372
							 * */
						}
						
						if( aTank || aTower || aWall || aMine || aShot ){
							if(! aMine ){
							//Set Rotation
							rotate = new Float(temp[temp.length-4]);
							tmp_entity.setRotation(rotate);
							//Set Scale
							scale = new Integer(temp[temp.length-3]);
						    tmp_entity.setScale(scale);
							}
							
							//Set Possition
							x = new Integer(temp[temp.length-2]); //x Possition an stelle 3
							y = new Integer(temp[temp.length-1]); //y Possition an stelle 4
						    tmp_entity.setPosition(new Vector2f(x,y)); // Position Setzen
						}
						
						// Temp-Entitaet an StateBasedEntityManager uebergeben
				    	StateBasedEntityManager.getInstance().addEntity(Tanks.GAMEPLAYSTATE, tmp_entity);
				    	
						//Zurücksetzen aller Boolischen Variablen
						aMap = false; 
						aBorder = false;
						aTank = false;
						aTower = false;
						aWall = false;
						aShot = false;
						aPickup = false;
						aExplosion = false;
						aMine = false;
						aScattershot = false;							 
					  }}
			in.close();
		
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

