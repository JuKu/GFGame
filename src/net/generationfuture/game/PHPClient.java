package net.generationfuture.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PHPClient extends WebClient {
    
    //Client für PHP-Server
    
    public PHPClient (Config config) throws IOException {
        super(config);
    }
    
    @Override
    public void runProtokoll () throws MalformedURLException, IOException {
        URL url = new URL(config.getServerURL());
        is = url.openStream();
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        String text = readFile(is);
        vomServer_text = text;
        
        if (this.getPlayerData) {
            url = new URL(config.getServerURL() + "?username=" + config.getUsername() + "&passwort=" + config.getPasswort() + "");
            is = url.openStream();
            br = new BufferedReader( new InputStreamReader(is) );
            text = readFile(is);
            vomServer_text = text;
            
            this.player_pos_x = Integer.parseInt(this.parseElement(text, "player_pos_x"));
            this.player_pos_y = Integer.parseInt(this.parseElement(text, "player_pos_y"));
            
            this.getPlayerData = false;
        } else if (this.setPlayerData) {
            url = new URL(config.getServerURL() + "/index.php?username=test" + config.getUsername() + "&passwort=" + config.getPasswort() + "&option=setPlayerPos&player_pos_x=" + player_pos_x + "&player_pos_y=" + player_pos_y);
            is = url.openStream();
            br = new BufferedReader( new InputStreamReader(is) );
            text = readFile(is);
            vomServer_text = text;
            
            this.setPlayerData = false;
        } else if (this.getAnimals) {
            url = new URL(config.getServerURL() + "/index.php?username=" + config.getUsername() + "&passwort=" + config.getPasswort() + "&option=getAnimals");
            is = url.openStream();
            br = new BufferedReader( new InputStreamReader(is) );
            text = readFile(is);
            vomServer_text = text;
            
            File file = new File(config.getCacheFolder() + "/Animals/Animals.ini");
            file.createNewFile();
            
            //file.setReadOnly();
            
            FileOutputStream schreibeStrom = new FileOutputStream(config.getCacheFolder() + "/Animals/Animals.ini");
            for (int i=0; i < text.length(); i++){
                schreibeStrom.write((byte)text.charAt(i));
                schreibeStrom.write(455);
            }
            schreibeStrom.close();
            
            //FileOutputStream out = new FileOutputStream(file);
            //DataOutputStream dout = new DataOutputStream(out);
            
            //dout.writeInt(10);
            //dout.writeInt(100);
            
            //dout.close();

            
            /*FileInputStream in = new FileInputStream(file);
            DataInputStream din = new DataInputStream(in);
            
                System.out.println("Int: " + din.readInt());*/
            
            this.getAnimals = false;
        }
        
        if (config.isDebugMode()) { System.out.println("vomServer: \"" + text + "\"."); }
    }
    
}
