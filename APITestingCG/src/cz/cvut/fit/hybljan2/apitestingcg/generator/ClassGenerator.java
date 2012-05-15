package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorJobConfiguration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 14:11
 */
public abstract class ClassGenerator extends Generator {
    /**
     * Code model of the generated class.
     */
    protected JDefinedClass cls;

    /**
     * Stack of generated classes. Used for generating of the nested classes.
     */
    protected Stack<JDefinedClass> classStack = new Stack<>();

    /**
     * Class from the scanned API that is currently processed.
     */
    protected APIClass visitingClass;


    protected JBlock fieldsMethodBlock;
    protected JExpression fieldsInstance;

    private Map<String, ClassReference> classReferenceMap;

    private class ClassReference {
        private APIModifier accessModifier;
        private JClass refence;

        public ClassReference(JClass reference, APIModifier accessModifier) {
            this.refence = reference;
            this.accessModifier = accessModifier;
        }

        public APIModifier getAccessModifier() {
            return accessModifier;
        }

        public JClass getRefence() {
            return refence;
        }
    }

    public ClassGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void generate(API api, GeneratorJobConfiguration job) {
        classReferenceMap = new HashMap<>();
        super.generate(api, job);
    }

    public JDefinedClass declareNewClass(int classMods, String packageName, String className, boolean nested) throws JClassAlreadyExistsException {
        JDefinedClass result;
        if (nested) {
            result = classStack.peek()._class(classMods, className, ClassType.CLASS);
        } else {
            result = cm._class(classMods, packageName + '.' + className, ClassType.CLASS);
        }
        return result;
    }

    public String generateGenericsString(Map<String, String[]> typeParamsMap) {
        StringBuilder sb = new StringBuilder();
        int typesCounter = 0;
        for (String typeName : typeParamsMap.keySet()) {
            if (typesCounter > 0) {
                sb.append(", ");
            }
            sb.append(typeName);
            int boundsCounter = 0;
            for (String typeBound : typeParamsMap.get(typeName)) {
                if (!typeBound.equals("java.lang.Object")) {
                    JClass boundClass = getClassRef(typeBound);
                    if (boundsCounter > 0) {
                        sb.append(" & ");
                    } else {
                        sb.append(" extends ");
                    }
                    sb.append(boundClass.name());
                    boundsCounter++;
                }
            }
            typesCounter++;
        }

        return sb.toString();
    }

    /**
     *
     * @param type
     * @return
     */
    protected JClass getTypeRef(APIType type, Collection<String> genericClasses) {
        // get reference to a base class of the type
        JClass typeReference = getTypeRef(type.getName(), genericClasses);

        // get references to the type argument classes
        for(APIType typeArgument : type.getTypeArgs()) {
            typeReference = typeReference.narrow(getTypeRef(typeArgument, genericClasses));
        }

        if(type.isArray()) {
            return typeReference.array();
        } else {
            return typeReference;
        }
    }

    protected JClass getTypeRef(APIType className) {
       return getTypeRef(className, null);
    }
    
    /**
     * TODO: rename the method to getClassRef when old getClassRef will be removed.
     * @param className
     * @param genericClasses    method defined generics types.
     * @return
     */
    protected JClass getTypeRef(String className, Collection<String> genericClasses) {
        JClass typeReference = null;
        if(classReferenceMap.containsKey(className)) {
            typeReference = classReferenceMap.get(className).getRefence();
        } else {
            try {
                APIClass cls = findClass(className);
                APIModifier accessModifier = APIModifier.PRIVATE;

                if (cls.getModifiers().contains(APIModifier.PUBLIC)) {
                    accessModifier = APIModifier.PUBLIC;
                } else if (cls.getModifiers().contains(APIModifier.PROTECTED)) {
                    accessModifier = APIModifier.PROTECTED;
                    // Because the protected classes can't be imported, we have to work in a generated
                    // code only with the short names and be sure the type is used only at places where
                    // the class can be referenced with short names (extender).
                    className = className.substring(className.lastIndexOf('.') + 1);
                }

                typeReference = cm.ref(className);

                classReferenceMap.put(className, new ClassReference(typeReference, accessModifier));
            } catch (ClassNotFoundException e) {
                if(visitingClass.getTypeParamsMap().keySet().contains(className)
                        || (genericClasses != null && genericClasses.contains(className))) {
                    typeReference = cm.ref(className);
                } else {
                    System.err.println("Class Not Found:" + className);
                }
            }
        }

        return typeReference;
    }

    /**
     * Method checks if all classes in type can be accessed with given minimal access level.
     * @param minimalAccessLevel
     * @param verifiedType
     * @param genericClasses    method defined generics types.
     * @return
     */
    protected boolean checkTypeAccessModifier(APIModifier minimalAccessLevel, APIType verifiedType, Collection<String> genericClasses) {
        try {
            APIClass cls = findClass(verifiedType.getName());
            if(!APIModifier.checkAccessLevel(minimalAccessLevel, cls)) {
                return false;
            }
        } catch (ClassNotFoundException e) {
            if ((!visitingClass.getTypeParamsMap().keySet().contains(verifiedType.getName()))
                    && !(genericClasses != null && genericClasses.contains(verifiedType.getName()))) {
                System.err.println("Class \""+ verifiedType.getName() +"\" not found.");
                return false;
            }
        }

        for(APIType typeArg : verifiedType.getTypeArgs()) {
            try {
                APIClass cls = findClass(typeArg.getName());
                if(!APIModifier.checkAccessLevel(minimalAccessLevel, cls)) {
                    return false;
                }
            } catch (ClassNotFoundException e) {
                if (!genericClasses.contains(typeArg.getName())) {
                    System.err.println("Class \""+ typeArg.getName() +"\" not found.");
                    return false;
                }
            }
        }



        return true;
    }

}
