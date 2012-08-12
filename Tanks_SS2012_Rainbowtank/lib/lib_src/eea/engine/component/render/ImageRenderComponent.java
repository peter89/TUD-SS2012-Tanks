package eea.engine.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.RenderComponent;

/**
 * Zeichne ein Bild neu.
 */
public class ImageRenderComponent extends RenderComponent {
	
	private Image image;

	/**
	 * Zeichne das Bild mit Identifier id und Bild image.
	 * 
	 * Hinweis: Diesem Event muss keine weitere Action hinzugefuegt werden.
	 * Die Aktion 'Neu zeichnen' wird mit Hinzufuegen dieses Event's bereits ausgefuehrt. 
	 * 
	 * @param String id - Identifier des Bilds
	 * @param Image image - Bild-Objekt
	 */
	public ImageRenderComponent(Image image) {
		super("ImageRenderComponent");
		this.image = image;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		float scale = owner.getScale();
		//image.draw(pos.x, pos.y, scale);
		//image.drawCentered(pos.x, pos.y);
		image.draw(pos.x-image.getWidth()/2.0f*scale, pos.y-image.getHeight()/2.0f*scale, image.getWidth()*scale,image.getHeight()*scale);
	}
	
	public Vector2f getSize(){
		return new Vector2f(image.getWidth()*owner.getScale(), image.getHeight()*owner.getScale());
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta){
		float scale = owner.getScale();
		image.setCenterOfRotation(image.getWidth()/2.0f*scale, image.getHeight()/2.0f*scale);
		image.rotate(owner.getRotation() - image.getRotation());
	}

}
