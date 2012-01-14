package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class NeedsDisplay {
    
    Player player;
    
    public NeedsDisplay (Player player) {
        this.player = player;
    }
    
    public void paint (Graphics g) {
        
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
        
        //Hygiene
        
        int hygiene_anzeige_x = 20;//20;
        int hygiene_anzeige_y = 550;//540;
        
        int hygiene_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(hygiene_anzeige_x, hygiene_anzeige_y, hygiene_length, 20, 50, 50);
        
        if (player.isDirty()) {
            g.setColor(Color.red);
        } else if (player.getHygiene() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getHygiene() <= 0) {
            player.addHygiene(1);
        }
        
        g.fillRoundRect(hygiene_anzeige_x, hygiene_anzeige_y, player.getHygiene() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Hygiene", hygiene_anzeige_x + 30, hygiene_anzeige_y);
        
        //Harndrang
        
        int harndrang_anzeige_x = 240;//20;
        int harndrang_anzeige_y = 550;//540;
        
        int harndrang_length = 200;//120;
        
        g.setColor(Color.blue);
        g.fillRoundRect(harndrang_anzeige_x, harndrang_anzeige_y, harndrang_length, 20, 50, 50);
        
        if (player.isHarndrang()) {
            g.setColor(Color.red);
        } else if (player.getHarndrang() < 40) {
            g.setColor(Color.orange);
        }  else {
            g.setColor(Color.green);
        }
        
        if (player.getHarndrang() <= 0) {
            player.addHarndrang(1);
        }
        
        g.fillRoundRect(harndrang_anzeige_x, harndrang_anzeige_y, player.getHarndrang() * 2, 20, 50, 50);
        
        g.setColor(Color.white);
        g.drawString("Harndrang", harndrang_anzeige_x + 30, harndrang_anzeige_y);
        
    }
    
}
