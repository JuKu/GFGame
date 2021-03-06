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
    private int menu_x_position = 200;
    private int menu_y_position = 140;
    
    private int menu_width_ = 60;
    private int menu_height_ = 50;
    
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
        
        //this.addMenu("test", "test");
        //this.addMenu("test2", "test2");
        
    }
    
    public void paint (Graphics g, double xp, double yp) {
        
        if (isShown) {
            
        int menu_x = menu_x_position;//200;
        int menu_y = menu_y_position;//140;
    
        int menu_width = this.menu_width_;//60;
        
        for (int i = 0; i < menu_counter; i++) {
            
            menu_width = 60 + (menu[i][0].length() * 5);
            Boolean isMouseOver = mouseOver[i];
            
            g.setColor(Color.white);
            g.fillRoundRect(menu_x - (int) xp, menu_y - (int) yp, menu_width, 20, 50, 50);
            
            int menu_x_ = menu_x + 2;
            int menu_y_ = menu_y + 2;
            
            if (isMouseOver) {
                g.setColor(Color.blue);
                g.fillRoundRect(menu_x - (int) xp, menu_y - (int) yp, menu_width, 20, 50, 50);
            } else {
                g.setColor(Color.blue);
                g.fillRoundRect(menu_x_ - (int) xp, menu_y_ - (int) yp, menu_width, 20, 50, 50);
            }
            
            g.setColor(Color.white);
            g.drawString(menu[i][0] + "", menu_x_ + 10 - (int) xp, menu_y_ - (int) yp);
            
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
    
    public void showMenu (Boolean show) {
        isShown = show;
    }
    
    public Boolean mouseMoved (int mouse_x, int mouse_y) {
        
        Boolean mouseMoved = false;
        
        for (int i = 0; i < 11; i++) {
            
            if (menu[i][0] != null) {
            
            int menu_width = 60 + (menu[i][0].length() * 5);
            
            if (mouse_x > menu_x_position + 2 && mouse_x < menu_x_position + 2 + menu_width) {
                
                if (mouse_y > menu_y_position + (i * 25) - 25 && mouse_y < menu_y_position + (i * 25) - 25 + menu_height_ - 2) {
                    //System.out.println("Menu clicked. (" + i + ")");
                    mouseMoved = true;
                    mouseOver(i);
                    break;
                }
                
                //System.out.println("Menu clicked.");
                
            }
            
        }
        
    }
        
    if (!mouseMoved) {
        
        for (int i = 0; i < 10; i++) {
            mouseOver[i] = false;
        }
        
    }
        
    return mouseMoved;
        
    }
    
    public void mouseOver (int menu) {
        
        for (int i = 0; i < 10; i++) {
            mouseOver[i] = false;
        }
        
        mouseOver[menu] = true;
        
    }
    
    public Boolean isClicked (int mouse_x, int mouse_y) {
        
        for (int i = 0; i < 11; i++) {
            
            if (menu[i][0] != null) {
            
            int menu_width = 60 + (menu[i][0].length() * 5);
            
            if (mouse_x > menu_x_position + 2 && mouse_x < menu_x_position + 2 + menu_width) {
                
                if (mouse_y > menu_y_position + (i * 25) - 25 && mouse_y < menu_y_position + (i * 25) - 25 + menu_height_ - 2) {
                    //System.out.println("Menu clicked. (" + i + ")");
                    
                    object.actionPerformed(menu[i][1]);
                    break;
                }
                
                //System.out.println("Menu clicked.");
                
            }
            
            }
            
        }
        
        return false;
        
    }
    
}
