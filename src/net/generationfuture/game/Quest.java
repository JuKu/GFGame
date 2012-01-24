package net.generationfuture.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Quest extends Thread {
    
    protected int id = 0;
    protected String Questname = "";
    protected Boolean UserQuest = true;
    protected Boolean FreakQuest = false;
    protected Boolean isShown = true;
    protected Boolean isLook = false;
    protected int windowNumber = 1;
    protected Boolean windowShow = false;
    protected int x = 0;
    protected int y = 0;
    protected Image Questimage[];
    protected Boolean mouseOver = false;
    protected int height = 50;
    protected int width = 150;
    
    protected String Questhinweis = "";
    protected Boolean QuesthasFinished = false;//Quest wurde beendet.
    protected int QuestNumber = 0;
    protected int maxQuestNumber = 0;
    
    protected Player player;
    protected Items items;
    
    private Boolean paintWindow = false;
    protected Color window_bg = Color.white;
    
    protected Boolean closeButtonMoved = false;
    
    public Quest (Player player, Items items) {
        Questimage = new Image[10];//Questimage[0] ist das Hauptimage.
        this.player = player;
        
        this.items = items;
    }
    
    public void startQuest () {
        //Startet den Quest
        isShown = true;
    }
    
    @Override
    public void run () {
        //Überprüft Variablen und beendet gegebenenfalls den Quest.
    }
    
    public void paint (Graphics g) {
        
        if (isShown) {
            
        //Quest zeichnen
        paintQuestIcon(g);
        
        }
        
        if (paintWindow) {
            paintWindow(g);
        }
        
    }
    
    public void showWindow (Boolean showWindow) {
        this.paintWindow = showWindow;
    }
    
    public void paintWindow (Graphics g) {
        
        g.setColor(window_bg);
        g.fillRect(200, 100, 550, 350);
        
        Color closeColor = Color.red;
        Color closeColor_ = Color.white;
        
        if (closeButtonMoved) {
            closeColor = Color.blue;
        }
        
        g.setColor(closeColor);
        g.fillRect(650, 100, 100, 40);
        g.setColor(closeColor_);
        g.drawString("Close", 660, 110);
        
        g.setColor(Color.white);
        
    }
    
    public void closeWindow () {
        this.paintWindow = false;
    }
    
    public Boolean isWindow () {
        return this.paintWindow;
    }
    
    public void setPositionsData (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void paintQuestIcon (Graphics g) {
        
        Questimage[0].draw(x + 10, y + 10);
        int anzahl_zeichen = Questhinweis.toCharArray().length;
        
        if (mouseOver) {//Questhinweis anzeigen
            g.setColor(Color.white);
            g.fillRect(x + 10, y + 18, anzahl_zeichen * 9 + 20, 20);
            g.setColor(Color.blue);
            g.drawString(Questhinweis, x + 20, y + 20);
        }
        
        g.setColor(Color.white);
        
    }
    
    public Boolean mouseOver (int mouse_x, int mouse_y) {
        
        Boolean mouseMoved = false;
        
        if (mouse_x > this.x && mouse_x < this.x + width) {
            
            if (mouse_y > this.y && mouse_y < this.y + height) {
                //System.out.println("mouseOver.");
                mouseOver = true;
                
                mouseMoved = true;
            } else {
                mouseOver = false;
            }
            
        } else {
            mouseOver = false;
        }
        
        return mouseMoved;
        
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
        System.out.println("WindowMouseClicked., mouse_x: " + mouse_x + ", mouse_y: " + mouse_y + ".");
        if (mouse_x > 650 && mouse_x < 650 + 100) {
            
            if (mouse_y > 100 && mouse_y < 100 + 40) {
                //System.out.println("mouseOver.");
                //mouseOver = true;
                
                this.closeWindow();System.out.println("wmc: yes.");
                
                mouseMoved = true;
                //this.closeButtonMoved = true;
            } else {System.out.println("wmc: no, y falsch. mouse_y: " + mouse_y + ".");
                //this.closeButtonMoved = false;
                //mouseOver = false;
            }
            
        } else {//System.out.println("wmc: no, x falsch.");
            //this.closeButtonMoved = false;
            //mouseOver = false;
        }
        
        return mouseMoved;
        
    }
    
    /*public void mouseClicked (int mouse_x, int mouse_y) {
        //
    }*/
    
    public Boolean mouseClicked (int mouse_x, int mouse_y) {
        //System.out.println("mouseClicked.");
        Boolean mouseMoved = false;
        
        if (mouse_x > this.x && mouse_x < this.x + width) {
            
            if (mouse_y > this.y && mouse_y < this.y + height) {
                //System.out.println("mouseOver.");
                //mouseOver = true;
                
                mouseMoved = true;
            } else {
                //mouseOver = false;
            }
            
        } else {
            //mouseOver = false;
        }
        
        return mouseMoved;
        
    }
    
    public void wechsleAnsicht (Boolean show) {
        this.isShown = show;
    }
    
    public void wechsleAnsicht () {
        this.isShown = !this.isShown;
    }
    
    public Boolean getIsShown () {
        return this.isShown;
    }
    
    public Boolean checkQuestFinish () {
        return QuesthasFinished;
    }
    
    public int getQuestNumber () {
        return QuestNumber;
    }
    
    public int getQuestID () {
        return id;
    }
    
    public Boolean nextQuestStufe () {
        return false;//Quest geht nicht weiter
    }
    
    public void reward () {
        //Player für fertigstellen des Quests belohnen.
    }
    
    public void rewardQuestStufe () {
        //Player für fertigstellen einer Quest-Stufe belohnen.
    }
    
    public void update () {//Wird automatisch aufgerufen
        //
    }
    
}
