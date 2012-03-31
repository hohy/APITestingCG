package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.api.JavacTool;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Options;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;

import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: write some javadoc
 */
public class SourceScanner implements APIScanner {
    private String sourceDir;
    private String classPath;
    private String sourceVersion;
    private String apiName;
    private String apiVersion;

    public SourceScanner(String sourceDir, String classPath, String sourceVersion) {
        this.sourceDir = sourceDir;
        this.classPath = classPath;
        this.sourceVersion = sourceVersion;
    }

    public SourceScanner() {
    }

    @Override
    public void setConfiguration(ScannerConfiguration sc) {
        if (sc.getPath() != null) this.sourceDir = sc.getPath();
        if (sc.getClasspath() != null) this.classPath = sc.getClasspath();
        if (sc.getSourceVersion() != null) this.sourceVersion = sc.getSourceVersion();
        if (sc.getApiName() != null) this.apiName = sc.getApiName();
        if (sc.getApiVersion() != null) this.apiVersion = sc.getApiVersion();
    }

    /**
     * Scan
     */
    @Override
    public API scan() {
        try {
            Context ctx = new Context();
            Options opt = Options.instance(ctx);
            opt.put("-source", sourceVersion);
            JavaCompiler compiler = JavaCompiler.instance(ctx);
            compiler.attrParseOnly = true;
            compiler.keepComments = true;

            List<String> files = listFiles(sourceDir);

            JavacFileManager fileManager = (JavacFileManager) ctx.get(JavaFileManager.class);
            List<JavaFileObject> fileObjects = new ArrayList<JavaFileObject>();
            for (String s : files) {
                fileObjects.add(fileManager.getFileForInput(s));
            }

            JavacTool tool = JavacTool.create();
            String cp = getClassPathJarFilesList(classPath.trim());
            loadJarFiles(cp);
            List<String> options = Arrays.asList("-cp", cp);
            JavacTaskImpl javacTaskImpl = (JavacTaskImpl) tool.getTask(null, fileManager, null, options, null, fileObjects);
            javacTaskImpl.updateContext(ctx);
            Iterable<? extends CompilationUnitTree> units = javacTaskImpl.parse();
            javacTaskImpl.analyze();
            JavacTypes types = javacTaskImpl.getTypes();
            SourceTreeScanner sc = new SourceTreeScanner(types);
            for (CompilationUnitTree cut : units) {
                sc.visitTopLevel((JCCompilationUnit) cut);
            }

            API api = sc.getAPI();
            api.setName(apiName);
            api.setVersion(apiVersion);
            return api;
        } catch (IOException ex) {
            Logger.getLogger(SourceScanner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void loadJarFiles(String cp) {
        System.out.println("Loading classpath jar files: " + cp);
        for (String jarPath : cp.split(File.pathSeparator)) {
            try {
                File jarFile = new File(jarPath);
                URL u = jarFile.toURI().toURL();
                URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                Class urlClass = URLClassLoader.class;
                Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
                method.setAccessible(true);
                method.invoke(urlClassLoader, new Object[]{u});
            } catch (Exception e) {
                Logger.getLogger(ByteCodeScanner.class.getName()).log(Level.SEVERE, "Can't add \"" + jarPath + "\" jar file to classpath.");
            }
        }
    }

    public static String getClassPathJarFilesList(String classPath) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        String[] pathItems = classPath.split(File.pathSeparator);
        for (String pathItem : pathItems) {
            File f = new File(pathItem);
            if (f.isDirectory()) {
                List<String> jarFiles = listFiles(pathItem, ".jar");
                for (String jarFilePath : jarFiles) {
                    sb.append(jarFilePath).append(File.pathSeparator);
                }
            } else {
                sb.append(pathItem).append(File.pathSeparator);
            }
        }
        // delete last pathSeparator
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Gets list of java files in directory with given path.
     *
     * @param path
     * @return
     */
    public static List<String> listFiles(String path) throws FileNotFoundException {
        return listFiles(path, ".java");
    }

    /**
     * Gets list of files with given path and suffix.
     *
     * @param path   Path to directory with files
     * @param suffix Suffix name of files. For example: ".java"
     * @return List of files
     * @throws FileNotFoundException
     */
    public static List<String> listFiles(String path, final String suffix) throws FileNotFoundException {
        List<String> p = new ArrayList<String>();
        File dir = new File(path);
        if (!dir.exists()) throw new FileNotFoundException("Can't file/dir " + dir.getAbsolutePath());
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                if (f.getName().endsWith(suffix)) {
                    return true;
                }
                return false;
            }
        };
        for (File f : dir.listFiles(filter)) {
            if (f.isFile()) {
                p.add(f.getPath());
            } else if (f.isDirectory()) {
                List<String> pp = listFiles(f.getPath());
                p.addAll(pp);
            }
        }
        return p;
    }

}
