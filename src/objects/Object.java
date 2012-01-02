package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Object {
    
    protected Image picture;
    protected Boolean isFokus = false;
    
    protected int x = 1;
    protected int y = 1;
    
    protected ObjectMenu objectmenu;
    
    public void createObject (Image picture, int x, int y) {
        this.picture = picture;
        this.objectmenu = new ObjectMenu(this);
        
        this.x = x;
        this.y = y;
    }
    
    public void paint (Graphics g) {
        picture.draw(x, y);
    }
    
    public void scroll (int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    public void paintMenu (Graphics g) {
        objectmenu.paint(g);
    }
    
    public void actionPerformed (String command) {
        //
    }
    
}
