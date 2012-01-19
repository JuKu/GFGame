package net.generationfuture.game;

import java.io.File;
import java.io.IOException;

public class Config {
    
    INIDatei config_datei;
    INIDatei debug_datei;
    
    private String server = "";
    private int port = 80;
    private int debug_modus = 0;
    private String cache_folder = "";
    private int auto_update = 1;
    private String server_typ = "";
    private String debug_folder = "";
    private String debug_file = "";
    private WebClient client;
    private String player_name = "testplayer";
    private int player_pos_x = 10;
    private int player_pos_y = 10;
    private String save_folder = "GameData/Player";
    private int player_id = 1;
    private String username = "testuser1";
    private String passwort = "testuser1234";
    
    private int showIRC_Chat = 1;
    private int IRC_Chat_max_lines = 5;
    private int IRC_Chat_showRanks = 1;
    private int IRC_Chat_showUsername=1;
    private String IRC_Chat_cache_folder = "";
    private String IRC_Chat_config_file = "GameData/Config/IRC_Chat.ini";
    
    private String firstRunFile = "GameData/Config/FirstRun.ini";
    private Boolean firstRun = true;
    
    public Config (String config_datei) throws IOException {
        this.config_datei = new INIDatei(config_datei);
        loadConfig();
        this.debug_datei = new INIDatei("GameData/Config/Debug.ini");
        loadDebugConfig();
        loadWebClient();
        loadIRC_ChatConfig();
        loadFirstRunConfig();this.setFirstRun(true);
    }
    
    public final void loadConfig () throws IOException {
        
        server = config_datei.leseString("Server", "server");
        port = config_datei.leseInteger("Server", "port", 80);
        debug_modus = config_datei.leseInteger("Config", "debug", 0);
        cache_folder = config_datei.leseString("Config", "cache");
        auto_update = config_datei.leseInteger("Config", "auto_update", 1);
        server_typ = config_datei.leseString("Config", "server_typ");
        
        if (debug_modus == 1) {
            System.out.println("Server: " + server + ", Port: " + port + ".");
            System.out.println("Cache: " + cache_folder + ".");
            System.out.println("Auto_update: " + auto_update + ".");
            System.out.println("server_typ: " + server_typ + ".");
        }
        
        File file = new File(cache_folder);
        if (!file.exists()) {
            
            file.mkdir();
            
            if (debug_modus == 1) {
                System.out.println("Cache-Ordner wurde erfolgreich erstellt.");
            }
            
        }
        
    }
    
    public final void loadDebugConfig () throws IOException {
        
        debug_folder = debug_datei.leseString("Debug", "folder");
        debug_file = debug_datei.leseString("Debug", "file");
        
        if (debug_modus == 1) {
            System.out.println("Debug-Folder: " + debug_folder + ".");
            System.out.println("Debug-File: " + debug_file + ".");
        }
        
        File file = new File(debug_folder);
        
        if (!file.exists()) {
            
            file.mkdir();
            
            if (debug_modus == 1) {
                System.out.println("Debug-Ordner wurde erfolgreich erstellt.");
            }
            
        }
        
        file = new File(debug_file);
        
        if (!file.exists()) {
            
            Boolean isCreatedNewFile = file.createNewFile();
            
            if (debug_modus == 1 && isCreatedNewFile) {
                System.out.println("Debug-Datei wurde erfolgreich erstellt.");
            } else if (debug_modus == 1 && !isCreatedNewFile) {
                System.out.println("Debug-Datei konnte nicht erstellt werden.");
            }
            
        }
        
    }
    
    public Boolean loadPlayer (String player_name) {
        
        this.player_name = player_name;
        
        File file = new File(save_folder + "/" + player_name + ".ini");
        
         if (!file.exists()) {
             return false;
         } else {
             
             INIDatei player_datei = new INIDatei(save_folder + "/" + player_name + ".ini");
             this.player_name = player_datei.leseString("Player", "name");
             this.player_id = player_datei.leseInteger("Player", "id", 1);
             this.player_pos_x = player_datei.leseInteger("Player", "player_pos_x", 10);
             this.player_pos_y = player_datei.leseInteger("Player", "player_pos_y", 10);
             
             return true;
             
         }
        
    }
    
    public final void loadIRC_ChatConfig () {
        INIDatei inidatei = new INIDatei(this.IRC_Chat_config_file);
        
        this.showIRC_Chat = inidatei.leseInteger("IRC_Chat", "showIRC_Chat", 1);
        this.IRC_Chat_max_lines = inidatei.leseInteger("IRC_Chat", "max_lines", 5);
        this.IRC_Chat_showRanks = inidatei.leseInteger("IRC_Chat", "showRanks", 1);
        this.IRC_Chat_showUsername = inidatei.leseInteger("IRC_Chat", "showUsername", 1);
        this.IRC_Chat_cache_folder = inidatei.leseString("IRC_Chat", "cache_folder");
    }
    
    public final void loadFirstRunConfig () {
        
        INIDatei inidatei = new INIDatei(this.firstRunFile);
        
        int i = inidatei.leseInteger("FirstRun", "FirstRun", 0);
        
        if (i == 0) {
            this.firstRun = false;//Anwendung wwurde schon öfters ausgeführt.
        } else {
            this.firstRun = true;//Anwendung wurde noch nicht ausgeführt.
        }
        
    }
    
    public Boolean isFirstRun () {
        return this.firstRun;
    }
    
    public final void setFirstRun (Boolean firstRun) {
        
        this.firstRun = firstRun;
        
        //Datei schreiben
        
        INIDatei inidatei = new INIDatei(this.firstRunFile);
        
        int i = 0;
        
        if (this.firstRun) {
            i = 1;//Anwenudng wurde noch nicht ausgeführt.
        } else {
            i = 0;//Anwenudng wurde schon ausgeführt.
        }
        
        inidatei.setzeInteger("FirstRun", "FirstRun", i);
        inidatei.schreibeINIDatei(this.firstRunFile, true);
        
    }
    
    public Boolean showIRC_Chat () {
        
        if (this.showIRC_Chat == 0) {
            return false;
        } else {
            return true;
        }
        
    }
    
    public String getCacheFolder () {
        return cache_folder;
    }
    
    public final void savePlayer () {
        
        String dateiname = save_folder + "/" + player_name + ".ini";
        File file = new File(dateiname);
        
        if (!file.exists()) {
            
            try {
            
            file.createNewFile();
            
            } catch (IOException ex) {
                
                if (debug_modus == 1) {
                    System.out.println("Die Datei \"" + dateiname + "\" konnte nicht angelegt werden.");
                }
                
            }
            
        }
        
        INIDatei player_datei = new INIDatei(save_folder + "/" + player_name + ".ini");
        
        player_datei.setzeString("Player", "name", "" + player_name);
        player_datei.setzeInteger("Player", "id", player_id);
        player_datei.setzeInteger("Player", "player_pos_x", player_pos_x);
        player_datei.setzeInteger("Player", "player_pos_y", player_pos_y);
        
        player_datei.schreibeINIDatei(save_folder + "/" + player_name + ".ini", true);
        
    }
    
    public final void loadWebClient () throws IOException {
        
        if ("php".equals(server_typ)) {
            this.client = new PHPClient(this);
        } else {
            this.client = new JavaClient(this);
        }
        
    }
    
    public void setPlayerPosData (int x, int y) {
        player_pos_x = x;
        player_pos_y = y;
    }
    
    public String getUsername () {
        return username;
    }
    
    public String getPasswort () {
        return passwort;
    }
    
    public WebClient getClient () {
        return this.client;
    }
    
    public String getServerURL () {
        return this.server;
    }
    
    public int getPort_ () {
        return this.port;
    }
    
    public Boolean isDebugMode () {
        
        if (debug_modus != 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public void setPlayerName (String player_name) {
        this.player_name = player_name;
    }
    
    public String getServerTyp () {
        return this.server_typ;
    }
    
}
