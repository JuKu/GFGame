package net.generationfuture.game;

import objects.Tree1;
import objects.Object;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ObjectManager {
    
    private Config config;
    private Player player;
    private Object objects[];
    private Image gras1;
    private Object objects_2[];//2. Grafik-Ebene
    private Object objects_3[];//3. Grafik-Ebene
    
    Image tree1_picture1;
    Image tree1_picture2;
    Image tree1_picture3;
    Image tree1_picture4;
    
    private int width = 200;
    private int height = 200;
    
    private int grafik_ebenen = 3;
    
    private Object objekte[][][];
    
    public ObjectManager (Config config, Player player) throws SlickException {
        this.config = config;
        this.player = player;
        objects = new Object[100];
        objekte = new Object[grafik_ebenen][width][height];
        
        initObjects();
    }
    
    public final void initObjects () throws SlickException {
        tree1_picture1 = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        gras1 = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        
        objects[0] = new Tree1(200, 200, this.tree1_picture1, "Baum1");
        objects[1] = new Tree1(200 + 128, 200, this.tree1_picture1, "Baum2");
        
        objekte[2][1][1] = objects[0];
        objects_2 = new Object[100];
        
        objects_3 = new Object[100];
        //objects_3[0] = new Tree1(180, 210, this.tree1_picture1);
        
        if (this.tree1_picture1 == null) {
            System.err.println("NullPointerException.");
        }
    }
    
    public void createObjects () {
        //
    }
    
    public Boolean isMouseOver (int mouse_x, int mouse_y, Boolean isMouseMoved) {
        
        /*********************************
        * 
        * Teste, ob Maus über Object "gefahren" wurde.
        * 
        ********************************/
            
            if (!isMouseMoved) {
            
                for (int i_ = 0; i_ < objects.length; i_++) {

                    Boolean isClicked;

                    if (objects[i_] != null) {
                        objects[i_].mouseMoved(mouse_x, mouse_y);
                    }

                }
            
            }
        
        return isMouseMoved;
        
    }
    
    public Boolean isClicked (int mouse_x, int mouse_y, Boolean isClicked_) {
        
        /*********************************
        * 
        * Teste, ob Object angeklickt wurde.
        * 
        ********************************/
        
        if (!isClicked_) {
            
            for (int i_ = 0; i_ < objects.length; i_++) {
                
            Boolean isClicked;
            
            if (objects[i_] != null) {
                isClicked = objects[i_].isClicked(mouse_x, mouse_y);
                if (isClicked) { System.out.println("Object[" + i_ + "] isClicked."); isClicked_ = true; }
            }
            
            }
        
        }
        
        return isClicked_;
        
    }
    
    public void paintObjectsLayer1 (Graphics g,double x,double y,boolean front) {
        
        paintObjects(g,x,y,front);
        
    }
    
    public void paintObjectsLayer2 (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects_2.length; i++) {
            
            if (objects_2[i] != null&&((front&&objects_2[i].getY()<=y)||(!front&&objects_2[i].getY()>y))) {
                objects_2[i].paint(g,x,y);
            }
            
        }
        
    }
    
    public void paintObjectsLayer3 (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects_3.length; i++) {
            
            if (objects_3[i] != null&&((front&&objects_3[i].getY()<=y)||(!front&&objects_3[i].getY()>y))) {
                objects_3[i].paint(g,x,y);
            }
            
        }
        
    }
    
    public void paintObjects (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null&&((front&&objects[i].getY()>=y)||(!front&&objects[i].getY()<y))) {
                objects[i].paint(g,x,y);
            }
            
        }
        
    }
    
    public void paintObjectsBehindPlayer(Graphics g,double x,double y) {
        paintObjectsLayer3(g, x, y, false);
        paintObjectsLayer2(g, x, y, false);
        paintObjectsLayer1(g, x, y, false);
    }
    
    public void paintObjectsOverPlayer(Graphics g,double x,double y) {
        paintObjectsLayer3(g, x, y, true);
        paintObjectsLayer2(g, x, y, true);
        paintObjectsLayer1(g, x, y, true);
    }
    
    public void paintObjectMenu (Graphics g) {
        
        /********************************************
         * 
         * Object-Menu
         * 
         *******************************************/
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].paintMenu(g);
            }
            
        }
        
    }
    
}
