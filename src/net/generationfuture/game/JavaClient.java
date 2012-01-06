package net.generationfuture.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JavaClient extends WebClient {
    
    //Client mit Java-Server
    
    public JavaClient (Config config) throws IOException {
        super(config);
    }
    
    @Override
    public void runProtokoll () throws MalformedURLException, IOException {
        URL url = new URL(config.getServerURL());
        is = url.openStream();
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        String text = readFile(is);
        vomServer_text = text;
        
        if (config.isDebugMode()) { System.out.println("vomServer: \"" + text + "\"."); }
    }

}
