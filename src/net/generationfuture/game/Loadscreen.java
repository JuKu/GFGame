package net.generationfuture.game;

import java.awt.*;
import javax.swing.*;

public class Loadscreen extends JFrame {
    
    Image image;
    String text = "Ladetext";
    
    public Loadscreen () {
        this.setSize(400, 200);
        this.setTitle("GFGame loading");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        image = Toolkit.getDefaultToolkit().getImage("materials/loadscreen_.png");
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
        g.drawString(this.text, 200, 200);
    }
    
    public void setText (String text) {
        this.text = text;
        this.repaint();
    }
    
    public void exit () {
        this.setVisible(false);
    }
    
}
