package eea.engine.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;
import eea.engine.component.RenderComponent;

/**
 * Eine Entity ist ein Objekt, welches sich in einem Fenster anzeigen laesst.
 *
 */
public class Entity {
	
	private boolean visible;		// Soll die Entity sichtbar sein?
    private String id;				// Name der Entity
    private Vector2f position;		// Position der Entity
    private float scale;			// Groesse der Entity
    private float rotation;		// Ausrichtung der Entity
    private boolean passable;		// Soll die Entity passierbar sein?
 
    /**
     * @return Ist das Objekt passierbar?
     */
    public boolean isPassable() {
		return passable;
	}

    /**
     * 
     * @param passable Ist die Entity passierbar?
     */
	public void setPacable(boolean pacable) {
		this.passable = pacable;
	}

	/**
     * Zugehoerige grafische Objekt. Kann auch eine Animation sein!
     */
    private RenderComponent renderComponent;
 
    /**
     * Liste der Events die bei jedem update abgearbeitet werden soll
     */
    private CopyOnWriteArrayList<Component> events;
    
    
    private Vector2f size;
 
    /**
     * Dies erzeugt ein Entity-Objekt. Bis zu einer manuellen Aenderung
     * gilt fuer die Entity:
     * - Position bei (x,y) = (0,0)
     * - keine Skalierung
     * - keine Drehung
     * - sichtbar
     * - Ueberschneidung mit anderen Enity-Objekten ist moeglich!
     * @param id Name der Entity
     */
    public Entity(String id)
    {
        this.id = id;
 
        events = new CopyOnWriteArrayList<Component>();
 
        position = new Vector2f(0,0);
        scale = 1;
        rotation = 0;
        visible = true;
        passable = true;
    }
    
    public void setSize(Vector2f size){
    	this.size = size;
    }
 
    /**
     * Fuegt dieser Entity ein Event hinzu. Tritt das Event ein, so werden
     * alle auf diesem Event registrierten Action's ausgefuehrt.
     * @param event Das für diese Entity relevante Ereignis
     */
    public void addComponent(Component event)
    {
        if(RenderComponent.class.isInstance(event))
            renderComponent = (RenderComponent)event;
 
        event.setOwnerEntity(this);
        events.add(event);
    }
    
    public void removeComponent(Component comp){
    	events.remove(comp);
    }
    
    public void removeComponent(String id){
    	for(int i = 0; i < events.size() ; i++){
    		if(events.get(i).getId().equalsIgnoreCase(id))
    			events.remove(i);
    	}
    }
 
    /**
     * Gibt ein Event zurück mit gewaehltem Tag
     * @param id Name des Events
     * @return Das Event Objekt oder null wenn nichts gefunden wurde
     */
    public Component getEvent(String id)
    {
        for(Component comp : events)
	{
	    if ( comp.getId().equalsIgnoreCase(id) )
	        return comp;
	}
 
        return null;
    }
 
    /**
     * Die Position der Entity
     * @return Position in Form eines Vektors
     */
    public Vector2f getPosition()
    {
    	return position;
    }
 
    /**
     * Groeße der Entity
     * @return Skallierung in form eines floats
     */
    public float getScale()
    {
    	return scale;
    }
 
    /**
     * Ausrichtung
     * @return float
     */
    public float getRotation()
    {
    	return rotation;
    }
 
    /**
     * Name der Entity
     * @return String
     */
    public String getId()
    {
    	return id;
    }
 
    /**
     * Aktualisiere die Position der Entity
     * @param position
     */
    public void setPosition(Vector2f position) {
    	this.position = position;
    }
 
    /**
     * Aktualisiere die Ausrichtung der Entity
     * @param rotate
     */
    public void setRotation(float rotate) {
        rotation = rotate;
    }
 
    /**
     * Aktualisiere die Skallierung der Entity
     * @param scale
     */
    public void setScale(float scale) {
    	this.scale = scale;
    }
    
    /**
     * Gibt zurück ob die Entity sichtbar sein soll.
     * @return boolean
     */
    public boolean isVisible(){
    	return visible;
    }
    
    /**
     * Stellt die Sichtbarkeit der Entity ein
     * @param visible
     */
    public void setVisible(boolean visible){
    	this.visible = visible;
    }
 
    /**
     * Fuehre die update Funktion bei allen Events aus
     * @param gc
     * @param sb
     * @param delta
     */
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        for(Component event : events)
        {
            event.update(gc, sb, delta);
        }
    }
 
    /**
     * Fuehre die render Funktion vom Zeichenbaren Objekt aus falls vorhanden und 
     * auf sichtbar gestellt
     * @param gc
     * @param sb
     * @param gr
     */
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
    {
        if(renderComponent != null && isVisible()){
            renderComponent.render(gc, sb, gr);
        }
      //gr.draw(getShape());
    }
    
    public boolean colides(Entity OtherEntity){
    	
    	if(this.id.equals(OtherEntity.getId())) return false;
    	
    	/*
    	Rectangle r1 = new Rectangle(position.x-getSize().x/2.0f, position.y-getSize().y/2.0f, getSize().x, getSize().y);
    	Rectangle r2 = new Rectangle(OtherEntity.getPosition().x-OtherEntity.getSize().x/2.0f, OtherEntity.getPosition().y-OtherEntity.getSize().y/2.0f, OtherEntity.getSize().x, OtherEntity.getSize().y);
    	
    	return r1.intersects(r2);
    	*/
    	return getShape().intersects(OtherEntity.getShape());
    	
    }
    
    public Shape getShape(){
    	
    	//Erstelle ein neues Rechteck
    	Rectangle rec = new Rectangle(position.x-getSize().x/2.0f, position.y-getSize().y/2.0f, getSize().x, getSize().y);
    	
    	//Drehe das Rechteck
    	//Shape shape = rec.transform(Transform.createRotateTransform(rotation, position.x, position.y));
    	Shape shape = rec.transform(Transform.createRotateTransform((float)Math.toRadians(rotation), position.x, position.y));
    	return shape;
    }
    
    
    
    

	public Vector2f getSize() {
		if(size != null)	return size;
		if(renderComponent == null) return new Vector2f(0,0);
		return renderComponent.getSize();
	}
}
