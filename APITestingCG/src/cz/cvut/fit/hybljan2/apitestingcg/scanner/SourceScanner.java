package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import com.sun.tools.javac.model.JavacTypes;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.ScannerConfiguration;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.api.JavacTool;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Options;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

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
        if(sc.getPath() != null) this.sourceDir = sc.getPath();
        if(sc.getClasspath() != null) this.classPath = sc.getClasspath();
        if(sc.getSourceVersion() != null) this.sourceVersion = sc.getSourceVersion();
        if(sc.getApiName() != null) this.apiName = sc.getApiName();
        if(sc.getApiVersion() != null) this.apiVersion = sc.getApiVersion();
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
            List<String> options = Arrays.asList("-cp", classPath);
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
    
    /**
     * Gets list of java files in directory with given path.
     * @param path
     * @return 
     */
    private List<String> listFiles(String path) {
        List<String> p = new ArrayList<String>();
        File dir = new File(path);
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                if (f.getName().endsWith(".java")) {
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
