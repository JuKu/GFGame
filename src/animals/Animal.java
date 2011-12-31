package animals;

import org.newdawn.slick.Image;

public abstract class Animal {
    
    Image walking_left1;
    Image walking_left2;
    Image walking_right1;
    Image walking_right2;
    int picture_counter = 0;
    
    protected Boolean walking_left = false;
    protected Boolean walking_right = false;
    
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
    
}
