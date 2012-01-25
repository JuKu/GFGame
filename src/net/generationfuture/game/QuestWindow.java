package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class QuestWindow {
    
    protected int windowNumber = 1;
    protected Boolean windowShow = false;
    protected Quest quest;
    protected Boolean paintWindow = false;
    protected Color window_bg = Color.white;
    protected Boolean closeButtonMoved = false;
    protected String windowBody = ""; 
    protected Color WindowSchriftColor = Color.blue;
    
    protected int image_x = 20;
    protected int image_y = 20;
    
    protected Image windowImage = null;
    
    public QuestWindow (Quest quest) {
        this.quest = quest;
    }
    
    public void setBGColor (Color color) {
        this.window_bg = color;
    }
    
    public void setImage (Image image, int x, int y) {
        this.windowImage = image;
        
        this.image_x = x;
        this.image_y = y;
    }
    
    public void paint (Graphics g) {
        
        if (this.paintWindow) {
            paintWindow(g);
        }
        
    }
    
    public void paintWindow (Graphics g) {
        
        g.setColor(window_bg);
        g.fillRect(200, 100, 550, 350);
        
        if (windowImage != null) {
            windowImage.draw(200 + image_x, 100 + image_y);
        }
        
        Color closeColor = Color.red;
        Color closeColor_ = Color.white;
        
        if (closeButtonMoved) {
            closeColor = Color.blue;
        }
        
        g.setColor(closeColor);
        g.fillRect(650, 100, 100, 40);
        g.setColor(closeColor_);
        g.drawString("Close", 660, 110);
        
        paintWindowBody(g);
        g.setColor(Color.white);
        
    }
    
    public void showWindow (Boolean showWindow) {
        this.paintWindow = showWindow;
    }
    
    public void paintWindowBody (Graphics g) {
        
        String[] param = /*(*/windowBody/*.split("<br>")[1])*/.split("<br>");
        //System.out.println("" + param[0] + ", " + param[1]);
        
        g.setColor(WindowSchriftColor);
        
        for (int i = 0; i < param.length; i++) {
            g.drawString("" + param[i], 250, 120 + (i * 20));
        }
        
    }
    
    public void writeTextOnWindowBody (String string) {
        windowBody = windowBody + "<br>" + string;
    }
    
    public void closeWindow () {
        this.paintWindow = false;
    }
    
    public Boolean isWindow () {
        return this.paintWindow;
    }
    
    public Boolean WindowMouseOver (int mouse_x, int mouse_y) {
        
        Boolean mouseMoved = false;//System.out.println("WindowMouseOver.");
        
        if (mouse_x > 650 && mouse_x < 650 + 100) {
            
            if (mouse_y > 100 && mouse_y < 100 + 40) {
                //System.out.println("mouseOver.");
                //mouseOver = true;
                
                mouseMoved = true;
                this.closeButtonMoved = true;
            } else {
                this.closeButtonMoved = false;
                //mouseOver = false;
            }
            
        } else {
            this.closeButtonMoved = false;
            //mouseOver = false;
        }
        
        return mouseMoved;
        
    }
    
    public Boolean WindowMouseClicked (int mouse_x, int mouse_y) {
        
        Boolean mouseMoved = false;//System.out.println("WindowMouseOver.");
        //System.out.println("WindowMouseClicked., mouse_x: " + mouse_x + ", mouse_y: " + mouse_y + ".");
        if (mouse_x > 650 && mouse_x < 650 + 100) {
            
            if (mouse_y > 100 && mouse_y < 100 + 40) {
                //System.out.println("mouseOver.");
                //mouseOver = true;
                
                this.closeWindow();//System.out.println("wmc: yes.");
                
                mouseMoved = true;
                //this.closeButtonMoved = true;
            } else {//System.out.println("wmc: no, y falsch. mouse_y: " + mouse_y + ".");
                //this.closeButtonMoved = false;
                //mouseOver = false;
            }
            
        } else {//System.out.println("wmc: no, x falsch.");
            //this.closeButtonMoved = false;
            //mouseOver = false;
        }
        
        return mouseMoved;
        
    }
    
    public void println (String string) {
        windowBody = windowBody + string + "<br>";
    }
    
    public void print (String string) {
        windowBody = windowBody + string;
    }
    
    public void removeWindowText () {
        windowBody = "";
    }
    
    public void append (String string) {
        windowBody = windowBody + string;
    }
    
}
