package objects;

import net.generationfuture.game.Items;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree2 extends plant {
    
    public Tree2 (int x, int y, Image picture1, Items items) throws SlickException {
        createObject (picture1, items, x, y);
    }
    
}
