package objects;

import net.generationfuture.game.Items;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fireplace extends Object {
    
    protected Boolean isFire = false;
    protected Image[] animation_ = new Image[8];
    protected Image picture2;
    protected int kostetHolz = 2;
    
    Animation animation;
    Animation animation2;
    
    public Fireplace (int x, int y, Image picture1, Items items, String name) throws SlickException {
        createObject (picture1, items, x, y);
        
        animation = new Animation();
        picture2 = new Image("materials/objects/fireplace/feuerstelle unter flamme.bmp",new Color(145, 90, 45, 255));
        
        animation2 = new Animation();
        
        for(int i=0;i<8;++i) {
            animation_[i] = new Image("materials/objects/fireplace/flammen000"+i+".bmp",new Color(128, 128, 128, 255));
            animation.addFrame(animation_[i], 60);
        }
        
        for(int i=0;i<16 + 1;++i) {
            
            Image image_;
            
            if (i < 10) {
                image_ = new Image("materials/objects/fireplace/rauchwolke000"+i+".bmp",new Color(250, 250, 200, 255));
            } else {
                image_ = new Image("materials/objects/fireplace/rauchwolke00"+i+".bmp",new Color(250, 250, 200, 255));
            }
            
            animation2.addFrame(image_, 100);
        }
        
        /*for(int i=0;i<7;++i) {
            growingi[i] = new Image("materials/trees/tree1_/fir C growing 000"+(i+1)+".bmp",new Color(94, 66, 41, 255));
        }*/
        
        objectmenu.addMenu("anzünden", "makeFire");
        objectmenu.addMenu("Feuer löschen", "deleteFire");
        
    }
    
    @Override
    public void actionPerformed (String command) {
            
        if ("makeFire".equals(command)) {
            
            if (items.Holz >= 2) {
                isFire = true;
                items.Holz = items.Holz - 2;
            }
            //isFire = true;
        } else if ("deleteFire".equals(command)) {
            isFire = false;
        }
        
    }
    
    @Override
    public void paint (Graphics g,double xp,double yp) {
        
        if (!isFire) {
            picture.draw(x-(int)xp, y-(int)yp);
        } else {
            picture2.draw(x-(int)xp, y-(int)yp);
            animation.draw(x-(int)xp + 15, y-(int)yp + 14);
            
            animation2.draw(x-(int)xp + 5, y-(int)yp - 20);
        }
        
        this.xp = xp;
        this.yp = yp;
        
        if (mouseMoved) {
            paintMouseOver(g,xp,yp);
        }
    }
    
    @Override
    public void paintMouseOver (Graphics g,double xp,double yp) {
        //System.out.println("paintMouseMoved.");
        
        g.setColor(Color.gray);
        g.fillRect(x - 10 - (int) xp, y/* - 10*/ - (int) yp, 350, 40);
        
        g.setColor(Color.white);
        
        if (items.Holz >= this.kostetHolz) {
            g.drawString("Das anzünden kostet " + kostetHolz + " Stück Holz.", x-(int)xp, y-(int)yp);
        } else {
            g.setColor(Color.gray);
            g.fillRect(x - 10, y - 10, 500, 40);
        
            g.setColor(Color.white);
            g.drawString("Du hast nicht genügend Holz, um das Feuer anzuzünden.", x-(int)xp, y-(int)yp);
        }
        
        g.drawString("Du hast " + items.Holz + " Stücke Holz.", x-(int)xp, y-(int)yp + 15);
        
        //g.drawString("Object-ID: " + ObjectID, x-(int)xp, y-(int)yp + 15);
        //g.drawString("ID: " + id, x-(int)xp, y-(int)yp + 40);
    }
    
}
