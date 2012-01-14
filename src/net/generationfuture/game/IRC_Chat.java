package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class IRC_Chat {
    
    private WebClient webclient;
    private GFGame gfgame;
    private Player player;
    private Config config;
    private int x = 10;
    private int y = 360;
    private int width = 200;
    private int height = 100;
    
    private Boolean showChat = false;
    private String messages[][];
    
    public IRC_Chat (WebClient webclient, GFGame gfgame, Player player, Config config) {
        this.webclient = webclient;
        this.gfgame = gfgame;
        this.player = player;
        this.config = config;
        
        messages = new String[100][5];
    }
    
    public void switchshowChat () {
        showChat = !showChat;
    }
    
    public void paint (Graphics g) {
        
        if (showChat) {
            g.setColor(Color.white);
            g.fillRoundRect(x, y, width, height, 5);
            g.setColor(new Color(225, 106, 0));//FF6A00
            g.drawRoundRect(x, y, width, height, 5);
            g.setColor(new Color(225, 106, 0));
            g.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 5);
            
            g.drawString("IRC-Chat", x + 4, y + 4);
            g.setColor(Color.black);
            
            g.drawRect(x + 8, y + 20, width - 20, height - 40);
            
            int j = 0;
            
            messages[0][0] = "Message1";
            messages[1][0] = "Message2";
            messages[2][0] = "Message3";
            messages[3][0] = "Message4";
            
            for (int i = 0; i < messages.length; i++) {
                
                if (j <= 3 && messages[i][0] != null) {
                    g.drawString(messages[i][0], x + 10, y + (j * 14) + 20);
                }
                
                j++;
            }
            
            g.setColor(Color.white);
        }
        
    }
    
}
