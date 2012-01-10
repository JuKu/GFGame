package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree1 extends plant {
    
    public Tree1 (int x, int y, Image picture1, String name) throws SlickException {
        createObject (name, picture1, x, y);
        
        tipping1 = new Image("materials/trees/tree1_/fir C tipping over0000.bmp",new Color(94, 66, 41, 255));
        tipping2 = new Image("materials/trees/tree1_/fir C tipping over0001.bmp",new Color(94, 66, 41, 255));
        tipping3 = new Image("materials/trees/tree1_/fir C tipping over0002.bmp",new Color(94, 66, 41, 255));
        tipping4 = new Image("materials/trees/tree1_/fir C tipping over0003.bmp",new Color(94, 66, 41, 255));
        tipping5 = new Image("materials/trees/tree1_/fir C tipping over0004.bmp",new Color(94, 66, 41, 255));
        tipping6 = new Image("materials/trees/tree1_/fir C tipping over0005.bmp",new Color(94, 66, 41, 255));
        tipping7 = new Image("materials/trees/tree1_/fir C tipping over0006.bmp",new Color(94, 66, 41, 255));
        tipping8 = new Image("materials/trees/tree1_/fir C tipping over0007.bmp",new Color(94, 66, 41, 255));
        tipping9 = new Image("materials/trees/tree1_/fir C tipping over0008.bmp",new Color(94, 66, 41, 255));
        
        growing1 = new Image("materials/trees/tree1_/fir C growing 0001.bmp",new Color(94, 66, 41, 255));
        growing2 = new Image("materials/trees/tree1_/fir C growing 0002.bmp",new Color(94, 66, 41, 255));
        growing3 = new Image("materials/trees/tree1_/fir C growing 0003.bmp",new Color(94, 66, 41, 255));
        growing4 = new Image("materials/trees/tree1_/fir C growing 0004.bmp",new Color(94, 66, 41, 255));
        growing5 = new Image("materials/trees/tree1_/fir C growing 0005.bmp",new Color(94, 66, 41, 255));
        growing6 = new Image("materials/trees/tree1_/fir C growing 0006.bmp",new Color(94, 66, 41, 255));
        
        growing7 = new Image("materials/trees/tree1_/fir C growing 0007.bmp",new Color(94, 66, 41, 255));
    }
    
}
