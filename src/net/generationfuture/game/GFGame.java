package net.generationfuture.game;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

import objects.Object;
import objects.Tree1;

public class GFGame extends BasicGame{

    // @param args
    Image minimap = null;
    Image playerposImage = null;
    TiledMap map = null;
        
        Image tree1_picture1;
        Image tree1_picture2;
        Image tree1_picture3;
        Image tree1_picture4;

    int x = 0;
    int y = 0;
    
    int minimapx = 248;
    int minimapy = 200;
    
    Object objects[];
    Player player;
    
    public GFGame() throws SlickException
    {
        super("GFGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        minimap = new Image("materials/mystery.png");
        map = new TiledMap("materials/firstmystery.tmx","materials");
        playerposImage = new Image("materials/point.png");
        tree1_picture1 = new Image("src/materials/trees/tree1_/fir C ani0000.bmp");
        //objects[0] = new Tree1(1, 1, this.tree1_picture1);
        
        player = new Player();
     }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {x--;}
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {x++;}
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {y--;}
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {y++;}
        
        player.addHunger(-1);
        player.addEnergie(-1);
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        map.render(0,0,x-400,y-300,800,600);
        map.render(0,0,x-60,y-60,120,120);
        playerposImage.draw(54, 54);
        //playerposImage.draw(394, 294);
        //objects[0].paint(g);
        
        /*********************************
         * 
         * Bedürfnis-Anzeige
         * 
         ********************************/
        
        g.setColor(Color.blue);
        g.drawLine(0, 500, 800, 500);
        g.setColor(Color.white);
        g.fillRect(0, 500, 800, 800);
        g.setColor(Color.blue);
        g.drawString("Bedürfnisse", 20, 500);
        
        //Hunger
        
        int hunger_anzeige_x = 20;//20;
        int hunger_anzeige_y = 520;//540;
        
        int hunger_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(hunger_anzeige_x, hunger_anzeige_y, hunger_length, 20, 50, 50);
        
        if (player.isHungry()) {
            g.setColor(Color.red);
        } else if (player.getHunger() < 40) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.green);
        }
        
        if (player.getHunger() <= 0) {
            player.addHunger(1);
        }
        
        g.fillRoundRect(hunger_anzeige_x, hunger_anzeige_y, player.getHunger() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Hunger", hunger_anzeige_x + 30, hunger_anzeige_y);
        
        //Energie
        
        int energie_anzeige_x = 240;//20;
        int energie_anzeige_y = 520;//540;
        
        int energie_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(energie_anzeige_x, energie_anzeige_y, energie_length, 20, 50, 50);
        
        if (player.isSleepy()) {
            g.setColor(Color.red);
        } else if (player.getEnergie() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getEnergie() <= 0) {
            player.addEnergie(1);
        }
        
        g.fillRoundRect(energie_anzeige_x, energie_anzeige_y, player.getEnergie() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Energie", energie_anzeige_x + 30, energie_anzeige_y);
        
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
