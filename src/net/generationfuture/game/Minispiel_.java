package net.generationfuture.game;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public abstract class Minispiel_ {
    
    protected Image background;
    protected Image mouse;
    protected int x;
    protected int y;
    protected int mouse_x = 10;
    protected int mouse_y = 10;
    protected Boolean enable_mouse = true;
    protected Boolean enable_keys = true;
    protected Boolean is_background = true;
    protected Boolean is_mouse = true;
    
    public void paint (Graphics g) {
        
        if (is_background) {
            background.draw(1, 1);
        }
        
        if (is_mouse) {
            mouse.draw(mouse_x, mouse_y);
        }
        
    }
    
    public void enableMouse (Boolean enableMouse) {
        this.enable_mouse = enableMouse;
    }
    
    public void setMouse (int x, int y) {
        this.mouse_x = x;
        this.mouse_y = y;
    }
    
    public int getMouseX () {
        return this.mouse_x;
    }
    
    public int getMouseY () {
        return this.mouse_y;
    }
    
}