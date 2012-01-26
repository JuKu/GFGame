package net.generationfuture.game;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import org.newdawn.slick.Graphics;
import java.lang.reflect.Method;

public class QuestManager extends Thread {
    
    private Quest Questliste[];
    private GFGame gfgame;
    private Player player;
    private Items items;
    private int FinishedQuests[];
    private int FinishedQuestCounter = 0;
    private int width = 150;
    private int height = 50;
    
    private Boolean beenden = false;
    private String QuestListe_[];
    private int x = 10;
    private int y = 150;
    
    private Config config;
    
    public Quest windowQuest = null;
    
    public QuestManager (GFGame gfgame, Player player, Items items, Config config) {
        //
        Questliste = new Quest[4];//Max. 3 Quests kann man gleichzeitig annehmen.
        this.config = config;
        FinishedQuests = new int[100];
        findQuests();
    }
    
    public void wechsleAnsicht () {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                Questliste[i].wechsleAnsicht();
            }
            
        }
        
    }
    
    public void paintQuests (Graphics g) {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                Questliste[i].setPositionsData(x + 10, y + 10 + (i * (height + 20)));
                Questliste[i].paint(g);
                
                if (Questliste[i].isWindow()) {
                    windowQuest = Questliste[i];//System.out.println("QuestWindow.");
                }
            }
            
        }
        
    }
    
    public Boolean createNewQuest (String className) {
        
        Quest quest = null;
        Class clazz;
        
        FileClassLoader fcl =
            new FileClassLoader(config.getCacheFolder() + "Quests/" + className + "/");
        
        try {
            clazz = fcl.loadClass(/*"Test"*/className);
            if (clazz != null) {
                Method method =
                    clazz.getMethod("main", new Class[] { String[].class });
                if (method != null)
                    method.invoke(null, new Object[] { null });
            }
 
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        } catch (SecurityException e) {
            //e.printStackTrace();
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            //e.printStackTrace();
        }
        
        //quest = clazz.newInstance();
        return createNewQuest(quest);
        
    }
    
    public Boolean createNewQuest (Quest quest) {
        
        int i_ = 0;
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] == null) {
                Questliste[i] = quest;
                i_ = 1;
                Questliste[i].start();//Startet Thread, falls Quest einen benötigt.
                break;
            }
            
        }
        
        if (i_ == 1) {
            return true;//Quest wurde erfolgreich hinzugefügt.
        } else {
            return false;//Quest konnte nicht hinzugefügt werden, da alle Quest-Speicherplätze schon belegt sind.
        }
        
    }
    
    public Boolean mouseOver (int x, int y, Boolean isMouseMoved) {
        
        if (windowQuest != null) {
            isMouseMoved = windowQuest.WindowMouseOver(x, y);
        }
        
        if (!isMouseMoved) {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                Questliste[i].mouseOver(x, y);
            }
            
        }
        
        }
        
        return isMouseMoved;
        
    }
    
    public Boolean mouseClicked (int x, int y, Boolean isMouseClicked) {//Wird aufgerufen, wenn Maus auf etwas geklickt wurde
        
        //return false;//Auf keinen Quest-Gegenstand geklickt.
        //System.out.println("QuestManager mouseClicked.");
        if (windowQuest != null) {
            isMouseClicked = windowQuest.WindowMouseClicked(x, y);
        }
        
        if (!isMouseClicked) {
        
        for (int i = 0; i < Questliste.length; i++) {
            
            if (Questliste[i] != null) {
                Questliste[i].mouseClicked(x, y);
            }
            
        }
        
        }
        
        return isMouseClicked;
        
    }
    
    public final void findQuests () {
        
        File file = new File("GamaData/Cache/Quests");
        
        if (!file.exists()) {
            file.mkdir();
        }
        
        try {
        
        String dateien[] = new String[100];
        dateien[0] = "";
        
        dateien = file.list();
        
        int i = 0;
        
        //for (int i_ = 0; i_ < dateien.length; i_++) {
            
            /*try {
            
            File file_ = new File(dateien[i_]);*/
            /*if (!"README".equals(dateien[i_]) && ".class".equals(file_.getName().substring(0, file_.getName().lastIndexOf(".")))) {
                System.out.println("Datei: " + dateien[i_]);
            }*/
            
            /*} catch (NullPointerException ex) {
                System.err.println("NullPointerException.");
            }*/
            
        //}
        
        } catch (NullPointerException ex) {
            System.err.println("NullPointerException.");
        }
        
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
                    
                    try {
                        Questliste[i].update();
                    } catch (NullPointerException ex) {
                        //
                    }
                
                }
            
            }
        
        }
        
    }
    
}
