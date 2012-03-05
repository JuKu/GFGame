package net.generationfuture.game;

import plugindeveloper.PluginManager;

public class PluginManagerImpl implements PluginManager {
    
    @Override
    public void showVisualMessage (String message) {
        System.out.println("Message: " + message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println("Plugin: " + message);
    }
    
}
