package net.generationfuture.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import java.awt.Font.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import quests.*;
    
public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
    Image playerposImage = null;
    TiledMap map = null;
    TiledMap grasland = null;
    TileSet tileset;

    int minimapx = 248;
    int minimapy = 200;
    public Player player;
    public GameMenu game_menu;
    public int zoom = 0;
    public Config config;
    private String config_datei = "GameData/Config/Config.ini";
    public WebClient client;
    private IRC_Chat irc_chat;
    private NeedsDisplay bedürfnis_anzeige;
    public AnimalManager animal_manager;
    public ObjectManager object_manager;
    
    private Image willkommens_bild;
    public Boolean GameStart = false;
    
    public QuestManager questmanager;
    public Items items;
    
    private inputHandler ih = new inputHandler();
    private static AppGameContainer app;
    
    public static Boolean pause = false;
    
    public GFGame() throws SlickException
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        
        //error("Loading map");
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/mystery1.tmx","materials");
        
        playerposImage = new Image("materials/point.png");
        willkommens_bild = new Image("materials/willkomen_bild_2.png");
        
        //error("Loading Settings...");
        try {
            config = new Config(config_datei);
        } catch (IOException ex) {
            Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        client = config.getClient();
        client.start();
        
        client.getPlayerData();
        
        //grasland = new TiledMap("materials/test_.tmx","materials");
        
        //error("Loading Menu...");
        game_menu = new GameMenu("Menu1", null, 140, 10);
        game_menu = MenuManager.getGameMenu(game_menu);//Menu übergeben.
        
        //error("Loading Entities...");
        player = new Player();
        player.setPos(client.getPlayerPos());
        
        //Object_Manager erzeugen, der sich um die Objekte kümmert.
        object_manager = new ObjectManager(config, player);
        //Animal_Manager erzeugen, der sich um die Animals kümmert.
        animal_manager = new AnimalManager(config, player);
        
        //error("Loading Chat...");
        
        irc_chat = new IRC_Chat(client, this, player, config);
        bedürfnis_anzeige = new NeedsDisplay(player);
        
        items = new Items();
        
        gc.getInput().addMouseListener(new GameMouseListener(this, player));
        questmanager = new QuestManager(this, player, items);
        
        questmanager.start();
        Quest quest_ = new Quest1(player, items);
        questmanager.createNewQuest(quest_);
        quest_ = new Quest2(player, items); quest_.showWindow(true);
        questmanager.createNewQuest(quest_);
     }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        
        if (this.GameStart) {//Wenn Game gestartet wurde.
            
            if (!pause) {//Spiel ist nicht pausiert.
        
            ih.check(gc,player);
        
            if (gc.isMouseGrabbed()) {
                JOptionPane.showInternalMessageDialog(new JLabel("test"), this);
            }
        
            //Animals bewegen
            animal_manager.moveAnimals();
        
            }
            
        app.setDisplayMode(800, 600, false);
        
        } else {
            app.setDisplayMode(600, 600, false);
        }
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        if(g.getClip()==null) { 
            g.setClip(0, 0, gc.getWidth(), gc.getHeight());
        }
        
        if (this.GameStart) {//Wenn Game gestartet wurde. (Vorher Willkommens-Bildschirm)
        
        
        map.render(0,0,(int)player.getX()-gc.getWidth()/2,(int)player.getY()-gc.getHeight()/2,gc.getWidth(),gc.getHeight()-100);
        
        //Animals zeichnen
        animal_manager.paintAnimals(g,player.getX(),player.getY());
        
        //Objects Ebene 2 zeichnen
        object_manager.paintObjectsBehindPlayer(g,player.getX(),player.getY());
        
        //Player zeichnen
        player.getImage().draw((gc.getWidth()-player.getImage().getWidth())/2, 224);//playerposImage.draw(394, 294);
        
        //Objects zeichnen
        object_manager.paintObjectsOverPlayer(g,player.getX(),player.getY());
        
        //Map zeichnen
        map.render(0,0,(int)player.getX()-60,(int)player.getY()-60,120,120);
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
        questmanager.paintQuests(g);
        
        } else {
            this.willkommens_bild.draw(1, 1);
            g.setColor(Color.blue);
            g.drawString("Zum Fortsetzen mit der Maus klicken.", 200, 300);
            g.setColor(Color.white);
        }
        
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         /*AppGameContainer */app = new AppGameContainer(new GFGame());
         //Dies ist JuKus erster Kommentar in dieser Datei. ;-)
         app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    public void actionPerformed (String actionCommand, GameMenuItem menuItem) {
        
        /***********************************************
         * 
         * Methode wird aufgerufen, wenn ein Menü angeklickt wurde.
         * Dabei wird der String actionCommand des Menüs übergeben,
         * der vorher mittels menuItem.setActionCommand (String action_command);
         * übergeben wird. (evtl. in der init()-Methode)
         * 
         **********************************************/
        
        System.out.println("ActionCommand: " + actionCommand);
        
        if ("irc".equals(actionCommand)) {
            irc_chat.switchshowChat();
        } else if ("quest".equals(actionCommand)) {
            questmanager.wechsleAnsicht();
        }
        
    }
}
