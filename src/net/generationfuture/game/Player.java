package net.generationfuture.game;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.print.attribute.standard.OrientationRequested;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
    
    private double x = 0;
    private double y = 0;
    
    private int energie = 100;//Energie (Schlafen)
    private int hunger = 100;//Hunger
    private int hygiene = 100;//Hygiene (waschen)
    private int harndrang = 100;//Harndrang (Toilette)
    
    private Boolean sleeping = false;//Player schläft
    private boolean walking = false;
    int orientation = 0;
    
    Image picture1;
    BufferedImage bufferedimage;
    
    private Image image;
    private Image timage;
    
    private Image[] stopped = new Image[4];
    
    private Image[] walking_lefti = new Image[8];
    private Image[] walking_righti = new Image[8];
    private Image[] walking_fori = new Image[8];
    
    private Image[] walking_backi = new Image[8];
    
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
        
        for(int i = 0;i<4;++i) {
            stopped[i] = new Image("materials/characters/test/stopped000"+(i*2)+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i=0;i<8;++i) {
            walking_lefti[i] = new Image("materials/characters/test/walking w000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        
        for(int i=0;i<8;++i) {
            walking_righti[i] = new Image("materials/characters/test/walking e000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i=0;i<8;++i) {
            walking_fori[i] = new Image("materials/characters/test/walking s000"+i+".bmp",new Color(97, 68, 43, 255));
        }
        for(int i=0;i<8;++i) {
            walking_backi[i] = new Image("materials/characters/test/walking n000"+i+".bmp",new Color(97, 68, 43, 255));
        }

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
    
    private void move () {
        
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
       if(!walking) {
            return stopped[orientation];
       }
       else {
            
            picture_counter++;
            
            if (picture_counter > 14) {
                picture_counter = 0;
                walking = false;
            }
            
            switch(orientation) {
                case 1:return walking_lefti[picture_counter/2];
                case 2:return walking_backi[picture_counter/2];
                case 3:return walking_righti[picture_counter/2];
                default:return walking_fori[picture_counter/2];
           }        
        }
     }
    
    public void standingLeft () {
        orientation = 1;
        walking = false;
        
        picture_counter = 0;
    }
    
    public void standingRight () {
        orientation = 3;
        walking = false;
        
        picture_counter = 0;
    }
    
    public void standingFor () {
        orientation = 2;
        walking = false;
        
        picture_counter = 0;
    }
    
    public void standingBack () {
        orientation = 0;
        walking = false;
        
        picture_counter = 0;
    }
    
    public void walkingLeft () {
        orientation = 1;
        walking = true;
        
        //picture_counter = 0;
    }
    
    public void walkingRight () {
        orientation = 3;
        walking = true;
        
        //picture_counter = 0;
    }
    
    public void walkingFor () {
        orientation = 2;
        walking = true;
        
        //picture_counter = 0;
    }
    
    public void walkingBack () {
        orientation = 0;
        walking = true;
        
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
    
    public void moveUp() {
        y--;
        move();
        walkingFor();
    }
    
    public void moveDown() {
        y++;
        move();
        walkingBack();
    }
    
    public void moveLeft() {
        x--;
        move();
        walkingLeft();
    }
    
    public void moveRight() {
        x++;
        move();
        walkingRight();
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setPos(int[] i) {
        if(i.length==2) { 
            x = i[0];
            y = i[1];
        }
    }
    
}
