package cz.cvut.fit.hybljan2.apitestingcg.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class help with generating java class files
 * @author Jan Hybl
 */
public class ClassGenerator {

    // class name (without package name)
    private String name;    
    // name of package, where generated class will be stored
    private String packageName;
    // List of imports used in class
    private Set<String> imports;
    // List of class constructors
    private List<MethodGenerator> constructors;
    // List of methods of class
    private List<MethodGenerator> methods;

    public ClassGenerator() {
        imports = new HashSet<String>();
        constructors = new LinkedList<MethodGenerator>();
        methods = new LinkedList<MethodGenerator>();
    }
    
    public void generateClassFile() {
        PrintWriter pw = null;
        
        File outputDir = new File("output" + File.separatorChar + getPathToPackage(packageName));
        outputDir.mkdirs(); 

        File file = new File(outputDir.getPath() + File.separatorChar + name + ".java");
        try {
            pw = new PrintWriter(file);

            // print package name.
            pw.println("package " + packageName + ';');
            pw.println();
            
            // print imports
            for (String imprt : imports) {
                pw.println("import " + imprt + ";\n");
            }
            
            // print class header 
            // TODO: add extends and import fields
            pw.println("public class " + name + " {\n");
            
            pw.println("\t// CONSTRUCTORS\n");
            
            for(MethodGenerator constructor : constructors) {
                pw.println(constructor.generateMethod());
                pw.println();
            }
            
            pw.println("\t// METHODS\n");
            
            for(MethodGenerator method : methods) {
                pw.println(method.generateMethod());
                pw.println();
            }
            
            pw.println("}");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }

    }


    // * * * Method used for generating * * *
    
    private String getPathToPackage(String pkgName) {
        return pkgName.replace('.', File.separatorChar);
    }    
    
    
    // * * * Getters and Setters * * *

    public void addImport(String imprt) {
        imports.add(imprt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    void addMethod(MethodGenerator method) {
        this.methods.add(method);
        this.imports.addAll(method.getImports());
    }
    
    void addConstructor(MethodGenerator costructor) {
        this.constructors.add(costructor);
        this.imports.addAll(costructor.getImports());
    }
}
