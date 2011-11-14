package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedSet;
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
                        
                        File file = new File(outputDir.getPath() + File.separatorChar + cls.getName() + "Instantiator.java");
                        pw = new PrintWriter(file);
                        
                        // print package name. Test classes will be in same package as tested classes.
                        pw.println("package " + pkg.getName() + ';');
                        pw.println();
                        
                        // list of imports - only tested class
                        pw.println("import " + cls.getFullName() + ";\n");
                        
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
                                pw.println("\tpublic " + cls.getName() + "Instantiator ("+ getMethodParamList(constructor) + ") {");
                                pw.println("\t\t" + constructor.getName() + " instance = new " + constructor.getName() +'(' + getMethodParamNameList(constructor) + ");");
                                
                                // generate null constructor
                                String nullConstructor = nullParamsConstructor(constructor, cls.getConstructors());
                                if(nullConstructor != null) { // null constructor should not be null, lol. ;-D
                                    pw.println("\t\t" + nullConstructor);
                                }
                                
                                pw.println("\t}\n");
                            }
                        }
                        
                        // list of callers (methods that test method calling)
                        for(APIMethod method : cls.getMethods()) {
                            // instantiator can test only public methods
                            if(method.getModifiers().contains(Modifier.PUBLIC)) {
                                
                                StringBuilder methodParams = new StringBuilder(getMethodParamList(method));
                                
                                // if method isn't static, add instance param
                                if(method.getModifiers().contains(Modifier.STATIC)) {
                                    if(!method.getParameters().isEmpty()) methodParams.append(',');
                                    methodParams.append(cls.getFullName()).append(" instance");                                                                
                                }                                
                                
                                // method header
                                pw.println("\tpublic void " + method.getName() + "Call(" + methodParams + ") {");
                                
                                pw.println("\t}");
                            }
                        }
                        
                        // end of Instantiator class
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

    private String getMethodParamList(APIMethod method) {
        // String builder for list of params for instantiator constructor
        StringBuilder paramDefListSb = new StringBuilder();
        char paramName = 'a';

        for(String className : method.getParameters()) {
            paramDefListSb.append(className).append(" ").append(paramName).append(',');
            paramName++;
        }

        // remove last ',' if there is any
        if(paramDefListSb.length() > 0) {
            paramDefListSb.deleteCharAt(paramDefListSb.length()-1);
        }        
        return paramDefListSb.toString();
    }
    
    private String getMethodParamNameList(APIMethod method) {
        // String builder for list of params for tested constructor
        StringBuilder paramListSb = new StringBuilder();
        char paramName = 'a';

        for(String className : method.getParameters()) {
            paramListSb.append(paramName).append(',');
            paramName++;
        }

        // remove last ',' if there is any
        if(paramListSb.length() > 0) {
            paramListSb.deleteCharAt(paramListSb.length()-1);
        }        
        return paramListSb.toString();
    }
    
    private String nullParamsConstructor(APIMethod cnstr, SortedSet<APIMethod> constructors) {
        
        // default constructor can't be tested with null values.
        if(cnstr.getParameters().isEmpty()) return null;
        
        String cstr = getNullParamString(cnstr.getParameters());
        
        // Check if there is no other same constructo
        // if exists, return null
        for(APIMethod c : constructors) {
            if(!c.equals(cnstr)) {
                if(cstr.equals(getNullParamString(c.getParameters()))) return null;
            }
        }
        
        // generate constructor
        StringBuilder sb = new StringBuilder();
        sb.append(cnstr.getName()).append(" nullinstance = new ").append(cnstr.getName()).append("(");
        sb.append(cstr);        
        sb.append(");");
        return sb.toString();
    }
    
    private String getNullParamString(List<String> parameters) {        
        StringBuilder sb = new StringBuilder(); {
            for(String s : parameters) {
                sb.append(getDefaultPrimitiveValue(s)).append(',');
            }
        }
        if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    private String getDefaultPrimitiveValue(String name) {
        if(name.equals("byte")) return "0";
        if(name.equals("short")) return "0";
        if(name.equals("int")) return "0";
        if(name.equals("long")) return "0";
        if(name.equals("float")) return "0.0";
        if(name.equals("double")) return "0.0";
        if(name.equals("boolean")) return "false";
        if(name.equals("char")) return "'a'";
        return "null";
    }
    
}
