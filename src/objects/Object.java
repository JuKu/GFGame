package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Object {
    
    protected Image picture;
    protected Boolean isFokus = false;
    
    protected int x = 1;
    protected int y = 1;
    protected int height = 128;
    protected int width = 128;
    
    protected Boolean mouseMoved = false;
    protected String ObjectName = "";
    
    protected ObjectMenu objectmenu;
    protected int ObjectID = 0;
    
    protected int id = 0;
    public static int object_counter = 0;
    
    public void createObject (Image picture, int x, int y) {
        this.picture = picture;
        this.objectmenu = new ObjectMenu(this);
        
        this.x = x;
        this.y = y;
    }
    
    public void paint (Graphics g) {
        picture.draw(x, y);
        
        if (mouseMoved) {
            paintMouseOver(g);
        }
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
    
    public void mouseMoved (int x, int y) {
        
        if (x > this.x && x < this.x + width) {
            
            if (y > this.y && y < this.y + height) {
                mouseMoved = true;
            } else {
                mouseMoved = false;
            }
            
        } else {
            mouseMoved = false;
        }
        
    }
    
    public void paintMouseOver (Graphics g) {
        System.out.println("paintMouseMoved.");
        g.drawString("teststring", x, y);
        
        g.drawString("Object-ID: " + ObjectID, x, y + 15);
        g.drawString("ID: " + id, x, y + 40);
    }
    
    public void grow () {
        //Nur für Pflanzen
    }
    
    public Boolean isClicked (int x, int y) {
        
        if (x > this.x && x < this.x + width) {
            
            if (y > this.y && y < this.y + height) {
                return true;
            } else {
                return false;
            }
            
        } else {
            return false;
        }
        
    }
    
}
