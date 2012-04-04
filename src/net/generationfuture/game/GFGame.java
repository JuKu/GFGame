package net.generationfuture.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import java.awt.Font.*;
import java.io.File;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import quests.*;
import plugindeveloper.Pluggable;

import plugindeveloper.PluginManager;
    
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
    public static Log log;
    public Boolean init = true;
    public Animation loading = null;
    public Boolean isInput = false;
    
    double plugindeveloper_jar_version = 1.0;
    List<Pluggable> plugins = null;
    
    Loadscreen loadscreen = null;
    
    public GFGame() throws SlickException
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        
        loadscreen = new Loadscreen();
        
        loading = new Animation();
        //loading.addFrame(new Image("materials/loading/loading.gif"), 50);
        
        int speed = 50;
        
        for (int i = 0; i < 20; i++) {
            
            if (i < 10) {
                loading.addFrame(new Image("materials/loading/IMG0000" + i + ".bmp"), speed);
            } else {
                loading.addFrame(new Image("materials/loading/IMG000" + i + ".bmp"), speed);
            }
            
        }
        
        loadscreen.repaint();
        
        //Plugins laden
        
        try {
            plugins = PluginLoader.loadPlugins(new File("Plugin"));
        } catch (IOException ex) {
            Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.setProperty("java.library.path", "./lib");
        System.out.println("java.library.path: " + System.getProperty("java.library.path"));
        
        try {
            loadscreen.setText("Konfiguration laden.");
            config = new Config(config_datei);
        } catch (IOException ex) {
            Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            log = new Log(config);
        } catch (IOException ex) {
            Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PluginManager manager = new PluginManagerImpl(log, this);
        for (Pluggable p : plugins) {
        //System.out.println("Plugin gefunden.");
        p.setPluginManager(manager);
        }
        
        //Plugins starten
        for (Pluggable p : plugins) {
            
            if (p.isCompatible(this.plugindeveloper_jar_version)) {
                //
            } else {
                //JOptionPane.showConfirmDialog(new JLabel("Das Plugin " + p.getName() + " ist wahrscheinlich nicht mit diesem Spiel kompatibel."), this);
                int i_ = JOptionPane.showConfirmDialog(new JLabel(), new JLabel("Das Plugin \"" + p.getName() + "\" ist wahrscheinlich nicht mit diesem Spiel kompatibel. Trotzdem fortfahren?"), "GFGame", 1);
                
                if (i_ == 1) {
                    System.exit(0);
                }
            }
            Boolean plugin_bool = p.start();
            
            if (!plugin_bool) {
                JOptionPane.showConfirmDialog(new JLabel("test"), new JLabel("Ein Plugin konnte nicht erfolgreich gestartet werden."));
                //JOptionPane.showConfirmDialog(new JLabel("test2"), new JLabel("test"), "test_", 1);
            } else {
                System.out.println("Ein Plugin wurde erfolgreich gestartet.");
            }
            
        }
    // wait
    /*try {
      Thread.sleep(10000);
    }
    catch (InterruptedException ie) {
      ie.printStackTrace();
    }*/
        
        //Das Game wird mit der Methode initGame(GameContainer gc) initialisiert.
        
     }
    
    public void initGame (GameContainer gc) throws SlickException, InterruptedException, IOException {
        
        this.init = true;
        this.isInput = false;
        
        log.write("Methode initGame() start.");
        
        //Initialisierung des Games
        
        //error("Loading map");
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/mystery1.tmx","materials");
        
        playerposImage = new Image("materials/point.png");
        willkommens_bild = new Image("materials/willkomen_bild_2.png");
        
        //error("Loading Settings...");
        
        try {
            log.write(System.getProperty("GFGame init()."));
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
        
        items = new Items();
        
        //Object_Manager erzeugen, der sich um die Objekte kümmert.
        object_manager = new ObjectManager(config, player, items);
        //Animal_Manager erzeugen, der sich um die Animals kümmert.
        animal_manager = new AnimalManager(config, player);
        
        //error("Loading Chat...");
        
        irc_chat = new IRC_Chat(client, this, player, config);
        bedürfnis_anzeige = new NeedsDisplay(player);
        
        gc.getInput().addMouseListener(new GameMouseListener(this, player));
        questmanager = new QuestManager(this, player, items, config);
        
        questmanager.start();
        Quest quest_ = new Quest1(player, items);
        questmanager.createNewQuest(quest_);
        quest_ = new Quest2(player, items); quest_.showWindow(true);
        questmanager.createNewQuest(quest_);
        
        log.write("Methode initGame() fertig.");
        log.write("Maus-Input eingeschalten.");
        
        this.init = false;
        this.isInput = true;
        
        loadscreen.exit();
        
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        
        if (!this.init) {
        
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
            this.isInput = true;
        }
        
        } else {
            
            app.setDisplayMode(400, 200, false);
            this.isInput = false;
            
            try {
                
                try {
                    initGame(gc);
                } catch (IOException ex) {
                    Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                log.write("GFGame init() ist fertig.");
            } catch (IOException ex) {
                Logger.getLogger(GFGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        if(g.getClip()==null) { 
            g.setClip(0, 0, gc.getWidth(), gc.getHeight());
        }
        
        if (!init) {
        
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
        object_manager.paintObjectMenu(g, player.getX(), player.getY());
        
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
        
        } else {
            g.setColor(Color.white);
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.black);
            g.drawString("The Game is loading...", 100, 20);
            loading.draw(100, 50);
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
    
    public void actionPerformed (String actionCommand, GameMenuItem menuItem) throws IOException {
        
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
        
        log.write("actionPerformed-Command: " + actionCommand + ".");
        
    }
    
    public void close () {
        for (Pluggable p : plugins) {
            p.stop();
        }
    }
    
}
