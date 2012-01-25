package quests;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.generationfuture.game.*;

public class Quest1 extends Quest {
    
    public Quest1 (Player player, Items items) throws SlickException {
        super(player, items);
        Questimage[0] = new Image("GameData/Cache/Quests/Quest1_1.png");
        
        Questhinweis = "Sammle 10 Stück Holz.";
        questwindow.println("Sammle 10 Stück Holz!<br>Die benötigst du z.B.,<br>wenn du ein Lagerfeuer anzünden möchtest, ...<br><br>Belohnung:<br>-weitere 10 Stück Holz");
    }
    
    @Override
    public void update () {
        
        if (items.Holz >= 11) {
            this.QuesthasFinished = true;
        }
        
    }
    
    @Override
    public void reward () {
        items.Holz = items.Holz + 10;
    }
    
}
