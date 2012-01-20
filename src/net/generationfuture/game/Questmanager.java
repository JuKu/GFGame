package net.generationfuture.game;

import org.newdawn.slick.Graphics;

public class QuestManager extends Thread {
    
    private Quest Questliste[];
    private GFGame gfgame;
    private Player player;
    private Items items;
    private int FinishedQuests[];
    private int FinishedQuestCounter = 0;
    
    private Boolean beenden = false;
    
    public QuestManager (GFGame gfgame, Player player, Items items) {
        //
        Questliste = new Quest[4];//Max. 3 Quests kann man gleichzeitig annehmen.
        
        FinishedQuests = new int[100];
    }
    
    public void paintQuests (Graphics g) {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                Questliste[i].paint(g);
            }
            
        }
        
    }
    
    public Boolean createNewQuest (Quest quest) {
        
        int i_ = 0;
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] == null) {
                Questliste[i] = quest;
                i_ = 1;
                Questliste[i].start();//Startt Thread, falls Quest einen benötigt.
                break;
            }
            
        }
        
        if (i_ == 1) {
            return true;//Quest wurde erfolgreich hinzugefügt.
        } else {
            return false;//Quest konnte nicht hinzugefügt werden, da alle Quest-Speicherplätze schon belegt sind.
        }
        
    }
    
    public void mouseOver (int x, int y) {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                //
            }
            
        }
        
    }
    
    public Boolean mouseClicked (int x, int y) {//Wird aufgerufen, wenn Maus auf etwas geklickt wurde
        return false;//Auf keinen Quest-Gegenstand geklickt.
    }
    
    @Override
    public void run () {//Überprüft die Quest-Daten
        
        while (!beenden) {
        
            for (int i = 0; i < Questliste.length; i++) {
            
                if (Questliste[i] != null) {
                
                    //
                    Boolean QuesthasFinished = Questliste[i].checkQuestFinish();
                
                    if (QuesthasFinished) {
                    
                        Boolean Quest_ = Questliste[i].nextQuestStufe();
                    
                        if (!Quest_) {//Quest ist fertig.
                            Questliste[i].reward();//Player belohnen
                            FinishedQuests[this.FinishedQuestCounter] = Questliste[i].getQuestID();
                            this.FinishedQuestCounter++;
                            Questliste[i] = null;//Quest löschen
                        } else {
                            Questliste[i].rewardQuestStufe();
                        }
                    
                    }
                
                    Questliste[i].update();
                
                }
            
            }
        
        }
        
    }
    
}
