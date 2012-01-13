package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GameMenu {
    
    private String menu_title = "";
    private GameMenuItem menuItems[];
    private int MenuCounter = 0;
    private int MenuItemCounter = 0;
    
    private Boolean isShown = false;
    private Image menu_image;
    private int x = 10;
    private int y = 10;
    private int width = 64;
    private int height = 64;
    
    private int menu_reihe = 1;
    
    public GameMenu (String menu_title, Image image, int x, int y) {
        this.menu_title = menu_title;
        
        menuItems = new GameMenuItem[10];
        menu_image = image;
        this.x = x;
        this.y = y;
    }
    
    public void setMenuImage (Image image) {
        menu_image = image;
    }
    
    public void removeMenuImage () {
        menu_image = null;
    }
    
    public void setVisible (Boolean visible) {
        this.isShown = visible;
    }
    
    public void addMenuItem (GameMenuItem menuItem) {
        menuItems[MenuItemCounter] = menuItem;
        menuItems[MenuItemCounter].setMenuItemPosition(this.x + (MenuItemCounter * width) + 10, (menu_reihe * height) - height + 20);
        
        MenuItemCounter++;
    }
    
    public void removeAll () {
        
        for (int i = 0; i <= 10; i++) {
            menuItems[i] = null;
            MenuItemCounter = 0;
        }
        
    }
    
    public GameMenuItem mouseOver (int mouse_x, int mouse_y) {
        
        Boolean isMouseOver = false;
        GameMenuItem menuItem = null;
        
        for (int i = 0; i <= MenuItemCounter; i++) {
            
            if (menuItems[i] != null && !isMouseOver) {
                
                isMouseOver = menuItems[i].isMouseClicked(mouse_x, mouse_y);
                
                if (isMouseOver) {
                    menuItem = menuItems[i];
                } else {
                    menuItems[i].setMouseOver(false);
                }
                
            }
            
        }
        
        if (isMouseOver && menuItem != null) {
            return menuItem;
        } else {
            return null;
        }
        
    }
    
    public void paint (Graphics g) {
        /*g.setColor(Color.white);
        g.fillRoundRect(x, y, (width * (MenuItemCounter + 10) + 10), this.menu_reihe * 50, 50);
        g.setColor(Color.blue);
        g.drawString("" + this.menu_title, x + 10, y + 6);*/
        
        for (int i = 0; i <= MenuItemCounter; i++) {
            
            if (menuItems[i] != null) {
                menuItems[i].paint(g);
            }
            
        }
    }
    
}
