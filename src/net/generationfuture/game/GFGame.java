package net.generationfuture.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import objects.Object;
import objects.Tree1;
import java.awt.Font.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

    
public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
    Image playerposImage = null;
    TiledMap map = null;
    TiledMap grasland = null;
    
    TileSet tileset;

    int x = 0;
    int y = 0;
    int minimapx = 248;
    int minimapy = 200;
    Player player;
    GameMenu game_menu;
    
    public int zoom = 0;
    Config config;
    String config_datei = "GameData/Config/Config.ini";
    WebClient client;
    
    IRC_Chat irc_chat;
    NeedsDisplay bedürfnis_anzeige;
    
    AnimalManager animal_manager;
    ObjectManager object_manager;
    
    public GFGame() throws SlickException
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/mystery1.tmx","materials");
        
        playerposImage = new Image("materials/point.png");
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
        menuItem = new GameMenuItem("Menu_2", new Image("materials/buttons/Buttons1/base_button.png"));
        menuItem.setActionCommand("base_button");
        game_menu.addMenuItem(menuItem);
        
        player = new Player();
        
        //Object_Manager erzeugen, der sich um die Objekte kümmert.
        object_manager = new ObjectManager(config, player);
        //Animal_Manager erzeugen, der sich um die Animals kümmert.
        animal_manager = new AnimalManager(config, player);
        
        irc_chat = new IRC_Chat(client, this, player, config);
        bedürfnis_anzeige = new NeedsDisplay(player);
        
        gc.getInput().addMouseListener(new GameMouseListener(this));
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
        
        //Animals bewegen
        animal_manager.moveAnimals();
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        
        map.render(0,0,x-400,y-300,800,600);
        //Objects Ebene 3 zeichnen
        object_manager.paintObjectsLayer3(g);
        //Animals zeichnen
        animal_manager.paintAnimals(g);
        //Player zeichnen
        player.getImage().draw(352, 224);//playerposImage.draw(394, 294);
        //Objects Ebene 2 zeichnen
        object_manager.paintObjectsLayer2(g);
        //Objects zeichnen
        object_manager.paintObjects(g);
        
        map.render(0,0,x-60,y-60,120,120);
        game_menu.paint(g);
        //PlayerposImage zeichnen
        playerposImage.draw(54, 54);        
        //Bedürfnis-Anzeige zeichnen
        bedürfnis_anzeige.paint(g);
        //Object-Menü zeichnen
        object_manager.paintObjectMenu(g);
        //Animal-Menü zeichnen
        animal_manager.paintAnimalMenu(g);
        
        irc_chat.paint(gc, g);
        
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         
         AppGameContainer app = new AppGameContainer(new GFGame());
         //Dies ist JuKus erster Kommentar in dieser Datei. ;-)
         app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    public void scroll (int x, int y) {
        
        //Objects scrollen
        object_manager.scrollObjects(x, y);
        //Animals scrollen
        animal_manager.scrollAnimals(x, y);
        
    }
    
    class GameMouseListener implements MouseListener {
        
        GFGame gfgame;
        
        public GameMouseListener (GFGame gfgame) {
            this.gfgame = gfgame;
        }

        @Override
        public void mouseWheelMoved(int i) {
            zoom = zoom + i;//throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(int i, int i1, int i2, int i3) {
            
            int mouse_x = i1;
            int mouse_y = i2;
            
            Boolean isClicked_ = false;
            
            GameMenuItem menuItem = game_menu.mouseClicked(mouse_x, mouse_y);
            
            if (menuItem != null) {
                isClicked_ = true;
                
                //System.out.println("Clicked.");
                gfgame.actionPerformed(menuItem.getActionCommand(), menuItem);
            }
            
            //Teste, ob Object angeklickt wurde.
            object_manager.isClicked(mouse_x, mouse_y, isClicked_);
            
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
            
            //Testen, ob Maus über Animals gefahren wurde.
            isMouseMoved = gfgame.animal_manager.isMouseOver(mouse_x, mouse_y, isMouseMoved);
             //Teste, ob Maus über Object "gefahren" wurde.
            isMouseMoved = gfgame.object_manager.isMouseOver(mouse_x, mouse_y, isMouseMoved);
            
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
    public void actionPerformed (String actionCommand, GameMenuItem menuItem) {
        
        /***********************************************
         * 
         * Methode wird aufgerufen, wenn ein Menü angeklickt wurde.
         * Dabei wird der String actionCommand des Menüs übergeben,
         * der vorher mittels menuItem.setActionCommand (String action_command);
         * übergeben wird. (evtl. der init()-Methode)
         * 
         **********************************************/
        
        System.out.println("ActionCommand: " + actionCommand);
        
        if ("irc".equals(actionCommand)) {
            irc_chat.switchshowChat();
        }
        
    }

}
