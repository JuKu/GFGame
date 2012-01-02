package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ObjectMenu {
    
    protected String menu[][];
    protected int menu_counter = 0;
    
    protected int menu_pages_counter = 0;
    protected Boolean isShown = false;
    
    Boolean mouseOver[];
    
    public ObjectMenu () {
        mouseOver = new Boolean[10];
        mouseOver[0] = false;
        mouseOver[1] = false;
        mouseOver[2] = false;
        mouseOver[3] = false;
        mouseOver[4] = false;
        mouseOver[5] = false;
        mouseOver[6] = false;
        mouseOver[7] = false;
        mouseOver[8] = false;
        mouseOver[9] = false;
    }
    
    public void paint (Graphics g) {
        
        //menu_pages_counter = menu_counter / 8;
        
        /*g.setColor(Color.blue);
        g.fillRoundRect(hunger_anzeige_x, hunger_anzeige_y, hunger_length, 20, 50, 50);
        
        if (player.isHungry()) {
            g.setColor(Color.red);
        } else if (player.getHunger() < 40) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.green);
        }
        
        if (player.getHunger() <= 0) {
            player.addHunger(1);
        }
        
        g.fillRoundRect(hunger_anzeige_x, hunger_anzeige_y, player.getHunger() * 2, 20, 50, 50);
        
        */g.setColor(Color.white);
        //g.drawString("Hunger", 10 + 30, 50);
        
        for (int i = 0; i < menu_pages_counter; i++) {
            //
        }
        
        
    }
    
    public void addMenu (String title, String command) {
        menu[menu_counter][0] = title;
        menu[menu_counter][1] = command;
        
        menu_counter++;
    }
    
    public void removeAll () {
        
        for (int i = 0; i < menu_counter + 1; i++) {
            menu[i][0] = null;
            menu[i][1] = null;
        }
        
        menu_counter = 0;
        
    }
    
    public void actionPerformed (int x, int y) {
        //
    }
    
    public void mouseOver (int menu) {
        
        for (int i = 0; i < 11; i++) {
            mouseOver[i] = false;
        }
        
        mouseOver[menu] = true;
        
    }
    
}
