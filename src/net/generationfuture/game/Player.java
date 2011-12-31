package net.generationfuture.game;

public class Player {
    
    private int energie = 100;//Energie (Schlafen)
    private int hunger = 100;//Hunger
    private int hygiene = 100;//Hygiene (waschen)
    private int harndrang = 100;//Harndrang (Toilette)
    
    private Boolean sleeping = false;//Player schläft
    
    /*****************************************
     * 
     * Alle Bedürfnisse müssen ausreichend versorgt werden, sonst wird der Spieler mit einem Rettungshubschrauber abgeholt.
     * 
     ****************************************/
    
    int counter1 = 0;
    
    public Player () {
        //
    }
    
    public void eat (int eat) {
        hunger = hunger + eat;
        if (hunger > 100) { hunger = 100; }
    }
    
    public void sleep (int sleep) {
        energie = energie + sleep;
        if (energie > 100) {
            energie = 100;
        }
    }
    
    public void move () {
        
        if (counter1 > 100) {
            
            counter1 = 0;
            energie--;
            hunger--;
            hygiene--;
            harndrang--;
            
        } else {
            counter1++;
        }
        
    }
    
    public void wake () {
        sleeping = false;
    }
    
    public Boolean isHungry () {
        
        if (hunger < 20) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public int getHunger () {
        return hunger;
    }
    
    public int getEnergie () {
        return energie;
    }
    
    public void addHunger (int hunger) {
        
        this.hunger = this.hunger + hunger;
        if (hunger > 100) { hunger = 100; }
        
        if (hunger <= 0) { 
            hunger = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
    public void addEnergie (int energie) {
        
        this.energie = this.energie + energie;
        if (energie > 100) { energie = 100; }
        
        if (energie <= 0) { 
            energie = 0;
            //Rettungshubschrauber holt Player aus dem Park
        }
        
    }
    
}
