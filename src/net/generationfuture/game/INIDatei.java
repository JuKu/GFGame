package net.generationfuture.game;

/**
 * Die Klasse INIListe dient den Lesen und Schreiben von INI-Dateien.
 * 
 * Der Konstruktor der Klasse erstellt eine Liste von INI-Datens�tzen:
 * mit den Attributen Sektion ([...], Attribut , wert
 * 
 *  Eintr�ge der Inidatei sind 
 *  [ABC]
 *     wert=5
 *     breite=4
 *  [EINS]
 *     f=5
 *  [ENDE]
 *     wichtig=578
 *     zahl=2010.02
 *     tag=17
 *     
 *  lesenINIDatei(String dateiname) importiert die Datei 
 *  
 *  schreibeINIDatei(String dateiname, boolean ersetzen) schreibt die datei
 *  ersetzen=true ersetzt eine besetehende Datei
 *  
 *  schreibeINIDatei() ersetzt die letzte (gelesene oder geschriebene) Datei
 *  
 * 
 * setzeString(String Sektion, String Attribut, String wert) 
 * erzeugt/�ndert einen INIDatei-Eintrag 
 * 
 * genauso setzeInteger und setzeDouble
 * 
 * existiert(String Sektion, String Attribut) �berpr�ft die Existenz eines Eintrags
 * 
 * 
 * leseString(String Sektion, String Attribut) lie�t einen Eintrag als String
 * 
 * leseInteger(String Sektion, String Attribut, double error)
 * und leseDouble(String Sektion, String Attribut, double error)
 * lesen eine INTEGER bzw DOUBLE-Zahl.
 * errror kommt zur�ck, falls dabei ein Fehler auftritt
 * 
 * autor: Witt
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class INIDatei extends LinkedList<INIDatensatz> {

	String dateiname = null;

	final static int INI_ENDE = -1;

	/**
	 * Konstruktor
	 * 
	 * erzeugt leere LinkedList
	 */
	public INIDatei() {
	}

	/**
	 * Konstruktor
	 * 
	 * erzeugt LinkedList und liest Datei "dateiname" in die LinkedList
	 */
	public INIDatei(String dateiname) {
		lesenINIDatei(dateiname);
	}

	/**
	 *  zum internen Gebrauch. muss leider public sein
	 */
	public boolean add(INIDatensatz ini) {
		setzeString(ini.Sektion, ini.Attribut, ini.wert);
		return true;
	}

	/**
	 * Eingabe/Ver�nderung eines Werts als String
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeString(String Sektion, String Attribut, String wert) {
		if (!existiert(Sektion, Attribut)) {
			INIDatensatz tmp = new INIDatensatz();
			// Eintr�ge nur mit Attribut
			if (Attribut.equals(""))
				return;
			tmp.Sektion = Sektion;
			tmp.Attribut = Attribut;
			tmp.wert = wert;
			super.add(tmp);
			// Liste sortieren
			Collections.sort(this);
		} else { // �ndern
			for (INIDatensatz ds : this) {
				if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
					ds.wert = wert;
					return;
				}
			}
		}
	}

	/**
	 * Eingabe/Ver�nderung eines Werts als Integer
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeInteger(String Sektion, String Attribut, int wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Eingabe/Ver�nderung eines Werts als double
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeDouble(String Sektion, String Attribut, double wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Testet, ob Attribut in der Sektion existiert
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @return existiert
	 */
	public boolean existiert(String Sektion, String Attribut) {
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
				return true;
			}
			if (ds.Sektion.compareTo(Sektion) > 0)
				return false;
		}
		return false;
	}

	/**
	 * gibt der Wert des Attributs als String zur�ck
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @return 
	 */
	public String leseString(String Sektion, String Attribut) {
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
				return ds.wert;
			}
			if (ds.Sektion.compareTo(Sektion) > 0)
				return "";
		}
		return "";
	}

	/**
	 * gibt den Wert des Attributs als ganze Zahl zur�ck
	 * 
	 * <br/>Falls das nicht geht, wird der error-Wert zur�ckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return
	 */
	public int leseInteger(String Sektion, String Attribut, int error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		int ret = error;
		try {
			ret = Integer.parseInt(erg);
		} catch (Exception e) {
		}

		return ret;
	}

	/**
	 * gibt den Wert des Attributs als double-Zahl zur�ck
	 * 
	 * <br/>Falls das nicht geht, wird der error-Wert zur�ckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return
	 */
	public double leseDouble(String Sektion, String Attribut, double error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		erg = erg.replaceAll(",", ".");
		double ret = error;
		try {
			ret = Double.parseDouble(erg);
		} catch (Exception e) {
		}

		return ret;
	}

	int indexAll = 0;
	/**
	 * <strong>Auslesen der Liste, erster Eintrag</strong>
	 * 
	 * R�ckgabe in der Form 
	 *  
	 * <strong>[Sektion]attribut=wert</strong>
	 * 
	 * <br/>Leere Liste -> R�ckgabe: leerer String
	 */
	public String leseFirst() {
		Collections.sort(this);
		indexAll = 0;
		if (this.isEmpty()) {
			return "";
		} else {
			return "[" + this.getFirst().Sektion + "]"
					+ this.getFirst().Attribut + "=" + this.getFirst().wert;
		}
	}

	/**
	 * <strong>Auslesen der Liste, Folge-Eintrag</strong>
	 * 
	 * R�ckgabe in der Form 
	 *  
	 * <strong>[Sektion]attribut=wert</strong>
	 * 
	 * <br/>Ende der Liste -> R�ckgabe: leerer String
	 */
	public String leseNext() {
		if (indexAll == INI_ENDE)
			return "";
		indexAll++;
		int i = 0;
		for (INIDatensatz ds : this) {
			if (i == indexAll) {
				return "[" + ds.Sektion + "]" + ds.Attribut + "=" + ds.wert;
			} else {
				i++;
			}
		}
		indexAll = INI_ENDE; // Ende
		return "";
	}

	int indexSektion = 0;
	/**
	 * <strong>Auslesen der Eintraege einer Sektion, erster Eintrag</strong>
	 * 
	 * R�ckgabe in der Form 
	 *  
	 * <strong>attribut=wert</strong>
	 * 
	 * <br/>Kein Eintrag -> R�ckgabe: leerer String
	 */
	public String leseFirstAusSektion(String Sektion) {
		indexSektion = 0;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				return ds.Attribut + "=" + ds.wert;
			}
		}
		return "";
	}

	/**
	 * <strong>Auslesen der Eintraege einer Sektion, Folge-Eintrag</strong>
	 * 
	 * R�ckgabe in der Form 
	 *  
	 * <strong>attribut=wert</strong>
	 * 
	 * <br/>Ende -> R�ckgabe: leerer String
	 */
	public String leseNextAusSektion(String Sektion) {
		if (indexSektion == INI_ENDE)
			return "";
		indexSektion++;
		int i = 0;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				if (i == indexSektion) {
					return ds.Attribut + "=" + ds.wert;
				} else {
					i++;
				}
			}
		}
		indexSektion = -1;
		return "";
	}
	/**
	 * L�schenaller Eintr�ge
	 */
	public void clear() {
		super.clear();
	}

	/**
	 * <strong>Schreiben der INI-Datei</strong>
	 * Dateiname bekannt, z.B. durch vorheriges lesen
	 */
	public void schreibeINIDatei() {
		if (dateiname != "")
			schreibeINIDatei(dateiname, true);
	}

	/**
	 * <strong>Schreiben der INI-Datei
	 * Dateiname wird f�r sp�tere Verwendung gespeichert. </strong>
	 * 
	 * Eine bestehende Datei wird bei ersetzen = true �berschrieben
	 * Bei ersetzen = false wird mit fehlermeldung abgebrochen 
	 */
	public boolean schreibeINIDatei(String dateiname, boolean ersetzen) {
		this.dateiname = dateiname;
		File datei = new File(dateiname);
		if (datei.exists()) {
			if (ersetzen) {
				datei.delete();
				try {
					datei.createNewFile();
				} catch (IOException e) {
					JOptionPane.showConfirmDialog(null,
							"Probleme beim Speichern !", "Speichern",
							JOptionPane.OK_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					return false ;
				}
			} else {
				return false;
			}
		}

		// Liste sortieren
		Collections.sort(this);

		try {
			FileWriter fw = new FileWriter(dateiname);
			BufferedWriter bfw = new BufferedWriter(fw);
			String Sektion = "";
			for (INIDatensatz ds : this) {
				if (!Sektion.equals(ds.Sektion)) {
					bfw.write("[" + ds.Sektion + "]");
					bfw.newLine();
					Sektion = ds.Sektion;
				}
				bfw.write("    " + ds.Attribut + "=" + ds.wert);
				bfw.newLine();
			}
			bfw.close();
			fw.close();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, e.toString(),
					"Probleme beim Speichern", JOptionPane.OK_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			return false ;
		}
		return true;
	}

	/**
	 * <strong>Lesen der Datei in die INI-Liste</strong>
	 * 
	 * @param dateiname
	 */
	public void lesenINIDatei(String dateiname) {
		this.dateiname = dateiname;
		File datei = new File(dateiname);
		if (!datei.exists()) {
			return;
		} else {
			FileReader reader = null;
			try {
				String pfad = datei.getPath();
				reader = new FileReader(pfad);
				BufferedReader br = new BufferedReader(reader);

				String zeile;

				while ((zeile = br.readLine()) != null) {
					INIDatensatz erg = INIDatensatz.zeilenAnalyse(zeile);
					if (erg != null) {
						this.add(erg);
					}
				}

			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, e.toString(),
						"Probleme beim Lesen", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

			} finally {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
			// Liste sortieren
			Collections.sort(this);
		}
	}
}
class INIDatensatz implements Comparable<INIDatensatz> {

	public static String letzeSektion = "";

	String Sektion;
	String Attribut;
	String wert;

	/**
	 * @param daten
	 */
	protected INIDatensatz() {
	}

	public static INIDatensatz zeilenAnalyse(String daten) {
		daten = daten.trim();
		if (!daten.equals("") && daten.startsWith("[") && daten.endsWith("]")) {
			// Neue Kathegorie
			letzeSektion = (daten.substring(1, daten.length() - 1)).trim();
			return null;
		} else {
			int i = daten.indexOf("=");
			INIDatensatz erg = new INIDatensatz();
			erg.Sektion = letzeSektion;
			if (i > 0) {
				erg.Attribut = (daten.substring(0, i)).trim();
				erg.wert = (daten.substring(i + 1)).trim();
				return erg;
			} else {
				return null;
			}
		}
	}

	public String toString() {
		return Sektion;
	}

	// F�r das Sortieren
	// Vergleich nur f�r Sektion
	public int compareTo(INIDatensatz arg) {
		return -1 * arg.Sektion.compareTo(Sektion);
	}
}
