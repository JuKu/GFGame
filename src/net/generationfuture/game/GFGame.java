
package net.generationfuture.game;

import org.newdawn.slick.*;

public class GFGame extends BasicGame{

    // @param args
     
    public GFGame()
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
 
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
 
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
 
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new GFGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
