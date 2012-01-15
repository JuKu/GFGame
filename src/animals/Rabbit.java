package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rabbit extends Animal {
    
    public Rabbit (String name, int x, int y) throws SlickException {
        super(name, x, y);
        
        
        for(int i =0;i<4;++i) {
            stopped[i] = new Image("materials/animals/rabbit/stopped000"+(i*2)+".bmp",new Color(97, 68, 43, 255));
        }
        
        for(int i=0;i<8;++i) {
           walking_left[i] = new Image("materials/animals/rabbit/walking w000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i=0;i<8;++i) {
            walking_right[i] = new Image("materials/animals/rabbit/walking e000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i =0; i<8;++i) {
            walking_for[i] = new Image("materials/animals/rabbit/walking n000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i= 0;i<8;++i) {
            walking_back[i] = new Image("materials/animals/rabbit/walking s000"+i+".bmp",new Color(97, 68, 43, 255));
        }
    }
    
}
