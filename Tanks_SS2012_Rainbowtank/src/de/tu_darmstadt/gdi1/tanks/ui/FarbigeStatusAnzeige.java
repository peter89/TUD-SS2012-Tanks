package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.Color;
import eea.engine.entity.Entity;

public class FarbigeStatusAnzeige extends Entity{

	int posx, posy;
	int max = 100, min = 0;
	int Value;
	
    public FarbigeStatusAnzeige(String id) {
    		super(id);
    }
	
    public Color getColor(int value){
    	//********************************+
    			float max=100;
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
    	            	gruen = (int) ((value*faktor)*0.9);
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