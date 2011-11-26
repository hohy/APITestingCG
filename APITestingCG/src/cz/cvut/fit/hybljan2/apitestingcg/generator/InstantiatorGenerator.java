package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.tools.doclets.internal.toolkit.builders.MethodBuilder;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
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
        // get all packages in api
        for(APIPackage pkg : api.getPackages()) {
            // get all classes from every package
            for(APIClass cls : pkg.getClasses()) {
                // filter out all abstract classes
                if(!cls.getModifiers().contains(Modifier.ABSTRACT)) {
                ClassGenerator cgen = new ClassGenerator();                                                                       

                cgen.setPackageName(pkg.getName());

                // Instantiator have to import tested class.
                cgen.addImport(cls.getFullName());

                // class header                        
                cgen.setName(cls.getName() + "Instantiator");                        

                // Constructor testing
                // For every constructor in tested class, create method 
                // createClassXIntance, that return ClassX instance
                for(APIMethod constructor : cls.getConstructors()) {
                    // only public constructors can be tested in instantiator
                    if(constructor.getModifiers().contains(Modifier.PUBLIC)) {

                        // generate constructor
                        MethodGenerator method = new MethodGenerator();
                        method.setModifiers("public");
                        method.setName("create" + cls.getName() + "Instance");
                        method.setReturnType(cls.getName());
                        List<String[]> params = getMethodParamList(constructor);
                        method.setParams(params);
                        StringBuilder methodBody = new StringBuilder();
                        methodBody.append("\t\t").append(cls.getName());
                        methodBody.append(" instance = new ").append(cls.getName());
                        methodBody.append('(').append(getMethodParamNameList(params)).append(");\n");
                        methodBody.append("\t\treturn instance;");
                        method.setBody(methodBody.toString());
                        cgen.addConstructor(method);

                        // generate null constructor                                                        
                        try {
                            // nonparam constructor can't be tested with null values.                        
                            if(constructor.getParameters().isEmpty()) 
                                throw new Exception("Nonparam constructor can't be tested with null values.");
                            
                            String cstr = getNullParamString(constructor.getParameters());

                            // Check if there is no other same constructor
                            for(APIMethod c : cls.getConstructors()) {
                                if(!c.equals(constructor)) {
                                    if(cstr.equals(getNullParamString(c.getParameters())))
                                        throw new Exception("Cant do null constructor.");                                            
                                }
                            }

                            MethodGenerator nconst = new MethodGenerator();
                            // TODO: find better name
                            nconst.setName("create" + cls.getName() + "NullInstance");
                            nconst.setModifiers("public");
                            nconst.setReturnType(cls.getName());

                            StringBuilder sb = new StringBuilder();
                            sb.append("\t\t").append(cls.getName()).append(" instance = new ").append(cls.getName()).append("(");
                            sb.append(cstr);        
                            sb.append(");\n");
                            sb.append("\t\treturn instance;");
                            nconst.setBody(sb.toString());
                            // add constructor method to the class
                            cgen.addConstructor(nconst);
                        } catch (Exception ex) {}                                                            
                    }
                }

                // list of callers (methods that test method calling)
                for(APIMethod method : cls.getMethods()) {
                    // instantiator can test only public methods
                    if(method.getModifiers().contains(Modifier.PUBLIC)) {
                        MethodGenerator caller = new MethodGenerator();
                            List<String[]> params = getMethodParamList(method);
                            
                            // if method isn't static, add instance param to param list
                            if(!method.getModifiers().contains(Modifier.STATIC)) {
                                params.add(new String[] {cls.getName(), "instance"});
                            }
                            caller.setParams(params);    
                            caller.setModifiers("public");
                            caller.setReturnType("void");
                            caller.setName(method.getName() + "Call");                                                        
                            // if method returns void, do not check result, if return something, save it to variable result
                            String result = method.getReturnType().equals("void") ? "" : method.getReturnType() + " result = ";                            
                            // if method is static, call it on class, if not, call it on instance parameter
                            String instance = method.getModifiers().contains(Modifier.STATIC) ? cls.getName() : "instance";
                            StringBuilder cbb = new StringBuilder();
                            cbb.append("\t\t").append(result).append(instance).append(".").append(method.getName());
                            cbb.append("(").append(getMethodParamNameList(getMethodParamList(method))).append(");");
                            caller.setBody(cbb.toString());
                            cgen.addMethod(caller);
                            
                            // Generate same method with null parameters
                            try {
                                if(method.getParameters().isEmpty())                                 
                                    throw new Exception("Nonparam method can't be called with null params.");
                                String cstr = getNullParamString(method.getParameters());

                                // Check if there is no other same constructor
                                for(APIMethod c : cls.getMethods()) {
                                    if(!c.equals(method)) {
                                        if(cstr.equals(getNullParamString(c.getParameters())))
                                            throw new Exception("Cant do null method.");                                            
                                    }
                                }
                                MethodGenerator ncaller = new MethodGenerator();
                                ncaller.setModifiers("public");
                                ncaller.setName(method.getName() + "NullCall");
                                ncaller.setReturnType("void");
                                StringBuilder ncbb = new StringBuilder();
                                ncbb.append("\t\t").append(result).append(instance).append(".").append(method.getName());
                                ncbb.append("(").append(cstr).append(");");                                
                                ncaller.setBody(ncbb.toString());
                                cgen.addMethod(ncaller);
                            } catch (Exception ex) {
                                //Logger.getLogger(InstantiatorGenerator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }                    
                }
                cgen.generateClassFile();
                }
            }
        }
    }

    private List<String[]> getMethodParamList(APIMethod method) {
        // String builder for list of params for instantiator constructor
        List<String[]> result = new LinkedList<String[]>();
        char paramName = 'a';

        for(String className : method.getParameters()) {
            String[] p = new String[2];
            p[0] = className;
            p[1] = Character.toString(paramName);
            paramName++;
            result.add(p);
        }
        return result;
    }
    
    private String getMethodParamNameList(List<String[]> params) {
        // String builder for list of params for tested constructor
        StringBuilder paramListSb = new StringBuilder();

        for(String[] param : params) {
            paramListSb.append(param[1]).append(',');
        }

        // remove last ',' if there is any
        if(params.size() > 0) {
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
