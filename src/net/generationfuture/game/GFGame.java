package net.generationfuture.game;

import animals.Animal;
import animals.Rabbit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

import objects.Object;
import objects.Tree1;

public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
    Image playerposImage = null;
    TiledMap map = null;
    TiledMap grasland = null;
    
    TileSet tileset;
        
        Image tree1_picture1;
        Image tree1_picture2;
        Image tree1_picture3;
        Image tree1_picture4;
        
        Image gras1;

    int x = 0;
    int y = 0;
    
    int minimapx = 248;
    int minimapy = 200;
    
    Object objects[];
    Object objects_2[];//2. Grafik-Ebene
    Object objects_3[];//3. Grafik-Ebene
    Player player;
    
    Object objekte[][][];
    GameMenu game_menu;
    
    int width = 200;
    int height = 200;
    
    int grafik_ebenen = 3;
    Animal animals[];
    
    public int zoom = 0;
    Config config;
    
    String config_datei = "GameData/Config/Config.ini";
    WebClient client;
    
    public GFGame() throws SlickException
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/firstmystery.tmx","materials");
        playerposImage = new Image("materials/point.png");
        tree1_picture1 = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        
        gras1 = new Image("materials/trees/tree1_/fir C ani0000.bmp",new Color(94, 66, 41, 255));
        try {
            config = new Config(config_datei);
        } catch (IOException ex) {
            Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        client = config.getClient();
        client.start();
        
        client.getPlayerData();
        int i[] = client.getPlayerPos();
        
        x = i[0];
        y = i[1];
        
        grasland = new TiledMap("materials/test_.tmx","materials");
        
        game_menu = new GameMenu("Menu1", null, 140, 10);
        GameMenuItem menuItem = new GameMenuItem("test", new Image("materials/buttons/Buttons1/base_button_code.png"));
        
        game_menu.addMenuItem(menuItem);
        
        menuItem = new GameMenuItem("test", new Image("materials/buttons/Buttons1/base_button_bugs.png"));
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_1", new Image("materials/buttons/Buttons1/base_button_faq.png"));
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_2", new Image("materials/buttons/Buttons1/base_button_irc.png"));
        game_menu.addMenuItem(menuItem);
        menuItem = new GameMenuItem("Menu_2", new Image("materials/buttons/Buttons1/base_button.png"));
        game_menu.addMenuItem(menuItem);
        
        if (this.tree1_picture1 == null) {
            System.err.println("NullPointerException.");
        }
        
        objects = new Object[100];
        objekte = new Object[grafik_ebenen][width][height];
        
        animals = new Animal[100];
        animals[0] = new Rabbit("Hase1", 100, 200);
        
        objects[0] = new Tree1(200, 200, this.tree1_picture1, "Baum1");
        objects[1] = new Tree1(200 + 128, 200, this.tree1_picture1, "Baum2");
        
        objekte[2][1][1] = objects[0];
        objects_2 = new Object[100];
        
        objects_3 = new Object[100];
        //objects_3[0] = new Tree1(180, 210, this.tree1_picture1);
        
        player = new Player();
        gc.getInput().addMouseListener(new GameMouseListener());
     }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {x--; player.move(); this.scroll(1, 0); player.walkingLeft(); }
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {x++; player.move(); this.scroll(-1, 0); player.walkingRight(); }
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {y--; player.move(); this.scroll(0, 1); player.walkingBack(); }
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {y++; player.move(); this.scroll(0, -1); player.walkingFor(); }
        
        if (gc.isMouseGrabbed()) {
            JOptionPane.showInternalMessageDialog(new JLabel("test"), this);
        }
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].move();
            }
            
        }
        
        //player.addHunger(-1);
        //player.addEnergie(-1);
        //player.addHygiene(-1);
        //player.addHarndrang(-1);
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        
        /*for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].paint(g);
            }
            
        }*/
        
        map.render(0,0,x-400,y-300,800,600);
        //grasland.render(0, 0, x, y, 800, 600);
        
        for (int i = 0; i < objects_3.length; i++) {
            
            if (objects_3[i] != null) {
                objects_3[i].paint(g);
            }
            
        }
        
        /*********************************
         * 
         * Animals "zeichnen"
         * 
         ********************************/
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].paint(g);
            }
            
        }
        
        player.getImage().draw(394, 294);//playerposImage.draw(394, 294);
        
        for (int i = 0; i < objects_2.length; i++) {
            
            if (objects_2[i] != null) {
                objects_2[i].paint(g);
            }
            
        }
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].paint(g);
            }
            
        }
        
        map.render(0,0,x-60,y-60,120,120);
        game_menu.paint(g);
        
        playerposImage.draw(54, 54);
        
        //objects[0].paint(g);
        
        /*for (int i = 0; i <= grafik_ebenen; i++) {
            
            for (int j = 0; j <= width; j++) {
                
                for (int k = 0; k <= height; k++) {
                    objekte[i][j][k].paint(g);
                }
                
            }
            
        }*/
        
        /*********************************
         * 
         * Animals "zeichnen"
         * 
         ********************************/
        
        /*for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].paint(g);
            }
            
        }*/
        
        /*********************************
         * 
         * Bedürfnis-Anzeige
         * 
         ********************************/
        
        g.setColor(Color.blue);
        g.drawLine(0, 500, 800, 500);
        g.setColor(Color.white);
        g.fillRect(0, 500, 800, 800);
        g.setColor(Color.blue);
        g.drawString("Bedürfnisse", 20, 500);
        
        //Hunger
        
        int hunger_anzeige_x = 20;//20;
        int hunger_anzeige_y = 520;//540;
        
        int hunger_length = 200;//120;
        
        g.setColor(Color.blue);
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
        
        g.setColor(Color.white);
        g.drawString("Hunger", hunger_anzeige_x + 30, hunger_anzeige_y);
        
        //Energie
        
        int energie_anzeige_x = 240;//20;
        int energie_anzeige_y = 520;//540;
        
        int energie_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(energie_anzeige_x, energie_anzeige_y, energie_length, 20, 50, 50);
        
        if (player.isSleepy()) {
            g.setColor(Color.red);
        } else if (player.getEnergie() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getEnergie() <= 0) {
            player.addEnergie(1);
        }
        
        g.fillRoundRect(energie_anzeige_x, energie_anzeige_y, player.getEnergie() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Energie", energie_anzeige_x + 30, energie_anzeige_y);
        
        //Hygiene
        
        int hygiene_anzeige_x = 20;//20;
        int hygiene_anzeige_y = 550;//540;
        
        int hygiene_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(hygiene_anzeige_x, hygiene_anzeige_y, hygiene_length, 20, 50, 50);
        
        if (player.isDirty()) {
            g.setColor(Color.red);
        } else if (player.getHygiene() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getHygiene() <= 0) {
            player.addHygiene(1);
        }
        
        g.fillRoundRect(hygiene_anzeige_x, hygiene_anzeige_y, player.getHygiene() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Hygiene", hygiene_anzeige_x + 30, hygiene_anzeige_y);
        
        //Harndrang
        
        int harndrang_anzeige_x = 240;//20;
        int harndrang_anzeige_y = 550;//540;
        
        int harndrang_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(harndrang_anzeige_x, harndrang_anzeige_y, harndrang_length, 20, 50, 50);
        
        if (player.isHarndrang()) {
            g.setColor(Color.red);
        } else if (player.getHarndrang() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getHarndrang() <= 0) {
            player.addHarndrang(1);
        }
        
        g.fillRoundRect(harndrang_anzeige_x, harndrang_anzeige_y, player.getHarndrang() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Harndrang", harndrang_anzeige_x + 30, harndrang_anzeige_y);
        
        /********************************************
         * 
         * Object-Menu
         * 
         *******************************************/
        
        /*if (objectmenu != null) {
            objectmenu.paint(g);
        }*/
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].paintMenu(g);
            }
            
        }
        
        /********************************************
         * 
         * Animal-Menu
         * 
         *******************************************/
        
        /*if (objectmenu != null) {
            objectmenu.paint(g);
        }*/
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].paintMenu(g);
            }
            
        }
        
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         
         AppGameContainer app = new AppGameContainer(new GFGame());
         //Dies ist JuKus erster Kommentar in dieser Datei. ;-)
         app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    public BufferedImage makeTransparent (BufferedImage img) {
        
        for (int i = img.getWidth() - 1; i > -1; i--) {
	for (int j = img.getHeight() - 1;  j > -1; j--) {
		if (img.getRGB(i, j) == new Color(255,255,255).getAlpha()) {
			img.setRGB(i, j, new Color(0, 0, 0, 0).getAlpha());
		}
	}
        
        }
        
        return img;
        
    }
    
    public void scroll (int x, int y) {
        
        for (int i = 0; i < objects.length; i++) {
            
            if (objects[i] != null) {
                objects[i].scroll(x, y);
            }
            
        }
        
        for (int i = 0; i < animals.length; i++) {
            
            if (animals[i] != null) {
                animals[i].scroll(x, y);
            }
            
        }
        
    }
    
    class GameMouseListener implements MouseListener {

        @Override
        public void mouseWheelMoved(int i) {
            zoom = zoom + i;//throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(int i, int i1, int i2, int i3) {
            //System.out.println("i: " + i + ", i1: " + i1 + ", i2: " + i2 + ", i3: " + i3 + ".");//throw new UnsupportedOperationException("Not supported yet.");
            
            int mouse_x = i1;
            int mouse_y = i2;
            
            //System.out.println("Mouse x: " + mouse_x + ", y: " + mouse_y + ".");
            
            /*********************************
             * 
             * Teste, ob Object angeklickt wurde.
             * 
             ********************************/
            
            Boolean isClicked_ = false;
            
            for (int i_ = 0; i_ < objects.length; i_++) {
                
            Boolean isClicked;
            
            if (objects[i_] != null) {
                isClicked = objects[i_].isClicked(mouse_x, mouse_y);
                if (isClicked) { System.out.println("Object[" + i_ + "] isClicked."); isClicked_ = true; }
            }
            
            if (!isClicked_) {
                
                //
                
            }
            
            }
        }

        @Override
        public void mousePressed(int i, int i1, int i2) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(int i, int i1, int i2) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseMoved(int i, int i1, int i2, int i3) {
            int mouse_x = i;
            int mouse_y = i1;
            
            GameMenuItem menuItem = game_menu.mouseOver(mouse_x, mouse_y);
            Boolean isMouseMoved = false;
            
            if (menuItem != null) {
                isMouseMoved = true;
                menuItem.setMouseOver(true);
            }
            
            //System.out.println("mouse_x: " + mouse_x + ", mouse_y: " + mouse_y + ".");//throw new UnsupportedOperationException("Not supported yet.");
            
            /*********************************
             * 
             * Teste, ob Maus über Animal "gefahren" wurde.
             * 
             ********************************/
            
            if (!isMouseMoved) {
            
            for (int i_ = 0; i_ < objects.length; i_++) {
                
            Boolean isClicked;
            
            if (animals[i_] != null) {
                Boolean isMouseMoved_ = animals[i_].mouseMoved(mouse_x, mouse_y);
                if (isMouseMoved_) { isMouseMoved = true; }
            }
            
            }
            
            }
            
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
        }

        @Override
        public void mouseDragged(int i, int i1, int i2, int i3) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setInput(Input input) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isAcceptingInput() {
            //throw new UnsupportedOperationException("Not supported yet.");
            return true;
        }

        @Override
        public void inputEnded() {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void inputStarted() {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        //
    }
    
}
