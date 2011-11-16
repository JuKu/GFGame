
package net.generationfuture.game;

import org.newdawn.slick.*;

public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
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
        minimap = new Image("materials/mystery.jpg");
     }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
 
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        minimap.draw(0, 0);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         
         AppGameContainer app = new AppGameContainer(new GFGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
