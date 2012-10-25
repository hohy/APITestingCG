package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 14:11
 */
public class ExtenderGenerator extends ClassGenerator {

    public ExtenderGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void visit(APIClass apiClass) {

        // extender can be generated for classes and interfaces
        if ((!apiClass.getKind().equals(APIItem.Kind.CLASS)) && (!apiClass.getKind().equals(APIItem.Kind.INTERFACE))) {
            return;
        }

        // check if extender for this class is enabled in jobConfiguration.
        if (!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        //  check if tested class is not final
        if (apiClass.getModifiers().contains(APIModifier.FINAL)) {
            return;
        }

        // check if class is not deprecated. If it does and in job configuration
        // are deprecated items disabled, this class is skipped.
        if (apiClass.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }

        // code can be generated only for public or protected classes.
        if (!(apiClass.getModifiers().contains(APIModifier.PUBLIC)
                || apiClass.getModifiers().contains(APIModifier.PROTECTED))) {
            return;
        }

        // check if extender has at least one protected or public constructor.
        // If it hasn't, extender can't be generated.
        if (apiClass.getConstructors().isEmpty()) {
            return;
        }

        try {
            visitingClass = apiClass;
            int classMods = 0;
//            if (visitingClass.getModifiers().contains(APIModifier.ABSTRACT)
//                    || apiClass.getKind() == APIItem.Kind.INTERFACE) {
//                classMods = JMod.PUBLIC | JMod.ABSTRACT;
            if (!visitingClass.isNested()) {
                classMods = JMod.PUBLIC;
            }

            // if tested item is interface, create Implementator, otherwise Extender
            if (visitingClass.getKind() == APIItem.Kind.INTERFACE) {
                String className = generateName(configuration.getImplementerClassIdentifier(), apiClass.getName());
                cls = declareNewClass(classMods, currentPackageName, className, visitingClass.isNested());
                cls._implements(getTypeRef(visitingClass.getType(), visitingClass.getTypeParamsMap().keySet()));
            } else {
                String className = generateName(configuration.getExtenderClassIdentifier(), apiClass.getName());
                cls = declareNewClass(classMods, currentPackageName, className, visitingClass.isNested());
                cls._extends(getTypeRef(visitingClass.getType(), visitingClass.getTypeParamsMap().keySet()));
            }

            if (!apiClass.getTypeParamsMap().isEmpty()) {
                for (String typeName : apiClass.getTypeParamsMap().keySet()) {
                    JTypeVar type = cls.generify(typeName);
                    for (APIType bound : visitingClass.getTypeParamsMap().get(typeName)) {
                        JClass typeBound = getTypeRef(bound, visitingClass.getTypeParamsMap().keySet());
                        if(!bound.equals(new APIType("java.lang.Object"))) {
                            type.bound(typeBound);
                        }
                    }
                }
            }

            // visit all constructors
            for (APIMethod constructor : apiClass.getConstructors()) {
                visitConstructor(constructor);
            }

            // visit all methods
            for (APIMethod method : apiClass.getMethods()) {
                method.accept(this);
            }

            // create method for fields test if there are any field
            if (apiClass.getFields().size() > 0) {
                JMethod fieldsMethod = cls.method(JMod.PUBLIC, cm.VOID, configuration.getFieldTestIdentifier());
                fieldsMethodBlock = fieldsMethod.body();
            }
            // visit all fields
            for (APIField field : apiClass.getFields()) {
                field.accept(this);
            }

            // implement all abstract method of ancestors (super class and implemented interfaces) of current class
            if (visitingClass.getExtending() != null) {
                implementAbstractMethods(visitingClass.getExtending().getName());
            }
            for (APIType type : visitingClass.getImplementing()) {
                implementAbstractMethods(type.getName());
            }

            classStack.push(cls);

            // visit all nested classes
            for (APIClass nestedClass : apiClass.getNestedClasses()) {
                nestedClass.accept(this);
            }

            cls = classStack.pop();
        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private void implementAbstractMethods(String className) {
        try {
            APIClass cls = findClass(className);
            for (APIMethod mth : cls.getMethods()) {
                if (mth.getModifiers().contains(APIModifier.ABSTRACT)) {
                if (!checkIfMethodIsAlreadyImplemented(cls, mth)) {
                        // all generic types in overridden method has to be replaced with their bounds.
                        // Type params has to be deleted.
                        mth.setReturnType(simplifyType(mth.getReturnType(),cls.getTypeParamsMap(),mth.getTypeParamsMap()));
                        for (APIMethodParameter p : mth.getParameters()) {
                            p.setType(simplifyType(p.getType(),cls.getTypeParamsMap(),mth.getTypeParamsMap()));
                        }
                        mth.getTypeParamsMap().clear();
                        mth.getThrown().clear();
                        addOverridingMethod(mth);
                }

//                    boolean alreadyImplemented = false;
//                    for(APIMethod implementedMethod : visitingClass.getMethods()) {
//                        if (implementedMethod.getName().equals(mth.getName())
//                                && checkParamType(implementedMethod.getReturnType(), mth.getReturnType())
//                                && checkParamList(mth.getParameters(), implementedMethod.getParameters())) {
//                            alreadyImplemented = true;
//                            break;
//                        }
//                    }
//                    if (!alreadyImplemented) {
//                        // all generic types in overridden method has to be replaced with their bounds.
//                        // Type params has to be deleted.
//                        mth.setReturnType(simplifyType(mth.getReturnType(),cls.getTypeParamsMap(),mth.getTypeParamsMap()));
//                        for (APIMethodParameter p : mth.getParameters()) {
//                            p.setType(simplifyType(p.getType(),cls.getTypeParamsMap(),mth.getTypeParamsMap()));
//                        }
//                        mth.getTypeParamsMap().clear();
//                        addOverridingMethod(mth);
//                    }
                }
            }
            if(cls.getExtending() != null) {
                implementAbstractMethods(cls.getExtending().getName());
            }
            for(APIType intrface : cls.getImplementing()) {
                implementAbstractMethods(intrface.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This huge and really ugly method check if method was already implemented in current exteder.
     * @param originalClass
     * @param originalMethod
     * @return
     */
    private boolean checkIfMethodIsAlreadyImplemented(APIClass originalClass, APIMethod originalMethod) {
        // we have to check all already implemented methods.
        for (JMethod implementedMethod : cls.methods()) {
            // name has to be same, if not try next one.
            if (!implementedMethod.name().equals(originalMethod.getName())) {
                continue;
            }

            // check method parameters compatibility
            if(originalMethod.getParameters().size() != implementedMethod.params().size()) {
                continue;
            }
            boolean incompatibleParameters = false;
            APIType originalType, implementedType;
            Map<String, APIType[]> methodTypeParams = convertTypeParams(implementedMethod.typeParams());
            for(int i = 0; i < originalMethod.getParameters().size(); i++) {
                originalType = prepareType(originalMethod.getParameters().get(i).getType(),
                        originalClass.getTypeParamsMap(),
                        originalMethod.getTypeParamsMap());
                implementedType = prepareType(new APIType(implementedMethod.params().get(i).type().fullName()),
                        visitingClass.getTypeParamsMap(),
                        methodTypeParams);
                if(!checkParamType(originalType,implementedType)) {
                    incompatibleParameters = true;
                    break;
                }
            }
            // if parameters are not comaptible, whole method is not.
            if(incompatibleParameters) {
                continue;
            }

            // check return types compatibility.
            originalType = prepareType(originalMethod.getReturnType(),
                    originalClass.getTypeParamsMap(),
                    originalMethod.getTypeParamsMap());
            implementedType = prepareType(new APIType(implementedMethod.type().fullName()),
                    visitingClass.getTypeParamsMap(),
                    methodTypeParams);

            // same applies to return type.
            if(!checkParamType(originalType,implementedType)) {
                continue;
            }

            // Ok, current implementedMethod is compatible with originalMethod, so it is already implemented.
            return true;
        }
        // No already implemented method was found in current class
        return false;
    }

    private static Map<String, APIType[]> convertTypeParams(JTypeVar[] typeVars) {
        Map<String, APIType[]> result = new HashMap<>();
        for (JTypeVar typeVar : typeVars) {
            String name = typeVar.fullName();
            APIType[] t = new APIType[1];
            t[0] = new APIType(typeVar._extends().fullName());
            result.put(name,t);
        }
        return result;
    }
    
    private static APIType prepareType(APIType type, Map<String, APIType[]> classTypeParams, Map<String, APIType[]> methodTypeParams) {

        // try to find it in class type params.
        if(classTypeParams.containsKey(type.getName())) {
            return classTypeParams.get(type.getName())[0];
        }
        // if it wasn't found, try to find it in method type params.
        if(methodTypeParams.containsKey(type.getName())) {
            return methodTypeParams.get(type.getName())[0];
        }

        return type;
    }
    
    /**
     * Returns true if all parameters in extender method are equal or subtypes of parameters in original method.
     * Used for checking if method is already implemented in extender. It's not the nicest solution but I have no
     * idea how to solve it better.
     * @param parametersOriginal
     * @param parametersExtender
     * @return
     */
    private static boolean checkParamList(List<APIMethodParameter> parametersOriginal, List<APIMethodParameter> parametersExtender) {
        if(parametersExtender.size() == 0) {
            return true;
        } else if(parametersExtender.size() != parametersOriginal.size()) {
            return false;
        }

        for (int i = 0; i < parametersExtender.size(); i++) {
            if (!checkParamType(parametersOriginal.get(i).getType(), parametersExtender.get(i).getType())) return false;
        }

        return true;
    }

    /**
     * Returns true if parameter type in extender method is equal to or extending coresponding parameter type in
     * original method. Used for checking if method is already implemented in extender. It's not the nicest solution
     * but I have no idea how to solve it better.
     * @param parameterTypeOriginal
     * @param parameterTypeExtender
     * @return
     */
    private static boolean checkParamType(APIType parameterTypeOriginal, APIType parameterTypeExtender) {

        if(parameterTypeExtender.getName().equals(parameterTypeOriginal.getName())) {
            return true;
        }

        Class typeOriginal, typeExtender;
        try {
            typeOriginal = Class.forName(parameterTypeOriginal.getName());
        } catch (ClassNotFoundException e) {
            try {
                typeOriginal = Class.forName(parameterTypeOriginal.getFlatName());
            } catch (ClassNotFoundException e1) {
                //throw new RuntimeException("Class " + parameterTypeOriginal.getName()  + " not found.");
                return false;
            }
        }

        try {
            typeExtender = Class.forName(parameterTypeExtender.getName());
        } catch (ClassNotFoundException e) {
            try {
                typeExtender = Class.forName(parameterTypeExtender.getFlatName());
            } catch (ClassNotFoundException e1) {
                //throw new RuntimeException("Class " + parameterTypeExtender.getName()  + " not found.");
                return false;
            }
        }

        return typeOriginal.isAssignableFrom(typeExtender);
    }

    private APIType simplifyType(APIType type, Map<String, APIType[]> clsGenerics, Map<String,APIType[]> mtdGenerics) {
        type.setBound(APIType.BoundType.NULL);
        type.getTypeArgs().clear();
        APIType[] genericTypes = clsGenerics.get(type.getName());
        if(genericTypes != null) {
            if(genericTypes.length > 0) {
                type.setName(genericTypes[0].getName());
            } else {
                type.setName("java.lang.Object");
            }
        } else {
            genericTypes = mtdGenerics.get(type.getName());
            if(genericTypes != null) {
                if(genericTypes.length > 0) {
                    type.setName(genericTypes[0].getName());
                } else {
                    type.setName("java.lang.Object");
                }
            }
        }
        return type;
    }
    
    private void visitConstructor(APIMethod constructor) {
        // Check if constructor is enabled in job configuration.
        if (!isEnabled(methodSignature(constructor, visitingClass.getFullName()), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        if (constructor.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }

        // create new constructor
        JMethod constr = cls.constructor(JMod.PUBLIC);
        // define body of the constructor. Body contains only super(...) call.
        JBlock body = constr.body();
        JInvocation superInv = body.invoke("super");

        // add generic types
        if (!constructor.getTypeParamsMap().isEmpty()) {
            for (String typeName : constructor.getTypeParamsMap().keySet()) {
                JTypeVar type = constr.generify(typeName);
                for (APIType bound : constructor.getTypeParamsMap().get(typeName)) {
                    JClass typeBound = getTypeRef(bound, constructor.getTypeParamsMap().keySet());
                    if (!bound.equals(new APIType("java.lang.Object"))) {
                        type.bound(typeBound);
                    }
                }
            }
        }

        // define params of the constructor.
        for (APIMethodParameter param : constructor.getParameters()) {
            JType type = getTypeRef(param.getType(), constructor.getTypeParamsMap().keySet());
            constr.param(type, String.valueOf(param.getName()));
            superInv.arg(JExpr.ref(String.valueOf(param.getName())));
        }

        for (String exception : constructor.getThrown()) {
            constr._throws(getTypeRef(exception, constructor.getTypeParamsMap().keySet()));
        }
    }

    /**
     * Generates test of the field.
     * Final fields are tested by assigning their value to new field of same type. Ex: {@code int x = super.x;}.
     * New local variable x hides original super field x, but it doesn't mind.
     * Non-final fields are tested by assigning some value to them. Ex: {@code File f = null; fileField = f;}
     *
     * @param apiField
     */
    @Override
    public void visit(APIField apiField) {

        if (apiField.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }

        // Type of the field has to be public or protected class
        if(!checkTypeAccessModifier(APIModifier.PROTECTED, apiField.getVarType(), visitingClass.getTypeParamsMap().keySet())) {
            return;
        }

        if (apiField.getModifiers().contains(APIModifier.FINAL)) { // Final fields
            // original field
            JFieldRef fld;
            if (apiField.getModifiers().contains(APIModifier.STATIC)) {
                fld = getTypeRef(visitingClass.getFullName(), visitingClass.getTypeParamsMap().keySet()).staticRef(apiField.getName());
            } else {
                fld = JExpr._super().ref(apiField.getName());
            }
            // create new local variable and assing original value to it
            fieldsMethodBlock.decl(getTypeRef(apiField.getVarType(), visitingClass.getTypeParamsMap().keySet()), apiField.getName(), fld);
        } else {
            // create new field of same type as original
            String fldName = generateName(configuration.getFieldTestVariableIdentifier(), apiField.getName());
            JClass typeRef = getTypeRef(apiField.getVarType(), visitingClass.getTypeParamsMap().keySet());
            JVar var = fieldsMethodBlock.decl(typeRef, fldName, getPrimitiveValue(apiField.getVarType().getName()));
            fieldsMethodBlock.assign(JExpr.ref(apiField.getName()), var);
        }
        //fieldsMethodBlock.directStatement(" ");

    }

    @Override
    public void visit(APIMethod method) {
        // check if method is enabled in configuration.
        if (!isEnabled(methodSignature(method, visitingClass.getFullName()), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        // Extender can't override final methods
        if (method.getModifiers().contains(APIModifier.FINAL)) {
            return;
        }

        // Extender can't override static methods
        if (method.getModifiers().contains(APIModifier.STATIC)) {
            return;
        }

        // only public or protected method can be tested by extender
        if (!(method.getModifiers().contains(APIModifier.PUBLIC)
                || method.getModifiers().contains(APIModifier.PROTECTED))) {
            return;
        }

        if (method.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }

        addOverridingMethod(method);

    }

    /**
     * Add new method to current generated class which overrides original method specified as parameter.
     * @param method
     */
    private void addOverridingMethod(APIMethod method) {

        // return type have to be public or protected class
        if (!checkTypeAccessModifier(APIModifier.PROTECTED, method.getReturnType(),method.getTypeParamsMap().keySet())) {
            if(method.getModifiers().contains(APIModifier.ABSTRACT)) {
                cls.mods().setAbstract(true);
            }
            return;
        }

        // all methods params has to be public or protected classes
        for (APIMethodParameter paramType : method.getParameters()) {

            if (!checkTypeAccessModifier(APIModifier.PROTECTED,paramType.getType(), method.getTypeParamsMap().keySet())) {
                if(method.getModifiers().contains(APIModifier.ABSTRACT)) {
                    cls.mods().setAbstract(true);
                }
                return;
            }

        }

        JClass extenderReturnType = getTypeRef(method.getReturnType(), method.getTypeParamsMap().keySet());
        // define new method
        JMethod mthd = cls.method(JMod.PUBLIC, extenderReturnType, method.getName());

        if (!method.getTypeParamsMap().isEmpty()) {
            for (String typeName : method.getTypeParamsMap().keySet()) {
                JTypeVar type = mthd.generify(typeName);
                for (APIType bound : method.getTypeParamsMap().get(typeName)) {
                    JClass typeBound = getTypeRef(bound, method.getTypeParamsMap().keySet());
                    if (!bound.equals(new APIType("java.lang.Object"))) {
                        type.bound(typeBound);
                    }
                }
            }
        }

        // set body of the method. = return super.method(...);
        mthd.body()._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)));

        // add params to method. New method has same params as overridden method.
        for (APIMethodParameter param : method.getParameters()) {
            boolean array = false;
            if (param.getType().isArray()) {
                array = true;
            }
            JClass paramType = getTypeRef(param.getType(),method.getTypeParamsMap().keySet());

            if (array) {
                paramType.array();
            }
            mthd.param(paramType, String.valueOf(param.getName()));
        }

        // add override annotation to the method.
        JAnnotationUse annotation = mthd.annotate(cm.ref(java.lang.Override.class));

        // add list of thrown methods. Must be same as list of original class
        for (String thrown : method.getThrown()) {
            mthd._throws(getTypeRef(thrown, method.getTypeParamsMap().keySet()));
        }
    }

    protected static String getParamArg(String className) {
        if (className.contains("<")) {
            return className.substring(className.indexOf('<') + 1, className.lastIndexOf('>')).trim();
        }
        return null;
    }

    /**
     * Checks if given type is public or protected. Type could be simple class (<code>java.util.List</code>) or complex type
     * (<code>java.util.Map<java.lang.String, java.lang.List<java.io.File>></code>). Result is true if every class
     * used in type definition is public or protected.
     *
     * @param type           Definition of the type
     * @param genericClasses List of the generics classes.
     * @return
     */
    protected boolean isTypePublicOrProtected(String type, Collection<String> genericClasses) {
        boolean result = true;
        // Split complex type to individual classes
        Set<String> classNames = getTypesList(type);
        // check public accessibility of every single class
        for (String className : classNames) {
            // check if it's generic class or wildcard
            if (!((genericClasses != null && genericClasses.contains(className)) || (className.equals("?")))) {
                // if class is not generic, use isClassPublic method to determine if class is public
                if (!isClassPublicOrProtected(className)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Finds class with given name and checks if the class has public modifier.
     *
     * @param name full class name
     * @return true or false if class is public or not. If class is not found (it's not part of API), returns false
     */
    protected boolean isClassPublicOrProtected(String name) {

        if (visitingClass.getTypeParamsMap().keySet().contains(name)) {
            return true;
        }

        try {
            APIClass c = findClass(name);
            if (c.getModifiers().contains(APIModifier.PUBLIC)
                    || (c.getModifiers().contains(APIModifier.PROTECTED))) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            try {
                APIClass nc = visitingClass.getNestedClass(name);
                return (nc.getModifiers().contains(APIModifier.PUBLIC)
                        || (nc.getModifiers().contains(APIModifier.PROTECTED)));
            } catch (ClassNotFoundException e1) {
                System.err.println("isClassPublicOrProtected() - Class not found: " + name);
            }
        }

        return true;
    }

}