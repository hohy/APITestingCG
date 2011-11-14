package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generates instantiators for all nonabstract classes in API.
 * @author Jan Hybl
 */
public class InstantiatorGenerator extends Generator {

    @Override
    public void generate(API api) {
        for(APIPackage pkg : api.getPackages()) {
            for(APIClass cls : pkg.getClasses()) {
                if(!cls.getModifiers().contains(Modifier.ABSTRACT)) {
                    PrintWriter pw = null;
                    try {
                        
                        File outputDir = new File("output" + File.separatorChar + getPathToPackage(pkg));
                        outputDir.mkdirs(); 
                        
                        File file = new File(outputDir.getPath() + File.separatorChar + cls.getName() + ".java");
                        pw = new PrintWriter(file);
                        
                        // print package name. Test classes will be in same package as tested classes.
                        pw.println("package " + pkg.getName() + ';');
                        pw.println();
                        
                        // there could be imports list, but we don't need any imports.
                        
                        // class header
                        pw.println("public class " + cls.getName() + "Instantiator {\n");
                        
                        // list of constructors. Every constructor in tested class 
                        // is used by constructor in new class.
                        for(APIMethod constructor : cls.getConstructors()) {
                            // only public constructors can be tested in instantiator
                            if(constructor.getModifiers().contains(Modifier.PUBLIC)) {
                                
                                // String builder for list of params for instantiator constructor
                                StringBuilder paramDefListSb = new StringBuilder();
                                // String builder for list of params for tested constructor
                                StringBuilder paramListSb = new StringBuilder();
                                char paramName = 'a';
                                
                                for(String className : constructor.getParameters()) {
                                    paramDefListSb.append(className).append(" ").append(paramName).append(',');
                                    paramListSb.append(paramName).append(',');
                                    paramName++;
                                }
                                
                                // remove last ',' if there is any
                                if(paramDefListSb.length() > 0) {
                                    paramDefListSb.deleteCharAt(paramDefListSb.length()-1);
                                    paramListSb.deleteCharAt(paramListSb.length()-1);
                                }
                                // generate constructor
                                pw.println("\tpublic " + cls.getName() + "Instantiator ("+ paramDefListSb.toString() + ") {");
                                pw.println("\t\t" + cls.getName() + " instance = new " + cls.getName() +'(' + paramListSb.toString() + ')');
                                pw.println("\t}\n");
                                                        
                            }
                        }
                        
                        pw.println("}");
                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(InstantiatorGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        pw.close();
                    }
                }
            }
        }
    }

    private String getPathToPackage(APIPackage pkg) {
        return pkg.getName().replace('.', File.separatorChar);
    }

    private String getParamsList(APIMethod constructor) {
        StringBuilder sb = new StringBuilder();
        char paramName = 'a';
        for(String className : constructor.getParameters()) {
            sb.append(className);
            sb.append(" ").append(paramName).append(',');
            paramName++;
        }
        if(sb.length() > 0) return sb.substring(0, sb.length() - 1);
        else return "";
    }
    
}
