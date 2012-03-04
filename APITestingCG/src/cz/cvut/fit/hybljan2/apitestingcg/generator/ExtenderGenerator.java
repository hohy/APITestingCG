package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

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
        if ((!apiClass.getType().equals(APIItem.Kind.CLASS)) && (!apiClass.getType().equals(APIItem.Kind.INTERFACE))) {
            return;
        }

        // check if extender for this class is enabled in jobConfiguration.
        if (!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        //  check if tested class is not final
        if (apiClass.getModifiers().contains(APIModifier.Modifier.FINAL)) {
            return;
        }

        // check if extender has at least one protected or public constructor.
        // If it hasn't, extender can't be generated.
        if (apiClass.getConstructors().isEmpty()) {
            return;
        }

        try {
            visitingClass = apiClass;
            int classMods;
            if (visitingClass.getModifiers().contains(APIModifier.Modifier.ABSTRACT)
                    || apiClass.getType() == APIItem.Kind.INTERFACE) {
                classMods = JMod.PUBLIC | JMod.ABSTRACT;
            } else {
                classMods = JMod.PUBLIC;
            }

            // if tested item is interface, create Implementator, otherwise Extender
            String pattern = null;
            if (apiClass.getType() == APIItem.Kind.INTERFACE) {
                String className = generateName(configuration.getImplementerClassIdentifier(), apiClass.getName());
                cls = cm._class(classMods, currentPackageName + '.' + className, ClassType.CLASS);
                cls._implements(getClassRef(apiClass.getFullName()));
            } else {
                String className = generateName(configuration.getExtenderClassIdentifier(), apiClass.getName());
                cls = cm._class(classMods, currentPackageName + '.' + className, ClassType.CLASS);
                cls._extends(getClassRef(apiClass.getFullName()));
            }

            if (!apiClass.getTypeParamsMap().isEmpty()) {
                cls.generify(generateGenericsString(apiClass.getTypeParamsMap()));
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

        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private void visitConstructor(APIMethod constructor) {
        // Check if constructor is enabled in job configuration.
        if (!isEnabled(methodSignature(constructor, visitingClass.getFullName()), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        // create new constructor
        JMethod constr = cls.constructor(JMod.PUBLIC);
        // define body of the constructor. Body contains only super(...) call.
        JBlock body = constr.body();
        JInvocation superInv = body.invoke("super");

        // define params of the constructor.
        char paramName = 'a';
        for (String param : constructor.getParameters()) {
            JType type = getClassRef(param);
            constr.param(type, String.valueOf(paramName));
            superInv.arg(JExpr.ref(String.valueOf(paramName)));
            paramName++;
        }

        for (String exception : constructor.getThrown()) {
            constr._throws(getClassRef(exception));
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

        if (apiField.getModifiers().contains(APIModifier.Modifier.FINAL)) { // Final fields
            // original field
            JFieldRef fld;
            if (apiField.getModifiers().contains(APIModifier.Modifier.STATIC)) {
                fld = getClassRef(visitingClass.getFullName()).staticRef(apiField.getName());
            } else {
                fld = JExpr._super().ref(apiField.getName());
            }
            // create new local variable and assing original value to it
            fieldsMethodBlock.decl(getClassRef(apiField.getVarType()), apiField.getName(), fld);
        } else {
            // create new field of same type as original
            String fldName = generateName(configuration.getFieldTestVariableIdentifier(), apiField.getName());
            JVar var = fieldsMethodBlock.decl(getClassRef(apiField.getVarType()), fldName, getPrimitiveValue(apiField.getVarType()));
            fieldsMethodBlock.assign(JExpr.ref(apiField.getName()), var);
        }
        fieldsMethodBlock.directStatement(" ");

    }

    @Override
    public void visit(APIMethod method) {
        // check if method is enabled in configuration.
        if (!isEnabled(methodSignature(method, visitingClass.getFullName()), WhitelistRule.RuleItem.EXTENDER)) {
            return;
        }

        // Extender can't override final methods
        if (method.getModifiers().contains(APIModifier.Modifier.FINAL)) {
            return;
        }

        // Extender can't override static methods
        if (method.getModifiers().contains(APIModifier.Modifier.STATIC)) {
            return;
        }

        JClass extenderReturnType = getClassRef(method.getReturnType());
        String returnTypeParam = getParamArg(method.getReturnType());
        if (returnTypeParam != null) {
            if (!visitingClass.getTypeParamsMap().isEmpty()) {
                extenderReturnType = getClassRef(method.getReturnType());
                /*
                TODO: v nekterych pripadech je mozne pouzit genericky navratovy typ,
                    viz metody write7 a 8 v testovacim souboru. Pouziti gt ale neni
                    povinne, tak jej zatim vynechavam.
                */

            } else if (!method.getTypeParamsMap().isEmpty()) {
                extenderReturnType = getGenericsClassRef(method.getReturnType());
            }
        } else {
            if (visitingClass.getTypeParamsMap().containsKey(method.getReturnType())) {
                extenderReturnType = getClassRef(method.getReturnType());
            } else if (method.getTypeParamsMap().containsKey(method.getReturnType())) {
                String returnTypeName = method.getReturnType();//method.getTypeParamsMap().get(method.getReturnType());
                extenderReturnType = getClassRef(returnTypeName);
            }
        }
        // define new method
        JMethod mthd = cls.method(JMod.PUBLIC, extenderReturnType, method.getName());

        if (visitingClass.getTypeParamsMap().isEmpty() && !method.getTypeParamsMap().isEmpty()) {
            mthd.generify(generateGenericsString(method.getTypeParamsMap()));
        }

        // set body of the method. = return super.method(...);
        mthd.body()._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)));

        // add params to method. New method has same params as overridden method.
        char paramName = 'a';
        for (String param : method.getParameters()) {
            boolean array = false;
            if (param.endsWith("[]")) {
                array = true;
            }
            JClass paramType = getClassRef(param);
            String paramTypeParam = getParamArg(param);
            if (paramTypeParam != null) {
                if (!visitingClass.getTypeParamsMap().isEmpty()) {
                    paramType = getClassRef(param);
                } else {
                    paramType = cm.ref(param);
                }

            } else {
                if (visitingClass.getTypeParamsMap().containsKey(param)) {
                    String paramTypeName = visitingClass.getTypeParamsMap().get(param)[0];
                    paramType = getClassRef(paramTypeName);
                } else if (method.getTypeParamsMap().containsKey(param)) {
                    String paramTypeName = method.getTypeParamsMap().get(param)[0];
                    paramType = getClassRef(paramTypeName);
                }
            }
//            if (param.contains("<")) {
//                String paramTypeArg = param.substring(param.indexOf('<')+1, param.indexOf('>')).trim();
//                if(paramTypeArg.equals(method.getGenerics().trim())) {
//                    paramType = cm.ref(param);
//                } else if(paramTypeArg.equals(visitingClass.getGenerics().trim())) {
//                    paramType = getClassRef(visitingClass.getTypeParamsMap().get(param));
//                }
//            }
            //if (param.contains("<" + method.getReturnType() + ">")) paramType = cm.ref(param);
            //if (param.contains("<" + method.getGenerics() + ">")) paramType = cm.ref(param);
            if (array) {
                paramType.array();
            }
            mthd.param(paramType, String.valueOf(paramName));

            paramName++;
        }

        // add override annotation to the method.
        JAnnotationUse annotation = mthd.annotate(cm.ref(java.lang.Override.class));

        // add list of thrown methods. Must be same as list of original class
        for (String thrown : method.getThrown()) {
            mthd._throws(getClassRef(thrown));
        }

    }

    protected static String getParamArg(String className) {
        if (className.contains("<")) {
            return className.substring(className.indexOf('<') + 1, className.lastIndexOf('>')).trim();
        }
        return null;
    }

}
