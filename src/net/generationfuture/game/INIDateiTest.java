package net.generationfuture.game;

public class INIDateiTest {

	public static void main(String[] args) {
	    INIDatei einaus = new INIDatei("Test.ini");
	    
	    System.out.println("=== Ausgabe nach Lesen ===");
	    String ausgabe = einaus.leseFirst();
	    while(!ausgabe.equals("") ){
	    	System.out.println(ausgabe);
	    	ausgabe =einaus.leseNext();  
	    }
	    
	    einaus.setzeString("Eins", "anfang","Erster Eintrag");
	    einaus.setzeString("Zwei", "zwei","Eintrag bei Zwei");
	    einaus.setzeString("Eins", "zweiterEintrag","weiter");
	    einaus.setzeString("Anfang", "Anfangseintrag","der Anfang");
	    einaus.setzeString("Eins", "dritterEintrag","drei");
	    
	    System.out.println("=== Lesen eines Eintrags ===");
	    ausgabe = einaus.leseString("Eins", "anfang");
    	System.out.println(ausgabe);
	    System.out.println("=== Lesen des Eintrags nach Veränderung===");
    	einaus.setzeString("Eins", "anfang","verändert");
	    ausgabe = einaus.leseString("Eins", "anfang");
    	System.out.println(ausgabe);
	    
	    System.out.println("=== Ausgabe nach Änderung ===");
	    ausgabe = einaus.leseFirst();
	    while(!ausgabe.equals("") ){
	    	System.out.println(ausgabe);
	    	ausgabe =einaus.leseNext();  
	    }
	    
	    einaus.schreibeINIDatei("Test.ini",true);
	    
	}
}