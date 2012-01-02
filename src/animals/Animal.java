package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Animal extends Object {
    
    int picture_counter = 0;
    
    protected Image stopped_left;
    protected Image stopped_right;
    protected Image stopped_front;
    protected Image stopped_back;
    protected Image sitting_left;
    protected Image sitting_right;
    protected Image sitting_front;
    protected Image sitting_back;
    
    protected Image walking_left1;
    protected Image walking_left2;
    protected Image walking_left3;
    protected Image walking_left4;
    protected Image walking_left5;
    protected Image walking_left6;
    protected Image walking_left7;
    protected Image walking_left8;
    
    protected Image walking_right1;
    protected Image walking_right2;
    protected Image walking_right3;
    protected Image walking_right4;
    protected Image walking_right5;
    protected Image walking_right6;
    protected Image walking_right7;
    protected Image walking_right8;
    
    protected Boolean walking_left = false;
    protected Boolean walking_right = false;
    protected Boolean walking_for = false;
    protected Boolean walking_back = false;
    
    protected int x = 50;
    protected int y = 50;
    
    protected int durst = 100;
    protected int hunger = 100;
    
    protected Boolean isIll = false;
    protected Boolean sitting = false;
    
    public Animal (int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
    }
    
    public void walkingLeft () {
        this.walking_left = true;
        this.walking_right = false;
        this.walking_for = false;
        this.walking_back = false;
    }
    
    public void walkingRight () {
        this.walking_left = false;
        this.walking_right = true;
        this.walking_for = false;
        this.walking_back = false;
    }
    
    public void paint (Graphics g) {
        stopped_left.draw(x, y);
    }
    
    public void scroll (int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    public void drink (int drink) {
        this.durst = this.durst + drink;
    }
    
    public void eat (int eat) {
        this.hunger = this.hunger + eat;
    }
    
    public void move () {
        
        if (walking_left) {
            x = x + 2;
        }
        
    }
    
}
