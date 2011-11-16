
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
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {x++;}
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {x--;}
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {y++;}
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {y--;}
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        map.render(0,0,x-400,y-300,800,600);
        map.render(0,0,x-40,y-40,80,80);
        playerposImage.draw(34, 34);
        playerposImage.draw(400, 300);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         
         AppGameContainer app = new AppGameContainer(new GFGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
