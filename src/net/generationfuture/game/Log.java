package net.generationfuture.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    
    Config config;
    
    public Log (Config config) throws IOException {
        this.config = config;
        this.write(System.getProperty("line.separator") + "GFGame starts.");
    }
    
    public final void write (String string) throws IOException {
        
        try {
        
        File debug_datei = new File(config.getDebugFile());
        
        if (!debug_datei.exists()) {
            debug_datei.createNewFile();
        }
        
        debug_datei.createNewFile();
        
        FileWriter writer;
        writer = new FileWriter(debug_datei, true);
        
        writer.write("" + string + System.getProperty("line.separator") + "");
        
        writer.flush();
        writer.close();
        
        } catch (Exception e) {
            System.err.println("Die Debug-Datei konnte nicht geschrieben werden.");
        }
        
    }
    
}
