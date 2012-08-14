package de.tu_darmstadt.gdi1.tanks.logic;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.entity.Entity;

public class FarbigeStatusAnzeige extends Entity{

	int posx, posy;
	int max = 100, min = 0;
	int Value;
	public static Color color;
	
    public FarbigeStatusAnzeige(String id) {
    		super(id);
    }
    

	/*
	Graphics g = new Graphics();
	g.setColor(color);
	g.fillRect(posx,posy,100,1);
	g.fill(bar);
	g.draw(bar);
	g.flush();//IMPORTANT!!!
	*/

	
    public Color getColor(int value){
    	//********************************+
    			float min=0, max=50;
    			
    	        int rot = 0, gruen = 255;		//Ausgangs Farbwerte 
    	        float faktor = 255f / max; 		//Verschiebungsfaktor der Farbe pro Wert
    	/*
    	RGB (0; 255; 0) gibt 65280 zurück, was Grün darstellt.
    	RGB (255; 255; 0) gibt 65280 zurück, was Gelb darstellt.
    	RGB (255; 0; 0) gibt 16711680 zurück, was Rot darstellt.
    	*/
    	        
    	        //Grün -> Gelb durch subtraktion der rot werte

    	            rot = (int) (255 - ((value*faktor)*0.1)); //5

    	        //Gelb -> Rot durch subtraktion der grünwerte
    	            if(rot>=200){
    	            	gruen = (int) ((value*faktor)*1.5);
    	            	System.out.println("rot");
    	            }

    	            //System.out.println("RGB: r/"+rot+" g/"+gruen+" b/"+ 0);
    	            
    	            //color should be between 0 and 255
    	            rot = rot > 255 ? 255 : rot;
    	            rot = rot < 0 ? 0 : rot; 
    	            gruen = gruen > 255 ? 255 : gruen; 
    	            gruen = gruen < 0 ? 0 : gruen;

    	           return (new Color(rot, gruen, 0));
    }
} 