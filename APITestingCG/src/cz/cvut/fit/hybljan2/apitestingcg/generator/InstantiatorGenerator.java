package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.util.LinkedList;
import java.util.List;

/**
 * Generates instantiators for all nonabstract classes in API.
 * @author Jan Hybl
 */
public class InstantiatorGenerator extends Generator {

    // name of object used to call non-static methods
    public static final String INSTANCE_OBJECT_NAME = "p";
    // identifier of "Caller" - Method that call some method from tested library.
    public static final String METHOD_CALL_IDENTIFIER = "";
    // identifier of Instantiator classes. Example: instantiator for class File is File[INSTANTIATOR_CLASS_IDENTIFIER]
    public static final String INSTANTIATOR_CLASS_IDENTIFIER = "Instantiator";

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
                    cgen.setName(cls.getName() + INSTANTIATOR_CLASS_IDENTIFIER);
                    
                    // generate constructors
                    List<MethodGenerator> constructors = generateConstructors(cls);
                    cgen.addConstructors(constructors);

                    // generate method callers
                    List<MethodGenerator> callers = generateMethodCallers(cls);                
                    cgen.addMethods(callers);
                    
                    // generate fields test methods
                    List<MethodGenerator> fieldTest = generateFieldTests(cls);
                    cgen.addMethods(fieldTest);

                    cgen.generateClassFile();
                }
            }
        }
    }

    private List<MethodGenerator> generateConstructors(APIClass cls) {        
        // Constructor testing
        List<MethodGenerator> result = new LinkedList<MethodGenerator>();
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
                method.setBody(generateConstructorBody(cls, getMethodParamNameList(params)));
                result.add(method);
                
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
                                throw new Exception("Can't do null constructor.");                                            
                        }
                    }

                    MethodGenerator nconst = new MethodGenerator();
                    // TODO: find better name
                    nconst.setName("create" + cls.getName() + "NullInstance");
                    nconst.setModifiers("public");
                    nconst.setReturnType(cls.getName());
                    nconst.setBody(generateConstructorBody(cls, cstr));                             
                    // add constructor method to the class
                    result.add(nconst);
                } catch (Exception ex) {}                                                            
            }
        }
        
        // if class extends other class, generate superconstructor
        if(cls.getExtending() != null && cls.getConstructors().size() > 0) {
            MethodGenerator scnstr = new MethodGenerator();
            scnstr.setModifiers("public");
            scnstr.setReturnType(cls.getExtending());
            scnstr.setName("create" + scnstr.getReturnType() + "SuperInstance");            
            List<String[]> params = getMethodParamList(cls.getConstructors().first());
            scnstr.setParams(params);
            scnstr.setBody(generateConstructorBody(cls, getMethodParamNameList(params)));
            result.add(scnstr);                    
        }
        
        // if class implements some constructor, generate instance of interface
        for(String intName : cls.getImplementing()) {
            MethodGenerator icnstr = new MethodGenerator();
            icnstr.setModifiers("public");
            icnstr.setReturnType(intName);
            icnstr.setName("create" + icnstr.getReturnType() + "InterfaceInstance");
            if(cls.getConstructors().size() > 0) { // TODO: Toto zkontrolovat, otestovat, jestli to tak muze byt...
                List<String[]> params = getMethodParamList(cls.getConstructors().first());
                icnstr.setParams(params);
                icnstr.setBody(generateConstructorBody(cls, getMethodParamNameList(params)));
                result.add(icnstr);
            }                                       
        }
        return result;
    }

    private List<MethodGenerator> generateMethodCallers(APIClass cls) {
        List<MethodGenerator> result = new LinkedList<MethodGenerator>();
        // list of callers (methods that test method calling)
        for(APIMethod method : cls.getMethods()) {
            // instantiator can test only public methods
            if(method.getModifiers().contains(Modifier.PUBLIC)) {
                MethodGenerator callerMethod = new MethodGenerator();
                List<String[]> params = getMethodParamList(method);

                // if method isn't static, add instance param to param list
                if(!method.getModifiers().contains(Modifier.STATIC)) {
                    params.add(new String[] {cls.getName(), INSTANCE_OBJECT_NAME});
                }
                callerMethod.setParams(params);
                callerMethod.setModifiers("public");
                // generated method return result of test method, so it has to have same return type
                callerMethod.setReturnType(method.getReturnType());
                callerMethod.setName(method.getName() + METHOD_CALL_IDENTIFIER);
                String body = generateCallerBody(method, cls);
                callerMethod.setBody(body);
                result.add(callerMethod);
                    
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
                        ncaller.setParams(params);
                        String nbody = generateCallerBody(method, cls, cstr);
                        ncaller.setBody(nbody);
                        result.add(ncaller);
                    } catch (Exception ex) {
                        //Logger.getLogger(InstantiatorGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
            }                    
        }
        return result;
    }

    private List<MethodGenerator> generateFieldTests(APIClass cls) {
        List<MethodGenerator> result = new LinkedList<MethodGenerator>();
        
        for (APIField field : cls.getFields()) {
            // only public fields can be tested in instantiator
            if(field.getModifiers().contains(Modifier.PUBLIC)) {
                MethodGenerator fmg = new MethodGenerator();
                fmg.setModifiers("public");
                fmg.setName(field.getName() + "Field");
                fmg.setReturnType("void");
                // if method is not static, instance is param.
                if(!field.getModifiers().contains(Modifier.STATIC)) {
                    List<String[]> params = new LinkedList<String[]>();
                    params.add(new String[] {cls.getName(), "instance"});
                    fmg.setParams(params);
                }
                StringBuilder sb = new StringBuilder();                
                String var = getInstance(field.getModifiers(), cls) + '.' + field.getName();
                // if field is final, print it, if not, write something into it.
                if(field.getModifiers().contains(Modifier.FINAL)) {
                    sb.append("\t\tSystem.out.println(").append(var).append(");");
                } else {
                    sb.append("\t\t").append(var).append(" = ").append(getDefaultPrimitiveValue(field.getVarType())).append(';');
                }               
                fmg.setBody(sb.toString());
                result.add(fmg);
            }
        }
        
        return result;
    }    
    
    private String generateConstructorBody(APIClass cls, String cstr) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\treturn new ").append(cls.getName()).append("(");
        sb.append(cstr);
        sb.append(");\n");       
        return sb.toString();
    }

    private String generateCallerBody(APIMethod method, APIClass cls, String paramsString) {
        
        // String builder for creating command line with following structure: ("return ")? instance.method(params);
        StringBuilder cmdSB = new StringBuilder("\t\t");
        
        // if method return something, command starts with return.
        if(!method.getReturnType().equals("void")) cmdSB.append("return ");
        // instance of tested class that will be used to call method.
        cmdSB.append(getInstance(method.getModifiers(), cls));
        cmdSB.append('.');
        // name of tested method
        cmdSB.append(method.getName());
        cmdSB.append('(');
        cmdSB.append(paramsString);
        cmdSB.append(");");

        // if method throws any exception, surround command with try-catch command.
        if(!method.getThrown().isEmpty()) {
            cmdSB.insert(0, "\t\ttry {\n\t");
            for(String exception : method.getThrown()) {
                cmdSB.append("\n\t\t} catch (").append(exception).append(" ex) {");
            }
            cmdSB.append('}');
        }
        return cmdSB.toString();
    }
    
    private String getInstance(List<Modifier> modifiers, APIClass cls) {
        // if method is static, call it on class, if not, call it on instance parameter
        return modifiers.contains(Modifier.STATIC) ? cls.getName() : INSTANCE_OBJECT_NAME;
    }

    private String generateCallerBody(APIMethod method, APIClass cls) {
        return generateCallerBody(method, cls, getMethodParamNameList(getMethodParamList(method)));
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
