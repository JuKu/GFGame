package net.generationfuture.game;

public class Config {
    
    INIDatei config_datei;
    
    private String server = "";
    private int port = 80;
    
    private int debug_modus = 0;
    private String cache_folder = "";
    
    public Config (String config_datei) {
        this.config_datei = new INIDatei(config_datei);
        loadConfig();
    }
    
    public final void loadConfig () {
        
        server = config_datei.leseString("Server", "server");
        port = config_datei.leseInteger("Server", "port", 80);
        
        debug_modus = config_datei.leseInteger("Config", "debug", 0);
        cache_folder = config_datei.leseString("Config", "cache");
        
        if (debug_modus == 1) {
            System.out.println("Server: " + server + ", Port: " + port + ".");
            System.out.println("Cache: " + cache_folder + ".");
        }
        
    }
    
    public Boolean isDebugMode () {
        
        if (debug_modus != 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
}
