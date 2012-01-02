package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rabbit extends Animal {
    
    public Rabbit (int x, int y) throws SlickException {
        super(x, y);
        
        stopped_left = new Image("materials/animals/rabbit/stopped0002.bmp",new Color(97, 68, 43, 255));
        stopped_right = new Image("materials/trees/tree1_/fir C tipping over0001.bmp",new Color(97, 68, 43, 255));
        stopped_front = new Image("materials/trees/tree1_/fir C tipping over0002.bmp",new Color(97, 68, 43, 255));
        stopped_back = new Image("materials/trees/tree1_/fir C tipping over0003.bmp",new Color(97, 68, 43, 255));
        
        walking_left1 = new Image("materials/animals/rabbit/walking w0000.bmp",new Color(97, 68, 43, 255));
        walking_left2 = new Image("materials/animals/rabbit/walking w0001.bmp",new Color(97, 68, 43, 255));
        walking_left3 = new Image("materials/animals/rabbit/walking w0002.bmp",new Color(97, 68, 43, 255));
        walking_left4 = new Image("materials/animals/rabbit/walking w0003.bmp",new Color(97, 68, 43, 255));
        walking_left5 = new Image("materials/animals/rabbit/walking w0004.bmp",new Color(97, 68, 43, 255));
        walking_left6 = new Image("materials/animals/rabbit/walking w0005.bmp",new Color(97, 68, 43, 255));
        walking_left7 = new Image("materials/animals/rabbit/walking w0006.bmp",new Color(97, 68, 43, 255));
        walking_left8 = new Image("materials/animals/rabbit/walking w0007.bmp",new Color(97, 68, 43, 255));
        
        walking_right1 = new Image("materials/animals/rabbit/walking e0000.bmp",new Color(97, 68, 43, 255));
        walking_right2 = new Image("materials/animals/rabbit/walking e0001.bmp",new Color(97, 68, 43, 255));
        walking_right3 = new Image("materials/animals/rabbit/walking e0002.bmp",new Color(97, 68, 43, 255));
        walking_right4 = new Image("materials/animals/rabbit/walking e0003.bmp",new Color(97, 68, 43, 255));
        walking_right5 = new Image("materials/animals/rabbit/walking e0004.bmp",new Color(97, 68, 43, 255));
        walking_right6 = new Image("materials/animals/rabbit/walking e0005.bmp",new Color(97, 68, 43, 255));
        walking_right7 = new Image("materials/animals/rabbit/walking e0006.bmp",new Color(97, 68, 43, 255));
        walking_right8 = new Image("materials/animals/rabbit/walking e0007.bmp",new Color(97, 68, 43, 255));
    }
    
}
