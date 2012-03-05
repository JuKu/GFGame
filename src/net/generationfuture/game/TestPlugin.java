package net.generationfuture.game;

import plugindeveloper.PluginManager;

public class TestPlugin implements Pluggable {
    
    private PluginManager manager;
    
    @Override
    public void setPluginManager (PluginManager manager) {
        this.manager = manager;
    }
    
    @Override
    public boolean start () {
        this.manager.showVisualMessage("Started!");
        return true;
    }
    
    @Override
    public boolean stop () {
        this.manager.showVisualMessage("Stopped!");
        return true;
    }
    
}
