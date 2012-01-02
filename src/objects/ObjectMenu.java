package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ObjectMenu {
    
    protected String menu[][];
    protected int menu_counter = 0;
    
    protected int menu_pages_counter = 0;
    protected Boolean isShown = false;
    
    Boolean mouseOver[];
    Object object;
    
    public ObjectMenu (Object object) {
        
        menu = new String[100][10];
        this.object = object;
        
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
        
        this.addMenu("test", "test");
        this.addMenu("test2", "test2");
        
    }
    
    public void paint (Graphics g) {
        
        if (isShown) {
        
        int menu_x = 100;
        int menu_y = 140;
        
        int menu_width = 60;
        
        for (int i = 0; i < menu_counter; i++) {
            
            menu_width = 60 + (menu[i][0].length() * 5);
            Boolean isMouseOver = mouseOver[i];
            
            g.setColor(Color.white);
            g.fillRoundRect(menu_x, menu_y, menu_width, 20, 50, 50);
            
            int menu_x_ = menu_x + 2;
            int menu_y_ = menu_y + 2;
            
            if (isMouseOver) {
                g.setColor(Color.blue);
                g.fillRoundRect(menu_x, menu_y, menu_width, 20, 50, 50);
            } else {
                g.setColor(Color.blue);
                g.fillRoundRect(menu_x_, menu_y_, menu_width, 20, 50, 50);
            }
            
            g.setColor(Color.white);
            g.drawString(menu[i][0] + "", menu_x_ + 10, menu_y_);
            
            menu_y = menu_y + 25;
            
            }
            
        }    
        
    }
    
    public final void addMenu (String title, String command) {
        /*if (menu_counter != 0) {
            menu_counter++;
        }*/
        
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
