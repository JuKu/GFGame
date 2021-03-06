package objects;

import net.generationfuture.game.Items;
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
    protected double xp = 0;
    protected double yp = 0;
    
    protected ObjectMenu objectmenu;
    protected int ObjectID = 0;
    
    protected int id = 0;
    public static int object_counter = 0;
    
    protected String name;
    protected Boolean isClicked = false;
    
    protected Items items;
    
    public void createObject (Image picture, Items items, int x, int y) {
        this.picture = picture;
        this.objectmenu = new ObjectMenu(this);
        this.x = x;
        this.y = y;
        
        this.items = items;
    }
    
    public void paint (Graphics g,double xp,double yp) {
        picture.draw(x-(int)xp, y-(int)yp);
        
        this.xp = xp;
        this.yp = yp;
        
        if (mouseMoved) {
            paintMouseOver(g,xp,yp);
        }
    }
    
    public void scroll (int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    public void paintMenu (Graphics g, double xp, double yp) {
        objectmenu.paint(g, xp, yp);
    }
    
    public ObjectMenu getObjectMenu () {
        return objectmenu;
    }
    
    public void actionPerformed (String command) {
        System.out.println("actionPerformed command: " + command + ".");
    }
    
    public void mouseMoved (int x, int y) {
        
        if (x > this.x - (int) xp && x < this.x + width - (int) xp) {
            
            if (y > this.y - (int) yp && y < this.y + height - (int) yp) {
                mouseMoved = true;
            } else {
                mouseMoved = false;
            }
            
        } else {
            mouseMoved = false;
        }
        
    }
    
    public void paintMouseOver (Graphics g,double xp,double yp) {
        //System.out.println("paintMouseMoved.");
        g.drawString("teststring", x-(int)xp, y-(int)yp);
        
        g.drawString("Object-ID: " + ObjectID, x-(int)xp, y-(int)yp + 15);
        g.drawString("ID: " + id, x-(int)xp, y-(int)yp + 40);
    }
    
    public void grow () {
        //Nur für Pflanzen
    }
    
    public Boolean isClicked (int x, int y) {
        
        if (x > this.x && x < this.x + width) {
            
            if (y > this.y && y < this.y + height) {
                isClicked = true;
                return true;
            } else {
                isClicked = false;
                return false;
            }
            
        } else {
            isClicked = false;
            return false;
        }
        
    }
    
    public double getY () {
        return y;
    }
    
    public Image getImage() {
        return this.picture;
    }
    
}
