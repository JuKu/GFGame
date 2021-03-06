package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree1 extends plant {
    
    public Tree1 (int x, int y, Image picture1, String name) throws SlickException {
        createObject (name, picture1, x, y);
        
        for(int i=0;i<9;++i) {
            tippingi[i] = new Image("materials/trees/tree1_/fir C tipping over000"+i+".bmp",new Color(94, 66, 41, 255));
        }
        
        for(int i=0;i<7;++i) {
            growingi[i] = new Image("materials/trees/tree1_/fir C growing 000"+(i+1)+".bmp",new Color(94, 66, 41, 255));
        }
        
        objectmenu.addMenu("gießen", "grow");
        objectmenu.addMenu("fällen", "tipping");
        
    }
    
    @Override
    public void actionPerformed (String command) {
            
        if ("tipping".equals(command)) {
            tipping();
        } else if ("grow".equals(command)) {
            grow();
        }
    
    }
    
}
