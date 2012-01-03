package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class plant extends Object {
    
    protected Image picture;
    
    protected Image growing1;
    protected Image growing2;
    protected Image growing3;
    protected Image growing4;
    protected Image growing5;
    protected Image growing6;
    protected Image growing7;
    protected Image growing8;
    
    protected Image tipping1;
    protected Image tipping2;
    protected Image tipping3;
    protected Image tipping4;
    protected Image tipping5;
    protected Image tipping6;
    protected Image tipping7;
    protected Image tipping8;
    protected Image tipping9;
    
    Boolean tipping = false;
    Boolean isShown = true;
    
    protected Boolean growing = false;
    protected int growing_counter = 0;
    
    protected int tipping_counter = 0;
    protected Boolean isTipping = false;
    
    @Override
    public void createObject (Image picture, int x, int y) {
        this.picture = picture;
        objectmenu = new ObjectMenu(this);
        this.x = x;
        this.y = y;
        
        this.id = ++plant.object_counter;
    }
    
    public void createObject (Image picture, Image growing1, Image growing2, Image growing3, Image growing4, Image growing5, Image growing6, Image growing7, Image growing8, int x, int y) {
        this.picture = picture;
        this.growing1 = growing1;
        this.growing2 = growing2;
        this.growing3 = growing3;
        this.growing4 = growing4;
        this.growing5 = growing5;
        this.growing6 = growing6;
        this.growing7 = growing7;
        this.growing8 = growing8;
        
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
    public void paint (Graphics g) {
        
        if (isShown) {
        
        if (growing) {
            
            if (growing_counter == 0) {
                growing1.draw(x, y);
            } else if (growing_counter == 1) {
                growing2.draw(x, y);
            } else if (growing_counter == 2) {
                growing3.draw(x, y);
            } else if (growing_counter == 3) {
                growing4.draw(x, y);
            } else if (growing_counter == 4) {
                growing5.draw(x, y);
            } else if (growing_counter == 5) {
                growing6.draw(x, y);
            } else if (growing_counter == 6) {
                growing7.draw(x, y);
            } else if (growing_counter == 7) {
                growing8.draw(x, y);
            } else if (growing_counter == 8) {
                picture.draw(x, y);
            }
            
        } else if (tipping) {
            
            if (!isTipping) {
            
            if (tipping_counter == 0) {
                tipping1.draw(x, y);
            } else if (tipping_counter == 1) {
                tipping2.draw(x, y);
            } else if (tipping_counter == 2) {
                tipping3.draw(x, y);
            } else if (tipping_counter == 3) {
                tipping4.draw(x, y);
            } else if (tipping_counter == 4) {
                tipping5.draw(x, y);
            } else if (tipping_counter == 5) {
                tipping6.draw(x, y);
            } else if (tipping_counter == 6) {
                tipping7.draw(x, y);
            } else if (tipping_counter == 7) {
                tipping8.draw(x, y);
            } else if (tipping_counter == 8) {
                tipping9.draw(x, y);
            } else if (tipping_counter == 9) {
                tipping9.draw(x, y);//picture.draw(x, y);
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
                tipping9.draw(x, y);
            }
            
        } else {
            picture.draw(x, y);
        }
        
        }
        
        if (mouseMoved) {
            paintMouseOver(g);
        }
        
    }
    
    public void pick () {
        isTipping = false;
        isShown = false;
        
        tipping = false;
    }
    
}
