package net.generationfuture.game;

public class IRC_Chat {
    
    private WebClient webclient;
    private GFGame gfgame;
    private Player player;
    private Config config;
    
    public IRC_Chat (WebClient webclient, GFGame gfgame, Player player, Config config) {
        this.webclient = webclient;
        this.gfgame = gfgame;
    }
    
}
