package cz.cvut.fit.hybljan2.apitestingcg.ngenerator;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.Pretty;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 8.2.12
 * Time: 10:21
 */
public abstract class ClassGenerator extends Generator {

    // CompilationUnit = one *.java file with source code. = package declaration + imports + class declaration.
    protected JCTree.JCCompilationUnit currentCompilationUnit;
    // Buffer for content of compilation unit = imports + class declarations
    protected ListBuffer<JCTree> cuBuffer;
    // Map imports to class names
    private Map<String, String> importsMap;
    

    public ClassGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    /**
     * Creates new compilation unit and set it as {@code currentCompilationUnit}
     */
    protected void initCompilationUnit() {
        cuBuffer = new ListBuffer<JCTree>();
        importsMap = new HashMap<String, String>();
    }

    /**
     * Converts full class name ({@code java.io.File}) to simple name ({@code File}).
     * Full class name is added to imports of {@code currentCompilationUnit}. 
     * @param fullName
     * @return
     */
    protected String simplifyName(String fullName) {
        if(!fullName.contains(".")) {   // full name is already simple
            return fullName;
        } else {
            String shortName = fullName.substring(fullName.lastIndexOf(".")+1);
            String mapFullName = importsMap.get(shortName);
            if(mapFullName == null) { // imports doesn't contain this class
                importsMap.put(shortName, fullName);
                return shortName;
            } else if (mapFullName.equals(fullName)) { // imports already contains same import
                return shortName;
            } else { // import already contains import of class with same name, but from different package
                return fullName;
            }
        }
    }
    
    /**
     * Generates java source file from AST using Pretty printer.
     * @param fileName  Path to generated file
     */
    protected void generateSourceFile(String fileName, JCTree.JCClassDecl cls) {

        List<JCTree.JCAnnotation> packageAnnotations = List.nil();
        JCTree.JCExpression packageNameExpression = maker.Ident(names.fromString(currentPackageName));
        ListBuffer<JCTree> finalBuffer = new ListBuffer<JCTree>();

        // generate import list
        for(String imprt : importsMap.keySet()) {
            JCTree.JCImport imprtTree = maker.Import(maker.Ident(names.fromString("java.io.File")), false);
            finalBuffer.append(imprtTree);//cuBuffer.append(imprtTree);
        }
        finalBuffer.addAll(cuBuffer.elems);
        currentCompilationUnit = maker.TopLevel(packageAnnotations, packageNameExpression, finalBuffer.toList());

        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            Pretty pretty = new Pretty(fw, true);
            pretty.printUnit(currentCompilationUnit, cls);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw != null) try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
