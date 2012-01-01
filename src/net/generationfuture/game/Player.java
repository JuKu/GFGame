package net.generationfuture.game;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
    
    private int energie = 100;//Energie (Schlafen)
    private int hunger = 100;//Hunger
    private int hygiene = 100;//Hygiene (waschen)
    private int harndrang = 100;//Harndrang (Toilette)
    
    private Boolean sleeping = false;//Player schläft
    private Boolean walking_right = false;
    private Boolean walking_left = false;
    private Boolean standing_right = false;
    private Boolean standing_left = false;
    private Boolean walking_for = false;
    private Boolean walking_back = false;
    private Boolean standing_for = false;
    private Boolean standing_back = false;
    
    Image picture1;
    BufferedImage bufferedimage;
    
    private Image image;
    private Image timage;
    
    private Image stopped1;
    private Image stopped2;
    private Image stopped3;
    private Image stopped4;
    
    private Image walking_left1;
    private Image walking_left2;
    private Image walking_left3;
    private Image walking_left4;
    private Image walking_left5;
    private Image walking_left6;
    private Image walking_left7;
    private Image walking_left8;
    private Image walking_left9;
    
    int picture_counter;
    
    /*****************************************
     * 
     * Alle Bedürfnisse müssen ausreichend versorgt werden, sonst wird der Spieler mit einem Rettungshubschrauber abgeholt.
     * 
     ****************************************/
    
    int counter1 = 0;
    
    public Player () throws SlickException {
        picture1 = new Image("materials/characters/test/stopped0000.bmp");
        timage = new Image("materials/characters/test/stopped0000.bmp",new Color(/*94,66,41,255*/97, 68, 43, 255));
        
        picture1 = timage;
        
        stopped1 = new Image("materials/characters/test/stopped0000.bmp",new Color(97, 68, 43, 255));
        stopped2 = new Image("materials/characters/test/stopped0002.bmp",new Color(97, 68, 43, 255));
        stopped3 = new Image("materials/characters/test/stopped0004.bmp",new Color(97, 68, 43, 255));
        stopped4 = new Image("materials/characters/test/stopped0006.bmp",new Color(97, 68, 43, 255));
        
        walking_left1 = new Image("materials/characters/test/walking e0000.bmp",new Color(97, 68, 43, 255));
        walking_left2 = new Image("materials/characters/test/walking e0001.bmp",new Color(97, 68, 43, 255));
        walking_left3 = new Image("materials/characters/test/walking e0002.bmp",new Color(97, 68, 43, 255));
        walking_left4 = new Image("materials/characters/test/walking e0003.bmp",new Color(97, 68, 43, 255));
        walking_left5 = new Image("materials/characters/test/walking e0004.bmp",new Color(97, 68, 43, 255));
        walking_left6 = new Image("materials/characters/test/walking e0005.bmp",new Color(97, 68, 43, 255));
        walking_left7 = new Image("materials/characters/test/walking e0006.bmp",new Color(97, 68, 43, 255));
        walking_left8 = new Image("materials/characters/test/walking e0007.bmp",new Color(97, 68, 43, 255));
        walking_left9 = new Image("materials/characters/test/stopped0006.bmp",new Color(97, 68, 43, 255));
        
        picture_counter = 0;
        
        /*Image src = picture1.getImage( "gatesInAlbuquerque.jpg" );
        ImageFilter colorfilter = new GrayFilter();
        ImageProducer imageprod = new FilteredImageSource( src.getSource(), colorfilter );
        Image img = createImage( imageprod );*/
        //5E4229
        
        //TransparentFilter transparentfilter = new TransparentFilter(Color.decode("0x5E4229"));
    }
    
    public void eat (int eat) {
        hunger = hunger + eat;
        if (hunger > 100) { hunger = 100; }
    }
    
    public void sleep (int sleep) {
        energie = energie + sleep;
        if (energie > 100) {
            energie = 100;
        }
    }
    
    public void move () {
        
        if (counter1 > 100) {
            
            counter1 = 0;
            energie--;
            hunger--;
            hygiene--;
            harndrang--;
            
        } else {
            counter1++;
        }
        
    }
    
    public void wake () {
        sleeping = false;
    }
    
    public Boolean isHungry () {
        
        if (hunger < 20) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public Boolean isSleepy () {
        
        if (energie < 20) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public Boolean isDirty () {
        
        if (hygiene < 20) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public Boolean isHarndrang () {
        
        if (harndrang < 20) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public int getHunger () {
        return hunger;
    }
    
    public int getEnergie () {
        return energie;
    }
    
    public int getHygiene () {
        return hygiene;
    }
    
    public int getHarndrang () {
        return harndrang;
    }
    
    public void addHunger (int hunger) {
        
        this.hunger = this.hunger + hunger;
        if (hunger > 100) { hunger = 100; }
        
        if (hunger <= 0) { 
            hunger = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
    public void addEnergie (int energie) {
        
        this.energie = this.energie + energie;
        if (energie > 100) { energie = 100; }
        
        if (energie <= 0) { 
            energie = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
    public void addHygiene (int hygiene) {
        
        this.hygiene = this.hygiene + hygiene;
        if (hygiene > 100) { hygiene = 100; }
        
        if (hygiene <= 0) { 
            hygiene = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
    public void addHarndrang (int harndrang) {
        
        this.harndrang = this.harndrang + harndrang;
        if (energie > 100) { energie = 100; }
        
        if (energie <= 0) { 
            energie = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
    public Image getImage () {
        
        if (standing_for) {
            return stopped1;
        } else if (standing_left) {
            return stopped2;
        } else if (standing_back) {
            return stopped3;
        } else if (standing_right) {
            return stopped4;
        } else if (walking_left) {
            
            if (picture_counter == 8) {
                picture_counter = 0;
            }
            
            picture_counter++;
            
            if (picture_counter == 0) {
                return walking_left1;
            } else if (picture_counter == 1) {
                return walking_left2;
            } else if (picture_counter == 2) {
                return walking_left3;
            } else if (picture_counter == 3) {
                return walking_left4;
            } else if (picture_counter == 4) {
                return walking_left5;
            } else if (picture_counter == 5) {
                return walking_left6;
            } else if (picture_counter == 6) {
                return walking_left7;
            } else if (picture_counter == 7) {
                return walking_left8;
            } else {
                return walking_left9;
            }          
            
        } else {
            return stopped1;
        }
        
    }
    
    public void standingLeft () {
        standing_for = false;
        standing_left = true;
        standing_back = false;
        standing_right = false;
        
        picture_counter = 0;
    }
    
    public void standingRight () {
        standing_for = false;
        standing_left = false;
        standing_back = false;
        standing_right = true;
        
        picture_counter = 0;
    }
    
    public void standingFor () {
        standing_for = true;
        standing_left = false;
        standing_back = false;
        standing_right = false;
        
        picture_counter = 0;
    }
    
    public void standingBack () {
        standing_for = false;
        standing_left = false;
        standing_back = true;
        standing_right = false;
        
        picture_counter = 0;
    }
    
    public void walkingLeft () {
        standing_for = false;
        standing_left = false;
        standing_back = false;
        standing_right = false;
        walking_left = true;
        walking_right = false;
        walking_for = false;
        walking_back = false;
        
        //picture_counter = 0;
    }
    
    public BufferedImage makeTransparent (BufferedImage img) {
        
        for (int i = img.getWidth() - 1; i > -1; i--) {
	for (int j = img.getHeight() - 1;  j > -1; j--) {
		if (img.getRGB(i, j) == new Color(255,255,255).getAlpha()) {
			img.setRGB(i, j, new Color(0, 0, 0, 0).getAlpha());
		}
	}
        
        }
        
        return img;
        
    }
    
}
