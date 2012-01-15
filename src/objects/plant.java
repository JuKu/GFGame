package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class plant extends Object {
    
    protected Image picture;
    
    protected Image[] growingi = new Image[8];
    
    protected Image[] tippingi = new Image[9];
    
    Boolean tipping = false;
    Boolean isShown = true;
    
    protected Boolean growing = false;
    protected int growing_counter = 0;
    
    protected int tipping_counter = 0;
    protected Boolean isTipping = false;
    
    public void createObject (String name, Image picture, int x, int y) {
        this.picture = picture;
        objectmenu = new ObjectMenu(this);
        this.x = x;
        this.y = y;
        
        this.id = ++plant.object_counter;
        this.name = name;
    }
    
    public void createObject (Image picture, Image growing1, Image growing2, Image growing3, Image growing4, Image growing5, Image growing6, Image growing7, Image growing8, int x, int y) {
        this.picture = picture;
        this.growingi[0] = growing1;
        this.growingi[1] = growing2;
        this.growingi[2] = growing3;
        this.growingi[3] = growing4;
        this.growingi[4] = growing5;
        this.growingi[5] = growing6;
        this.growingi[6] = growing7;
        this.growingi[7] = growing8;
        this.id = ++plant.object_counter;
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void grow () {
        
        growing_counter++;
        
        if (growing_counter >= 8) {
            growing_counter = 0;
            growing = false;
        } else {
            growing = true;
        }
        
    }
    
    public void tipping () {
        tipping = true;
    }
    
    @Override
    public void paint (Graphics g,double xp,double yp) {
        
        if (isShown) {
        
        if (growing) {
            
            if(growing_counter<8) {
                growingi[growing_counter].draw(x-(int)xp, y);
            } else if (growing_counter == 8) {
                picture.draw(x-(int)xp, y-(int)yp);
            }
            
        } else if (tipping) {
            
            if (!isTipping) {
            
            if(tipping_counter<9) {
                tippingi[tipping_counter].draw(x-(int)xp, y-(int)yp);
            } 
            
            if (tipping_counter >= 9) {
                tipping_counter = 0;
                //tipping = false;
                
                //isShown = false;
                isTipping = true;
            } else {
                tipping_counter++;
            }
            
            } else {
                tippingi[8].draw(x-(int)xp, y-(int)yp);
            }
            
        } else {
            picture.draw(x-(int)xp, y-(int)yp);
        }
        
        }
        
        if (mouseMoved) {
            paintMouseOver(g,xp,yp);
        }
        
    }
    
    @Override
    public void paintMouseOver (Graphics g,double xp,double yp) {
        //System.out.println("paintMouseMoved.");
        g.drawString("" + this.name, x-(int)xp, y-(int)yp);
        
        g.drawString("Object-ID: " + ObjectID, x-(int)xp, y + 15-(int)yp);
        g.drawString("ID: " + id, x-(int)xp, y + 40-(int)yp);
    }
    
    public void pick () {
        isTipping = false;
        isShown = false;
        
        tipping = false;
    }
    
}
