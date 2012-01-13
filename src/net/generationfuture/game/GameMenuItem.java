package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GameMenuItem {
    
    private String menu_title;
    private Image image;
    private int x = 10;
    private int y = 10;
    private int mouseOverX = 10;
    private int mouseOverY = 10;
    private int width = 40;
    private int height = 40;
    
    private Boolean isMouseOver = false;
    private Boolean isShown = true;
    
    private Color color_filter = null;
    
    public GameMenuItem (String MenuTitle, Image image) {
        this.menu_title = MenuTitle;
        this.image = image;
    }
    
    public void setVisible (Boolean visible) {
        isShown = visible;
    }
    
    public void setMenuItemPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setMouseOver (Boolean mouseOver) {
        this.isMouseOver = mouseOver;
    }
    
    public void actionPerformed () {
        //
    }
    
    public Boolean isMouseClicked (int mouse_x, int mouse_y) {
        
        if (mouse_x > x && mouse_x < x + width + mouseOverX) {
            
            if (mouse_y > y && mouse_y < y + height + mouseOverY) {
                return true;
            } else {
                return false;
            }
            
        } else {
            
            return false;
            
        }
        
    }
    
    public void paint (Graphics g) {
        
        if (isMouseOver) {
            
            if (color_filter != null) {
                image.draw(x + mouseOverX, y + mouseOverY, color_filter);
            } else {
                image.draw(x + mouseOverX, y + mouseOverY);
            }
            
        } else {
            
            if (color_filter != null) {
                image.draw(x, y, color_filter);
            } else {
                image.draw(x, y);
            }
            
        }
        
    }
    
}
