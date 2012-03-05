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
    
    public PluginManagerImpl (Log log) {
        this.log = log;
    }
    
    @Override
    public void showVisualMessage (String message) {
        System.out.println("Message: " + message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println("Plugin: " + message);
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
    
}
