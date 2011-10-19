package cz.cvut.fit.hybljan2.apitestingcg;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Hýbl
 */
public class ByteCodeScanner implements APIScanner {

    private String jarFilePath;

    public ByteCodeScanner(String jarFilePath) {
        this.jarFilePath = jarFilePath;
    }
        
    @Override
    public API scan() {
        JarInputStream jis = null;
        try {
            File jarFile = new File(jarFilePath);
            jis = new JarInputStream(new FileInputStream(jarFile));
            
            URL[] jarUrls = new URL[1];
            jarUrls[0] = jarFile.toURI().toURL();
            URLClassLoader urlcl =  new URLClassLoader(jarUrls, this.getClass().getClassLoader());
            
            JarEntry jarEntry;
            while ((jarEntry = jis.getNextJarEntry()) != null) {
                if(jarEntry.getName().endsWith(".class")) {                    
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    className = className.substring(0,className.length() - 6);
                    System.out.println("Class: " + className);
                    Class classToLoad = Class.forName (className, true, urlcl);
                    Method m[] = classToLoad.getDeclaredMethods();
                    for (int i = 0; i < m.length; i++) System.out.println("  " + m[i].toString());
                } else {
                    System.out.println("Package: " + jarEntry.getName().substring(0, jarEntry.getName().length()-1).replaceAll("/", "\\."));
                }                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ByteCodeScanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioex) {
            Logger.getLogger(ByteCodeScanner.class.getName()).log(Level.SEVERE, null, ioex);
        } finally {
            try {
                jis.close();
            } catch (IOException ex) {
                Logger.getLogger(ByteCodeScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
