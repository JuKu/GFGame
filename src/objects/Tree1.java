package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree1 extends plant {
    
    public Tree1 (int x, int y, Image picture1) throws SlickException {
        createObject (picture1, x, y);
        
        tipping1 = new Image("materials/trees/tree1_/fir C tipping over0000.bmp",new Color(97, 68, 43, 255));
        tipping2 = new Image("materials/trees/tree1_/fir C tipping over0001.bmp",new Color(97, 68, 43, 255));
        tipping3 = new Image("materials/trees/tree1_/fir C tipping over0002.bmp",new Color(97, 68, 43, 255));
        tipping4 = new Image("materials/trees/tree1_/fir C tipping over0003.bmp",new Color(97, 68, 43, 255));
        tipping5 = new Image("materials/trees/tree1_/fir C tipping over0004.bmp",new Color(97, 68, 43, 255));
        tipping6 = new Image("materials/trees/tree1_/fir C tipping over0005.bmp",new Color(97, 68, 43, 255));
        tipping7 = new Image("materials/trees/tree1_/fir C tipping over0006.bmp",new Color(97, 68, 43, 255));
        tipping8 = new Image("materials/trees/tree1_/fir C tipping over0007.bmp",new Color(97, 68, 43, 255));
        tipping9 = new Image("materials/trees/tree1_/fir C tipping over0008.bmp",new Color(97, 68, 43, 255));
    }
    
}
