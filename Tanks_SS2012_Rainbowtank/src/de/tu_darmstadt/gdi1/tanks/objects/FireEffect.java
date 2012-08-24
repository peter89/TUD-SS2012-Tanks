package de.tu_darmstadt.gdi1.tanks.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import eea.engine.entity.Entity;
import global.Global;

/**
 * A stock effect for fire usin the particle system
 *
 * @author kevin
 */
@SuppressWarnings("unused")
public class FireEffect extends Entity {
	ParticleSystem partikelSystem = new ParticleSystem("test");
	
  public FireEffect(String id) {

			super(id);

			try {
				partikelSystem = ParticleIO.loadConfiguredSystem(Global.getPath("skins")+"firewithsmoke.xml");
				}
			catch (Exception e) {
			 	System.err.println("Partikel System konnte nicht geladen werden");
			}
		 	partikelSystem.update(7);
		 	partikelSystem.render();
  }
  
 public void setPosition(Vector2f point){
	  partikelSystem.setPosition(point.x, point.y);
  }
}