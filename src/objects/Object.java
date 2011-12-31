package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Object {
    
    protected Image picture;
    
    protected int x = 1;
    protected int y = 1;
    
    public void createObject (Image picture) {
        this.picture = picture;
    }
    
    public void paint (Graphics g) {
        picture.draw(x, y);
    }
    
}
