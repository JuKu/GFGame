package net.generationfuture.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuManager {
    
    public MenuManager () {
        //
    }
    
    public static GameMenu getGameMenu (GameMenu game_menu) throws SlickException {
        
        GameMenuItem menuItem = new GameMenuItem("test", new Image("materials/buttons/Buttons1/base_button_code.png"));
        menuItem.setActionCommand("settings");
        
        game_menu.addMenuItem(menuItem);
        
        menuItem = new GameMenuItem("test", new Image("materials/buttons/Buttons1/base_button_bugs.png"));
        menuItem.setActionCommand("bugs");
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_1", new Image("materials/buttons/Buttons1/base_button_faq.png"));
        menuItem.setActionCommand("faq");
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_2", new Image("materials/buttons/Buttons1/base_button_irc.png"));
        menuItem.setActionCommand("irc");
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_Quest", new Image("materials/buttons/Buttons1/quest_button.png"));
        menuItem.setActionCommand("quest");
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_2", new Image("materials/buttons/Buttons1/base_button.png"));
        menuItem.setActionCommand("base_button");
        game_menu.addMenuItem(menuItem);
        
        return game_menu;
        
    }
    
}
