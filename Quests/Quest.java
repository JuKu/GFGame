import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Quest extends Thread {

	protected int id = 0;
    protected String Questname = "";
    protected Boolean UserQuest = true;
    protected Boolean FreakQuest = false;
    protected Boolean isShown = false;
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
	
	public Quest (Player player, Items items) {
	//
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
        //Quest zeichnen
    }
    
    public void setPositionsData (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void paintQuestIcon (Graphics g) {
        
   	//
        
    }
    
    public void mouseOver (int x, int y) {
        //
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