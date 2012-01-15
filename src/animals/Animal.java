package animals;

import objects.ObjectMenu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Animal extends Object {
    
    protected int picture_counter = 0;
    
    protected Image[] stopped= new Image[4];
    
    protected Image[] walking_left = new Image[8];
    
    protected Image[] walking_right = new Image[8];
    
    protected Image[] walking_for = new Image[8];
    
    protected Image[] walking_back = new Image[8];
    
    private boolean walking = true;
    private int orientation = 3;
    
    protected int x = 50;
    protected int y = 50;
    
    protected int durst = 100;
    protected int hunger = 100;
    
    protected Boolean isIll = false;
    protected Boolean sitting = false;
    
    protected int width = 96;
    protected int height = 96;
    
    protected int AnimalID = 0;
    protected int id = 0;
    
    public static int animal_counter = 0;
    protected Boolean mouseMoved = false;
    
    protected String name = "";
    protected AnimalMenu animalmenu;
    
    public Animal (String name, int x, int y) throws SlickException {
        super();
        this.x = x;
        this.y = y;
        this.id = ++Animal.animal_counter;
        this.name = name;
        
        animalmenu = new AnimalMenu(this);
    }
    
    public void walkingLeft () {
        orientation = 1;
        walking = true;
    }
    
    public void walkingRight () {
        orientation = 3;
        walking = true;
    }
    
    public void walkingFront () {
        orientation = 2;
        walking = true;
    }
    
    public void walkingBack () {
        orientation = 0;
        walking = true;
    }
    
    public void paint (Graphics g,double xp,double yp) {
        
        if (!walking) {
            stopped[orientation].draw(x-(int)xp,y-(int)yp);
        } else {
            
            if (picture_counter >= 7) {
                picture_counter = -1;
                walking = false;
            }
            
            picture_counter++;

            switch(orientation) {
                case 1: walking_left[picture_counter].draw(x-(int)xp, y-(int)yp); break;
                case 2: walking_for[picture_counter].draw(x-(int)xp, y-(int)yp); break;
                case 3: walking_right[picture_counter].draw(x-(int)xp, y-(int)yp); break;
               default: walking_back[picture_counter].draw(x-(int)xp, y-(int)yp); break;         
            }
        }
        
        if (mouseMoved) {
            paintMouseOver(g,xp,yp);
        }
        
    }
    
    public void paintMenu (Graphics g) {
        animalmenu.paint(g);
    }
    
    public AnimalMenu getAnimalMenu () {
        return animalmenu;
    }
    
    public void scroll (int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    public void drink (int drink) {
        this.durst = this.durst + drink;
    }
    
    public void eat (int eat) {
        this.hunger = this.hunger + eat;
    }
    
    public void move () {
        
        walkingRight();
        
        switch(orientation) {
            case 1: x--; break;
            case 3: x++; break;
            case 2: y--; break;
            default: y++; break;
        }
        
    }
    
    public Boolean mouseMoved (int x, int y) {
        
        if (x > this.x && x < this.x + width) {
            
            if (y > this.y && y < this.y + height) {
                mouseMoved = true;
                return true;
            } else {
                mouseMoved = false;
                return false;
            }
            
        } else {
            mouseMoved = false;
            return false;
        }
        
    }
    
    public void paintMouseOver (Graphics g,double xp,double yp) {
        //System.out.println("paintMouseMoved.");
        g.drawString("" + this.name, x-(int)xp, y-(int)yp);
        
        g.drawString("Object-ID: " + AnimalID, x-(int)xp, y-(int)yp + 15);
        g.drawString("ID: " + id, x-(int)xp, y + 40);
        g.drawString("Hunger: " + hunger, x-(int)xp, y-(int)yp + 60);
        g.drawString("Durst: " + durst, x-(int)xp, y-(int)yp + 80);
    }
    
    public Boolean isClicked (int x, int y) {
        
        if (x > this.x && x < this.x + width) {
            
            if (y > this.y && y < this.y + height) {
                return true;
            } else {
                return false;
            }
            
        } else {
            return false;
        }
        
    }
    
}
