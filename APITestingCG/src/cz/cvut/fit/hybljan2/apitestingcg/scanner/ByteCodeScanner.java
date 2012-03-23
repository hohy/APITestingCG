package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jan Hýbl
 */
public class ByteCodeScanner implements APIScanner {

    private String jarFilePath;
    private String apiName;
    private String apiVersion;
    private String classPath;

    public ByteCodeScanner(String jarFilePath) {
        this.jarFilePath = jarFilePath;
    }

    public ByteCodeScanner() {
    }

    @Override
    public void setConfiguration(ScannerConfiguration sc) {
        this.jarFilePath = sc.getPath();
        this.apiName = sc.getApiName();
        this.apiVersion = sc.getApiVersion();
        this.classPath = sc.getClasspath();
    }

    @Override
    public API scan() {
        JarInputStream jis = null;
        URLClassLoader urlcl;
        List<URL> jarUrlsList = new LinkedList<URL>();
        Map<String, APIPackage> pkgMap = new TreeMap<String, APIPackage>();
        try {
            // Load classes set defined in class path (If there is anything to load).
            if (classPath != null) {
                classPath = SourceScanner.getClassPathJarFilesList(classPath);
                if (!classPath.isEmpty()) {
                    String[] paths = classPath.split(File.pathSeparator);
                    for (String path : paths) {
                        try {
                            File jarFile = new File(path);
                            URL u = jarFile.toURI().toURL();
                            URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                            Class urlClass = URLClassLoader.class;
                            Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
                            method.setAccessible(true);
                            method.invoke(urlClassLoader, new Object[]{u});
                        } catch (Exception e) {
                            Logger.getLogger(ByteCodeScanner.class.getName()).log(Level.SEVERE, "Can't add \"" + path + "\" jar file to classpath.");
                        }
                    }
                }
            }

            // Get list of jar files to be loaded. One jar single or all jar files in directory.
            List<File> jarFiles = new LinkedList<File>();
            File jarPath = new File(jarFilePath);
            if (jarPath.isDirectory()) {
                for (String path : SourceScanner.listFiles(jarFilePath, ".jar")) {
                    jarFiles.add(new File(path));
                }
            } else {
                jarFiles.add(new File(jarFilePath));
                jarUrlsList.add(new File((jarFilePath)).toURI().toURL());
            }

            // Load every jar file in the list.
            URL[] urlArray = new URL[jarUrlsList.size()];
            urlArray = jarUrlsList.toArray(urlArray);
            for (URL jarFileUrl : jarUrlsList) {
                jis = new JarInputStream(new FileInputStream(jarFileUrl.getFile()));
                urlcl = new URLClassLoader(urlArray, this.getClass().getClassLoader());

                JarEntry jarEntry;
                while ((jarEntry = jis.getNextJarEntry()) != null) {

                    // find java class files in jar
                    if (jarEntry.getName().endsWith(".class")) {

                        // get name of class
                        String className = jarEntry.getName().replaceAll("/", "\\.");
                        className = className.substring(0, className.length() - 6);

                        // load the class
                        Class classToLoad = Class.forName(className, true, urlcl);

                        //if ((Modifier.isPublic(classToLoad.getModifiers())              // class has to be Public
                        //        || Modifier.isProtected(classToLoad.getModifiers()))    // or protected
                        if (!classToLoad.isMemberClass()) {                      // and must not be nested.

                            APIClass apiClass = new APIClass(classToLoad);

                            // put class to right package, if package doesn't exist, create it.
                            if (classToLoad.getPackage() != null) {
                                String packageName = classToLoad.getPackage().getName();
                                if (pkgMap.containsKey(packageName)) {
                                    pkgMap.get(packageName).addClass(apiClass);
                                } else {
                                    APIPackage pkg = new APIPackage(packageName);
                                    pkg.addClass(apiClass);
                                    pkgMap.put(pkg.getName(), pkg);
                                }
                            }

                        }
                    }
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
        API api = new API(apiName, apiVersion);
        for (APIPackage pkg : pkgMap.values()) {
            api.addPackage(pkg);
        }
        return api;
    }

}