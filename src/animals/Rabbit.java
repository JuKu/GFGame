package animals;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rabbit extends Animal {
    
    public Rabbit (int x, int y) throws SlickException {
        super(x, y);
        
        stopped_left = new Image("materials/trees/tree1_/fir C tipping over0000.bmp",new Color(94, 66, 41, 255));
        stopped_right = new Image("materials/trees/tree1_/fir C tipping over0001.bmp",new Color(94, 66, 41, 255));
        stopped_front = new Image("materials/trees/tree1_/fir C tipping over0002.bmp",new Color(94, 66, 41, 255));
        stopped_back = new Image("materials/trees/tree1_/fir C tipping over0003.bmp",new Color(94, 66, 41, 255));
    }

    @Override
    public void move() {
        //
    }

    @Override
    public void drink() {
        //
    }

    @Override
    public void eat() {
        //
    }

    @Override
    public void walking_left() {
        //
    }

    @Override
    public void walking_right() {
        //
    }
    
}
