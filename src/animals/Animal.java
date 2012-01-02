package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Animal extends Object {
    
    Image walking_left1;
    Image walking_left2;
    Image walking_right1;
    Image walking_right2;
    int picture_counter = 0;
    
    protected Image stopped_left;
    protected Image stopped_right;
    protected Image stopped_front;
    protected Image stopped_back;
    
    protected Boolean walking_left = false;
    protected Boolean walking_right = false;
    
    protected int x = 50;
    protected int y = 50;
    
    public Animal (int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
    }
    
    public abstract void move ();
    public abstract void drink ();
    public abstract void eat ();
    public abstract void walking_left ();
    public abstract void walking_right ();
    
    public void walkingLeft (Boolean walking_left) {
        this.walking_left = walking_left;
        this.walking_right = !walking_left;
    }
    
    public void walkingRight (Boolean walking_right) {
        this.walking_right = walking_right;
        this.walking_left = !walking_right;
    }
    
    public void paint (Graphics g) {
        stopped_left.draw(x, y);
    }
    
}
