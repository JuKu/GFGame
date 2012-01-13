package net.generationfuture.game;

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
    private int width = 40;
    private int height = 40;
    
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
        menuItems[MenuItemCounter].setMenuItemPosition(this.x + (MenuItemCounter * width) + 10, (menu_reihe * height) + 10);
        
        MenuItemCounter++;
    }
    
    public void removeAll () {
        
        for (int i = 0; i <= 10; i++) {
            menuItems[i] = null;
            MenuItemCounter = 0;
        }
        
    }
    
}
