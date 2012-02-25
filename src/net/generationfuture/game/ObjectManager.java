package net.generationfuture.game;

import objects.Fireplace;
import objects.Tree1;
import objects.Object;
import objects.ObjectMenu;
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
    
    Image[] tree1_picture = new Image[4];
    
    private int width = 200;
    private int height = 200;
    
    private int grafik_ebenen = 3;
    private ObjectMenu objectmenu = null;
    
    private Object objekte[][][];
    protected Image image;
    
    protected Items items;
    
    public ObjectManager (Config config, Player player, Items items) throws SlickException {
        this.config = config;
        this.player = player;
        objects = new Object[100];
        objekte = new Object[grafik_ebenen][width][height];
        this.items = items;
        initObjects();
    }
    
    public final void initObjects () throws SlickException {
        tree1_picture[0] = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        gras1 = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        
        image =  new Image("materials/objects/fireplace/feuerstelle.bmp",new Color(106, 76, 48, 255));
        
        objects[0] = new Tree1(200, 200, this.tree1_picture[0], "Baum1");
        objects[1] = new Tree1(200 + 128, 200, this.tree1_picture[0], "Baum2");
        
        objects[2] = new Fireplace(200, 400, image, items, "Feuerstelle1");
        
        objekte[2][1][1] = objects[0];
        objects_2 = new Object[100];
        
        objects_3 = new Object[100];
        //objects_3[0] = new Tree1(180, 210, this.tree1_picture1);
        
        if (this.tree1_picture[0] == null) {
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
            
            int isObjectMenu = 0;
            
            for (int i_ = 0; i_ < objects.length; i_++) {
                
            Boolean isClicked;
            
            if (objects[i_] != null) {
                isClicked = objects[i_].isClicked(mouse_x, mouse_y);
                if (isClicked) {
                    System.out.println("Object[" + i_ + "] isClicked.");
                    isClicked_ = true;
                    objectmenu = objects[i_].getObjectMenu();
                    objects[i_].getObjectMenu().showMenu(true);
                    isObjectMenu = 1;
                } else {
                    
                    //if (objectmenu == null) {
                        objects[i_].getObjectMenu().showMenu(false);
                    //}
                    
                }
            }
            
            }
            
            if (isObjectMenu == 0) {
                objectmenu = null;//ObjectMenu aus der Variable löschen.
            }
        
        }
        
        return isClicked_;
        
    }
    
    public ObjectMenu getObjectMenu () {
        
        if (objectmenu != null) {
            return objectmenu;
        } else {
            return null;
        }
        
    }
    
    public void paintObjectsLayer1 (Graphics g,double x,double y,boolean front) {
        
        paintObjects(g,x,y,front);
        
    }
    
    public void paintObjectsLayer2 (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects_2.length; ++i) {
            
            if (objects_2[i] != null) {
                if(((front&&objects_2[i].getY()+objects_2[i].getImage().getHeight()>y)||(!front&&objects_2[i].getY()+objects_2[i].getImage().getHeight()<=y))) {
                    objects_2[i].paint(g,x,y);
                }
            }
            
        }
        
    }
    
    public void paintObjectsLayer3 (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects_3.length; ++i) {
            
            if (objects_3[i] != null) {
                if(((front&&objects_3[i].getY()+objects[i].getImage().getHeight()>y)||(!front&&objects_3[i].getY()+objects_3[i].getImage().getHeight()<=y))) {
                    objects_3[i].paint(g,x,y);
                }
            }
            
        }
        
    }
    
    public void paintObjects (Graphics g,double x,double y,boolean front) {
        
        for (int i = 0; i < objects.length; ++i) {
            
            if (objects[i] != null) {
                if(((front&&objects[i].getY()+objects[i].getImage().getHeight()>y)||(!front&&objects[i].getY()+objects[i].getImage().getHeight()<=y))) {
                    objects[i].paint(g,x,y);
                }
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
    
    public void paintObjectMenu (Graphics g, double xp, double yp) {
        
        /********************************************
         * 
         * Object-Menu
         * 
         *******************************************/
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].paintMenu(g, xp, yp);
            }
            
        }
        
    }
    
}
