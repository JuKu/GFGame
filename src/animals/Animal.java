package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Animal extends Object {
    
    protected int picture_counter = 0;
    
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
    
    protected Image walking_for1;
    protected Image walking_for2;
    protected Image walking_for3;
    protected Image walking_rfor4;
    protected Image walking_for5;
    protected Image walking_for6;
    protected Image walking_for7;
    protected Image walking_for8;
    
    protected Image walking_back1;
    protected Image walking_back2;
    protected Image walking_back3;
    protected Image walking_back4;
    protected Image walking_back5;
    protected Image walking_back6;
    protected Image walking_back7;
    protected Image walking_back8;
    
    protected Boolean walking_left = true;
    protected Boolean walking_right = false;
    protected Boolean walking_for = false;
    protected Boolean walking_back = false;
    protected Boolean standing_left = true;
    protected Boolean standing_right = false;
    protected Boolean standing_for = false;
    protected Boolean standing_back = false;
    
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
        this.standing_left = false;
        this.standing_right = false;
        this.standing_for = false;
        this.standing_back = false;
    }
    
    public void walkingRight () {
        this.walking_left = false;
        this.walking_right = true;
        this.walking_for = false;
        this.walking_back = false;
        this.standing_left = false;
        this.standing_right = false;
        this.standing_for = false;
        this.standing_back = false;
    }
    
    public void paint (Graphics g) {
        
        if (standing_left) {
            stopped_left.draw(x, y);
        } else if (standing_right) {
            stopped_right.draw(x, y);
        } else if (standing_for) {
            stopped_front.draw(x, y);
        } else if (standing_back) {
            stopped_back.draw(x, y);
        } else if (walking_left) {
            
            if (picture_counter >= 8) {
                picture_counter = 0;
                walking_left = false;
                
                standing_left = true;
            }
            
            picture_counter++;
            
            if (picture_counter == 0) {
                walking_left1.draw(x, y);
            } else if (picture_counter == 1) {
                walking_left2.draw(x, y);
            } else if (picture_counter == 2) {
                walking_left3.draw(x, y);
            } else if (picture_counter == 3) {
                walking_left4.draw(x, y);
            } else if (picture_counter == 4) {
                walking_left5.draw(x, y);
            } else if (picture_counter == 5) {
                walking_left6.draw(x, y);
            } else if (picture_counter == 6) {
                walking_left7.draw(x, y);
            } else if (picture_counter == 7) {
                walking_left8.draw(x, y);
            } else {
                walking_left1.draw(x, y);
            }
            
        } else if (walking_right) {
            
            if (picture_counter >= 8) {
                picture_counter = 0;
                walking_right = false;
                
                standing_right = true;
            }
            
            picture_counter++;
            
            if (picture_counter == 0) {
                walking_right1.draw(x, y);
            } else if (picture_counter == 1) {
                walking_right2.draw(x, y);
            } else if (picture_counter == 2) {
                walking_right3.draw(x, y);
            } else if (picture_counter == 3) {
                walking_right4.draw(x, y);
            } else if (picture_counter == 4) {
                walking_right5.draw(x, y);
            } else if (picture_counter == 5) {
                walking_right6.draw(x, y);
            } else if (picture_counter == 6) {
                walking_right7.draw(x, y);
            } else if (picture_counter == 7) {
                walking_right8.draw(x, y);
            } else {
                walking_right1.draw(x, y);
            }
            
        }
        
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
        
        walkingRight();
        
        if (walking_left) {
            x = x - 2;
        } else if (walking_right) {
            x = x + 2;
        } else if (walking_for) {
            y = y - 2;
        } else if (walking_back) {
            y = y + 2;
        }
        
    }
    
}
