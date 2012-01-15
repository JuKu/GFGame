package net.generationfuture.game;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebClient extends Thread {
    
    protected Config config;
    protected Socket socket;
    protected BufferedReader vomServer;
    protected PrintWriter zumServer;
    
    private boolean ready = false;
    
    protected int timeout = 5000;
    protected String vomServer_text = "";
    
    protected Boolean Client_beenden = false;
    protected InputStream is;
    
    protected Boolean getPlayerData = true;
    protected Boolean setPlayerData = false;
    
    protected Boolean getAnimals = true;
    protected Boolean getPlayer = true;
    
    protected Boolean getAnimalsData = true;
    
    protected int player_pos_x = 0;
    protected int player_pos_y = 0;
    
    protected String server_url = "";
    
    public WebClient (Config config) throws IOException {
        this.config = config;
        
        //socket = new Socket(config.getServerURL(), config.getPort_());
        //socket.setSoTimeout(timeout);
        //vomServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //zumServer = new PrintWriter(socket.getOutputStream(), true);
        
        server_url = config.getServerURL();
        
        URL url = new URL(server_url);
        //try {
            is = url.openStream();
        //} catch (Exception e) {
            //Error.throwError("Server not found");
        //}
        
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        String text = readFile(is);
        vomServer_text = text;
        vomServer = br;
        if (config.isDebugMode()) { System.out.println("vomServer: \"" + text + "\"."); }
        ready = true;
    }
    
    @Override
    public void run () {
        
        while (!Client_beenden) {
            
            try {
                
                runProtokoll();
                
            } catch (IOException ex) {
                Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public int[] getPlayerPos() {
        
        while (((player_pos_x == 0 && player_pos_y == 0) || setPlayerData )&& !ready) {//Wenn gerade Pos-Daten gesendet werden, keine Player-Daten empfangen.
            //warten
        }

        int player_pos_data[] = new int[2];
        
        /*player_pos_data[0] = player_pos_x;
        player_pos_data[1] = player_pos_y;*/
        
        player_pos_data[0] = 10;
        player_pos_data[1] = 10;
        
        return player_pos_data;
        
    }
    
    public void setPlayerPos (int x, int y) {
        player_pos_x = x;
        player_pos_y = y;
        getPlayerData = false;
        setPlayerData = true;
        
        config.setPlayerPosData(x, y);
        config.savePlayer();
    }
    
    public void getPlayerData () {
        getPlayerData = true;
    }
    
    public void runProtokoll () throws MalformedURLException, IOException {
        URL url = new URL(config.getServerURL());
        is = url.openStream();
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        String text = readFile(is);
        vomServer_text = text;
    }

        public final String readFile(InputStream is)
      throws IOException
   {
      BufferedReader br = new BufferedReader( new InputStreamReader(is) );
      StringBuffer text = new StringBuffer();
      String line;
      while ( (line=br.readLine()) !=null )
         text.append(line).append("\n");

      return text.toString();
   }
        
   public String parseElement (String text, String element) {
        
        int i_ = text.indexOf(element);
        char zeichen[] = text.toCharArray();
        
        char zeichen_element[] = element.toCharArray();
        
        int anzahl_zeichen = zeichen.length;
        int anzahl_zeichen_element = zeichen_element.length;
        
        String string_ = null;
        int i2 = i_ + anzahl_zeichen_element + 1;
        
        /*while (zeichen[i2] + "" != "<") {
            string_ = string_ + zeichen[i2];
            i2++;
        }*/
        
        //string_ = zeichen[i2] + "";
        string_ = "";
        
        while (!"<".equals(zeichen[i2] + "")) {
            string_ = string_ + zeichen[i2] + "";
            i2++;
        }
            
        return string_;
        
    }
    
}
