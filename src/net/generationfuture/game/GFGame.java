
package net.generationfuture.game;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
    Image playerposImage = null;
    TiledMap map = null;
    
    int x = 0;
    int y = 0;
    
    int minimapx = 248;
    int minimapy = 200;
    
    public GFGame()
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/firstmystery.tmx","materials");
        playerposImage = new Image("materials/point.png");
     }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {x--;}
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {x++;}
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {y--;}
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {y++;}
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        map.render(0,0,x-400,y-300,800,600);
        map.render(0,0,x-60,y-60,120,120);
        playerposImage.draw(54, 54);
        playerposImage.draw(394, 294);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         
         AppGameContainer app = new AppGameContainer(new GFGame());
         //Dies ist JuKus erster Kommentar in dieser Datei. ;-)
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
