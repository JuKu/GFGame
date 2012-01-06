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
    
    
    public Config (String config_datei) throws IOException {
        this.config_datei = new INIDatei(config_datei);
        loadConfig();
        this.debug_datei = new INIDatei("GameData/Config/Debug.ini");
        loadDebugConfig();
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
            
            file.createNewFile();
            
            if (debug_modus == 1) {
                System.out.println("DEbug-Datei wurde erfolgreich erstellt.");
            }
            
        }
        
    }
    
    public Boolean isDebugMode () {
        
        if (debug_modus != 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public String getServerTyp () {
        return this.server_typ;
    }
    
}
