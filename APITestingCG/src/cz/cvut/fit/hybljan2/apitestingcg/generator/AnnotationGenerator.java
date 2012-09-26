package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.lang.annotation.ElementType;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 20.2.12
 * Time: 11:22
 */
public class AnnotationGenerator extends ClassGenerator {

    public AnnotationGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void visit(APIClass apiClass) {
        // annotations tests can be generated only for annotations
        if (!apiClass.getKind().equals(APIItem.Kind.ANNOTATION)) return;

        // check if this annotation is enabled in jobConfiguration.
        if (!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.ANNOTATION)) return;

        // check if class is not deprecated. If it does and in job configuration
        // are deprecated items disabled, this class is skipped.
        if (apiClass.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }


        // check if annotation has specified targets. If it hasn't, targets are all elements.
        if (apiClass.getAnnotationTargets() == null) {
            apiClass.setAnnotationTargets(Arrays.asList(ElementType.values()));
        }

        // only public classes can be tested.
        if (!apiClass.getModifiers().contains(APIModifier.PUBLIC)) {
            return;
        }

        cls = null;

        // generate test class
        generateTestClass(apiClass, false);

        cls = null;

        // check if any annotation param has defined default value. If it has, it has to be tested.
        for (APIMethod annotationParam : apiClass.getMethods()) {
            if (annotationParam.getAnnotationDefaultValue() != null) {
                generateTestClass(apiClass, true);
                break;
            }
        }


    }

    private void initClass(String className) throws JClassAlreadyExistsException {
        if (cls == null) {
            cls = cm._class(currentPackageName + '.' + className);
        }
    }

    /**
     * Generates a new class that tests given annotation.
     * @param testedAnnotation Annotation that is tested by the generated code.
     * @param setDefaultValues Should be default value of the annotation parameters be set to some value or the default
     *                         value should be used.
     *
     * TODO: all names hardcoded in the source should be configurable through configuration.
     */
    private void generateTestClass(APIClass testedAnnotation, boolean setDefaultValues) {
        // declare new class
        try {
            // TODO: pridat konfiguraci jmena do nastaveni.
            String className = generateName("%sClass", testedAnnotation.getName());
            if(setDefaultValues) className += "DV";

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.TYPE)) {
                initClass(className);
                annotate(cls, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.FIELD)) {
                initClass(className);
                JFieldVar fld = cls.field(JMod.NONE, cm.INT, "annotatedField");
                annotate(fld, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.LOCAL_VARIABLE)) {
                initClass(className);
                JMethod method = cls.method(JMod.NONE, cm.VOID, "localVarMethod");
                JVar localVar = method.body().decl(cm.INT, "localVariable");
                annotate(localVar, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.METHOD)) {
                initClass(className);
                JMethod method = cls.method(JMod.NONE, cm.VOID, "annotatedMethod");
                annotate(method, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.PARAMETER)) {
                initClass(className);
                JMethod method = cls.method(JMod.NONE, cm.VOID, "parameterMethod");
                JVar param = method.param(cm.INT, "param");
                annotate(param, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.CONSTRUCTOR)) {
                initClass(className);
                JMethod method = cls.constructor(JMod.NONE);
                annotate(method, testedAnnotation, setDefaultValues);
            }

            if (testedAnnotation.getAnnotationTargets().contains(ElementType.ANNOTATION_TYPE)) {
                String name = testedAnnotation.getName() + "Annotation";
                if(setDefaultValues) name += "DV";
                JDefinedClass annotation = cm._class(currentPackageName + '.' + name, ClassType.ANNOTATION_TYPE_DECL);
                annotate(annotation, testedAnnotation, setDefaultValues);
            }


            if (testedAnnotation.getAnnotationTargets().contains(ElementType.PACKAGE)) {
                String packageName = currentPackageName + "." + testedAnnotation.getName().toLowerCase();
                if(setDefaultValues) packageName += "DV";
                JPackage a = cm._package(packageName);
                annotate(a, testedAnnotation, setDefaultValues);
            }

        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private JAnnotationUse annotate(JAnnotatable item, APIClass annotation, boolean setDefaults) {
        //JAnnotationUse result = item.annotate(getClassRef(annotation.getFullName()));
        JAnnotationUse result = item.annotate(getTypeRef(annotation.getFullName(),null));
        for (APIMethod method : annotation.getMethods()) {
            if (setDefaults || method.getAnnotationDefaultValue() == null) {
                try {
                    result.param(method.getName(), getAnotationParamValue(method.getReturnType().getName()));
                } catch (Exception e) {
                    System.err.println("Cant set param value." + e.getMessage());
                }
            }
        }
        return result;
    }

    public JExpression getAnotationParamValue(String name) throws Exception {
        name = name.trim();
        if (name.equals("byte")) return JExpr.lit(0);
        if (name.equals("short")) return JExpr.lit(0);
        if (name.equals("int")) return JExpr.lit(0);
        if (name.equals("long")) return JExpr.lit(0);
        if (name.equals("float")) return JExpr.lit(0.0);
        if (name.equals("double")) return JExpr.lit(0.0);
        if (name.equals("boolean")) return JExpr.lit(false);
        if (name.equals("char")) return JExpr.lit('a');
        if (name.equals("java.lang.String")) return JExpr.lit("A");
        if (name.equals("java.lang.Class")) return JExpr.dotclass(getClassRef("java.io.File"));
        if (name.endsWith("]")) {
            String arrayType = name.substring(0, name.indexOf("["));
            return JExpr.newArray(getClassRef(arrayType)).add(getAnotationParamValue(arrayType));
        }

        // generic class
        int idx = name.indexOf('<');
        if (name.startsWith("java.lang.Class") && idx >= 0) {
            String typeParam = name.substring(idx + 1, name.lastIndexOf('>'));
            return getAnotationParamValue(typeParam);
        }

        idx = name.indexOf("extends");
        if (idx >= 0) {
            return getAnotationParamValue(name.substring(idx + 8));
        }

        APIClass paramType = findClass(name);
        if (paramType.getKind().equals(APIItem.Kind.ENUM)) {
            // visit all fields
            for (APIField field : paramType.getFields()) {
                if (field.getVarType().equals(paramType.getFullName())) { // test if field is enum field or just variable
                    return getClassRef(paramType.getFullName()).staticRef(field.getName());
                }
            }
        } else {
            return JExpr.dotclass(getClassRef(paramType.getFullName()));
        }
        throw new Exception("Unknown annotation parameter type: " + name);
    }

    @Override
    public void visit(APIField apiField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(APIMethod method) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
