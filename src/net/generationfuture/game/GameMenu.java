package net.generationfuture.game;

import org.newdawn.slick.Image;

public class GameMenu {
    
    private String menu_title = "";
    private GameMenuItem menuItems[];
    private int MenuCounter = 0;
    private int MenuItemCounter = 0;
    
    private Boolean isShown = false;
    private Image menu_image;
    
    public GameMenu (String menu_title) {
        this.menu_title = menu_title;
        menuItems = new GameMenuItem[10];
    }
    
    public void setVisible (Boolean visible) {
        this.isShown = visible;
    }
    
    public void addMenuItem (GameMenuItem menuItem) {
        menuItems[MenuItemCounter] = menuItem;
        MenuItemCounter++;
    }
    
    public void removeAll () {
        
        for (int i = 0; i <= 10; i++) {
            menuItems[i] = null;
            MenuItemCounter = 0;
        }
        
    }
    
}
