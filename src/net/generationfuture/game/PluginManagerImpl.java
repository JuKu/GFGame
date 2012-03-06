package net.generationfuture.game;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import plugindeveloper.PluginManager;

public class PluginManagerImpl implements PluginManager {
    
    Log log;
    GFGame gfgame;
    
    public PluginManagerImpl (Log log, GFGame gfgame) {
        this.log = log;
        this.gfgame = gfgame;
    }
    
    @Override
    public void showVisualMessage (String message) {
        System.out.println("Message: " + message);
    }

    @Override
    public void showErrorMessage(String message) {
        
        try {
            System.err.println("Plugin: " + message);
            this.log.write("#### Plugin-Error: " + message);
        } catch (IOException ex) {
            Logger.getLogger(PluginManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getPluginDataPath(String pluginname) {
        return "GameData/Plugin/Data/" + pluginname;
    }

    @Override
    public Boolean createPluginDataPath(String pluginname) {
        File f = new File("GameData/Plugin/Data/" + pluginname);
        return f.mkdirs();
    }

    @Override
    public void writeLog(String message) {
        try {
            log.write("####Plugin: " + message);
        } catch (IOException ex) {
            Logger.getLogger(PluginManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showConfirmDialog(new JLabel(), new JLabel("" + message), "GFGame-Plugin", 1);
    }

    @Override
    public void closeGame() {//Erlaubt dem Plugin das Game zu schließen
        gfgame.close();
    }

    @Override
    public void createQuest(File file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getVersion() {
        return PluginManagerImpl.version;
    }

    @Override
    public void exec(String command) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
