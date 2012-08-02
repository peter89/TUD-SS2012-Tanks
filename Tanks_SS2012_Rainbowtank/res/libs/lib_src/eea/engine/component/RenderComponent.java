package eea.engine.component;
 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 

/**
 * Abstraktes RenderEvent fuer alle Events, welche die Moeglichkeit haben
 * etwas zu zeichnen.
 * 
 * Hinweis: Diesem Event muss keine weitere Action hinzugefuegt werden.
 * Die Aktion 'Neu zeichnen' wird mit Hinzufuegen dieses Event's bereits ausgefuehrt. 
 */
public abstract class RenderComponent extends Component {
 
    public RenderComponent(String id)
    {
    	super(id);
    }
    public abstract Vector2f getSize();
 
    public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);
}
