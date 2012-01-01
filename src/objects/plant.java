package objects;

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
    
    protected Boolean growing = false;
    protected int growing_counter = 0;
    
    @Override
    public void createObject (Image picture, int x, int y) {
        this.picture = picture;
        
        this.x = x;
        this.y = y;
    }
    
    public void grow () {
        
        growing_counter++;
        
        if (growing_counter >= 8) {
            growing_counter = 0;
            growing = false;
        } else {
            growing = true;
        }
        
    }
    
}
