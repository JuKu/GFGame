package net.generationfuture.game;

import animals.Animal;
import animals.Rabbit;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AnimalManager {
    
    private Animal animals[];
    
    private Config config;
    private Player player;
    
    public AnimalManager (Config config, Player player) throws SlickException {
        this.config = config;
        this.player = player;
        
        initAnimals();
    }
    
    public final void initAnimals () throws SlickException {
        animals = new Animal[100];
        animals[0] = new Rabbit("Hase1", 100, 200);
    }
    
    public void createAnimals () {
        //
    }
    
    public void paintAnimals (Graphics g) {
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].paint(g);
            }
            
        }
        
    }
    
    public void paintAnimalMenu (Graphics g) {
        
        /********************************************
         * 
         * Animal-Menu
         * 
         *******************************************/
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].paintMenu(g);
            }
            
        }
        
    }
    
    public Animal[] getAnimals () {
        return animals;
    }
    
    public void scrollAnimals (int x, int y) {
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].scroll(x, y);
            }
            
        }
        
    }
    
    public Boolean isMouseOver (int mouse_x, int mouse_y, Boolean isMouseMoved) {
        
        /*********************************
             * 
             * Teste, ob Maus über Animal "gefahren" wurde.
             * 
             ********************************/
            
            if (!isMouseMoved) {
            
                for (int i_ = 0; i_ < animals.length; i_++) {

                    Boolean isClicked;

                    if (animals[i_] != null) {
                        Boolean isMouseMoved_ = animals[i_].mouseMoved(mouse_x, mouse_y);
                        if (isMouseMoved_) { isMouseMoved = true; }
                    }

                }
            
            }
        
        return isMouseMoved;
        
    }
    
    public void moveAnimals () {//Bewegt die Animals
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].move();
            }
            
        }
        
    }
    
}
