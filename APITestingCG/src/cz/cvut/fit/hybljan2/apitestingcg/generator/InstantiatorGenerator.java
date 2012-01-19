package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Generates instantiators for all nonabstract classes in API.
 * @author Jan Hybl
 */
public class InstantiatorGenerator extends ClassGenerator {

    @Override
    public void generate(APIClass cls, GeneratorJobConfiguration jobConfiguration) {

        setPackageName(generateName(jobConfiguration.getOutputPackage(), cls.getPackageName()));

        // Instantiator has to import tested class.
        addImport(cls.getFullName());

        // import all package of class... TODO: remove this
        addImport(cls.getPackageName() + ".*");

        // Also import classes and interfaces that class is extending resp. implementing.
        if(cls.getExtending() != null) addImport(getFullClassName(cls, cls.getExtending()));//cgen.addImport(cls.getExtending());
        for(String iname : cls.getImplementing()) {
            addImport(getFullClassName(cls, iname));////cgen.addImport(iname);
        }

        // class header
        setName(generateName(configuration.getInstantiatorClassIdentifier(), cls.getName()));

        // generate constructors
        List<MethodGenerator> constructors = generateConstructors(cls);
        addConstructors(constructors);

        // generate method callers
        List<MethodGenerator> callers = generateMethodCallers(cls);
        addMethods(callers);

        // generate fields test methods
        List<MethodGenerator> fieldTest = generateFieldTests(cls);
        addMethods(fieldTest);

        generateClassFile();
    }

    private String getFullClassName(APIClass cls, String simpleName) {
        if(simpleName.contains(".")) {
            return simpleName;
        } else {
            return cls.getFullName().substring(0, cls.getFullName().lastIndexOf(".")) + "." + simpleName;
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
                MethodGenerator method = new InstantiatorConstructorGenerator(cls.getName(), constructor, configuration);
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

                    MethodGenerator nconst = new InstantiatorConstructorGenerator(cls.getName(), constructor, cstr, configuration);
                    // add constructor method to the class
                    result.add(nconst);
                } catch (Exception ex) {}                                                            
            }
        }
        
        // if class extends other class, generate superconstructor
        if(cls.getExtending() != null && cls.getConstructors().size() > 0) {
            MethodGenerator scnstr = new InstantiatorConstructorGenerator(cls.getExtending(), cls.getConstructors().first(), configuration);
            result.add(scnstr);
        }
        
        // if class implements some interface, create instance of the interface
        for(String intName : cls.getImplementing()) {
            if(!cls.getConstructors().isEmpty()) {
                MethodGenerator icnstr = new InstantiatorConstructorGenerator(intName, cls.getConstructors().first(), configuration);
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

                MethodGenerator callerMethod = new MethodCallerMethodGenerator(method, cls, configuration);
                result.add(callerMethod);
                    
                // Generate same method with null parameters
                try {
                    if(method.getParameters().isEmpty())
                        throw new Exception("method with no parameters can't be called with null params.");
                    String nullParamString = getNullParamString(method.getParameters());

                    // Check if there is no other same method (with equal name and parameters)
                    for(APIMethod mthd : cls.getMethods()) {
                        if(!mthd.equals(method)) {
                            if(mthd.getName().equals(method.getName()) && nullParamString.equals(getNullParamString(mthd.getParameters())))
                                throw new Exception("Cant generate null method.");
                        }
                    }

                    MethodGenerator nullCallerMethod = new MethodCallerMethodGenerator(method, cls, nullParamString, configuration);
                    result.add(nullCallerMethod);
                } catch (Exception ex) {
                    /*
                    In some cases, nullCaller can't be generated. First case is if tested method has no parameters.
                    Second case is if in tested class is other method with same name and same parameters.
                    If one of these cases come, exception is thrown and generating of this nullCaller is skipped.
                    */
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
                MethodGenerator fmg = new FieldTestMehtodGenerator(cls, field, getInstance(field.getModifiers(), cls) + '.' + field.getName(), configuration);
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
    
    private String getNullParamString(List<String> parameters) {        
        StringBuilder sb = new StringBuilder(); {
            for(String s : parameters) {
                sb.append(getDefaultPrimitiveValue(s)).append(',');
            }
        }
        if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
