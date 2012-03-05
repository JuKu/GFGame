package net.generationfuture.game;

import java.io.File;
import java.io.FileFilter;

public class JARFileFilter implements FileFilter {
    
    @Override
    public boolean accept (File f) {
        return f.getName().toLowerCase().endsWith(".jar");
    }
    
}
