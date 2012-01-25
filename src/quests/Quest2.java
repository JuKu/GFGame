package quests;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.generationfuture.game.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Quest2 extends Quest {
    
    public Quest2 (Player player, Items items) throws SlickException {
        super(player, items);
        Questimage[0] = new Image("GameData/Cache/Quests/Quest1_2.png");
        Questhinweis = "Willkommen";
        questwindow.writeTextOnWindowBody("Herzlich Willkommen im Mystery-Park!<br><br>Im Mystery-Park gibt es viel zu entdecken.<br>bla, bla, bla.");
        
        questwindow.showWindow(true);
        //questwindow.setImage(new Image("GameData/Cache/Quests/Quest1_2.png"), 20, 20);
    }
    
    @Override
    public void update () {
        
        if (!questwindow.isWindow()) {//Wenn Fenster geschlossen wurde.
            this.QuesthasFinished = true;
        }
        
    }
    
    @Override
    public void reward () {
        items.Holz = items.Holz + 10;
    }
    
    /*@Override
    public void paintWindowBody (Graphics g) {
        
        g.setColor(Color.blue);
        g.drawString("Herzlich Willkommen im Mystery-Park!", 250, 120);
        
        g.drawString("Im Mystery-Park gibt es viel zu entdecken.", 250, 140);
        g.drawString("bla, bla, bla.", 250, 160);
        
        //
        
    }*/
    
}