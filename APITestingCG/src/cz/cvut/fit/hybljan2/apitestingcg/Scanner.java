package cz.cvut.fit.hybljan2.apitestingcg;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.api.JavacTool;
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
public class Scanner {
    private String sourceDir;
    private String classPath;
    private String sourceVersion;

    public Scanner(String sourceDir, String classPath, String sourceVersion) {
        this.sourceDir = sourceDir;
        this.classPath = classPath;
        this.sourceVersion = sourceVersion;
    }
        
    /**
     * Scan
     */
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
            
            SourceScanner sc = new SourceScanner();
            for (CompilationUnitTree cut : units) {                
                sc.visitTopLevel((JCCompilationUnit) cut);                
            }
            
            API api = sc.getAPI();
            return api;
        } catch (IOException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
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
