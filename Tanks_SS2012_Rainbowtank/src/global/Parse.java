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

import de.tu_darmstadt.gdi1.tanks.model.exceptions.SemanticException;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SyntaxException;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

import de.tu_darmstadt.gdi1.tanks.objects.*;

public class Parse {
	
	
	void saveMapToFile(File file){
	  	//TODO:
		//Fill List<Entity> with Entities from Tanks.GAMEPLAYSTATE
		List<Entity> entities = new ArrayList<Entity>();//Leere Entity Liste
		entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		//entities.parse >> Write to file
	}
	
	
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws FileNotFoundException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
	
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
			while ((zeile = in.readLine()) != null) {
				
				//Zeile enthält mehr wie ein zeichen...  ...und ist keine Komentarzeile
				if( zeile.length()>=1 && (! zeile.contains("#")) && (! zeile.contains("//")) ) {

					zeile = zeile.replaceAll("\t", " ");
					zeile = zeile.replaceAll("  ", " ");
					char[] data=zeile.toCharArray();
					//Lehrzeichen am anfang auswendig machen
					if (data[0]==' ') zeile = zeile.substring(1);
					
					System.out.println("Gelesene Zeile: " + zeile);

					boolean Map = zeile.contains("Map");
					boolean Border = zeile.contains("Border");
					boolean Tank = zeile.contains("Tank");
					boolean Tower = zeile.contains("Tower");
					boolean Wall = zeile.contains("Wall");
					boolean Shot = zeile.contains("Shot");
					boolean Pickup = zeile.contains("Pickup");
					boolean Explosion = zeile.contains("Explosion");
					boolean Mine = zeile.contains("Mine");
					boolean Scattershot = zeile.contains("Scattershot");
					
					
					  String delimiter = " ";
					  String[] temp = zeile.split(delimiter);
					  
					  for(int i =0; i < temp.length ; i++){
					   // System.out.println(temp[i]);
					  
							  
						    	// Entitiy Erstellen
						    	Entity parse_entity = new Entity(temp[1]);

						    	// Setze Bildkomponente
						    	parse_entity.addComponent(new ImageRenderComponent(Global.getImage(temp[2])));
						    	
						    	int x = Integer.parseInt(temp[3]); //x Possition an stelle 3
						    	int y = Integer.parseInt(temp[4]); //y Possition an stelle 4
						    	
						    	parse_entity.setPosition(new Vector2f(x,y)); // Setze Possition


						    	
							    // Hintergrund-Entitaet an StateBasedEntityManager uebergeben
						    	//entityManager.addEntity(stateID, background);
						    	
						    	/*
							    void printClassName(Object obj) {
							        System.out.println("The class of " + obj +
							                           " is " + obj.getClass().getName());
							    }
							    */
							  
						    	/* Zweiter Ansatz Complett Dynamisch */
						    	//Class<? extends Entity>[] entityArg = new Class[entitySize]; //array mit dynamischen entityss
						    	
						    	Class<? extends Entity> myclass = (Class<? extends Entity>) Class.forName(temp[0]);
							    
							  // Suche Klasse nach Klassename
						    	
						    	myclass.asSubclass(Class myclass);
							  
							  test (Class<? extends Entity>) asSubclass( (Class<U> clazz) );
							  
							  /*
							  public <U> Class<? extends U> asSubclass(Class<U> clazz)
							  Casts this Class object to represent a subclass of the class represented by the specified class object. Checks that that the cast is valid, and throws a ClassCastException if it is not. If this method succeeds, it always returns a reference to this class object. 
							  This method is useful when a client needs to "narrow" the type of a Class object to pass it to an API that restricts the Class objects that it is willing to accept. A cast would generate a compile-time warning, as the correctness of the cast could not be checked at runtime (because generic types are implemented by erasure).

							  Returns:
							  this Class object, cast to represent a subclass of the specified class object. 
							  Throws: 
							  ClassCastException - if this Class object does not represent a subclass of the specified class (here "subclass" includes the class itself).
							  Since: 
							  1.5
							  */
							  classz.asSubclass(class eea.engine.entity.Entity);
							 
							  
							  Entity superb = (Entity) classz.newInstance(); //Vorraussetzung der Klassenname Existiert
							  
							  //Bild Hinzufügen
							  superb.addComponent(new ImageRenderComponent(Global.getImage(temp[1])));
							  
							  //Füge Aktuelle Entity hinzu
							  StateBasedEntityManager.getInstance().addEntity(Tanks.GAMEPLAYSTATE, superb);
							  
					  }
					  
					  
					  
				}
			}
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

