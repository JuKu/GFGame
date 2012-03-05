package net.generationfuture.game;

import plugindeveloper.PluginManager;

public interface Pluggable {
    
    boolean start();
    boolean stop();
    
    void setPluginManager(PluginManager manager);
    
}
