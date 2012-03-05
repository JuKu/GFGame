package net.generationfuture.game;

public interface Pluggable {
    
    boolean start();
    boolean stop();
    
    void setPluginManager(PluginManager manager);
    
}
