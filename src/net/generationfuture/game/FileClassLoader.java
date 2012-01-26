package net.generationfuture.game;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//Quelle: http://www.tutorials.de/java/153725-classpath-dynamisch-setzen.html
 
public class FileClassLoader extends ClassLoader {
 
    private String rootPath;
 
    public FileClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }
    //Klasse mit Namen name ab rootPath suchen
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        if (b == null)
            throw new ClassNotFoundException(name);
        return defineClass(name, b, 0, b.length);
    }
 
    /**
     * Liefert die gefundene Klasse als Byte Array zurück ... 
     * @param name
     * @return byte[] theClassBytes
     */
    private byte[] loadClassData(String name) {
        try {
            name = rootPath + name.replace('.', File.separatorChar) + ".class";
            File f = new File(name);
            FileInputStream fis = new FileInputStream(f);
            byte cByte[] = new byte[(int) f.length()];
            fis.read(cByte);
            fis.close();
            return cByte;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
 
    /*public static void main(String[] args) {
        FileClassLoader fcl =
            new FileClassLoader("C:/asw/eclipse/workspace/Test/");
        try {
            Class clazz = fcl.loadClass("Test");
            if (clazz != null) {
                Method method =
                    clazz.getMethod("main", new Class[] { String[].class });
                if (method != null)
                    method.invoke(null, new Object[] { null });
            }
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/
 
}

