package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 20.2.12
 * Time: 11:22
 */
public class AnnotationGenerator extends ClassGenerator {

    JDefinedClass noDefaultClass;

    public AnnotationGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void visit(APIClass apiClass) {
        // annotations tests can be generated only for annotations
        if(!apiClass.getType().equals(APIItem.Kind.ANNOTATION)) return;

        // check if this annotation is enabled in jobConfiguration.
        if(!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.ANNOTATION)) return;

        // check if annotation has specified targets. If it hasn't, targets are all elements.
        if(apiClass.getAnnotationTargets() == null) {
            apiClass.setAnnotationTargets(Arrays.asList(APIClass.AnnotationTargets.values()));
        }

        // generate test class
        generateTestClass(apiClass, false);
        
        // check if any annotation param has defined default value. If it has, it has to be tested.
        for(APIMethod annotationParam : apiClass.getMethods()) {
            if(annotationParam.getAnnotationDefaultValue() != null) {
                generateTestClass(apiClass, true);
                break;
            }
        }
        


    }

    private void generateTestClass(APIClass apiClass, boolean setDefaultValues) {
        // declare new class
        try {
            String className = setDefaultValues ? generateName(configuration.getAnnotationClassIdentifier(), apiClass.getName())+"DV" : generateName(configuration.getAnnotationClassIdentifier(), apiClass.getName());
            cls = cm._class(currentPackageName + '.' + className);
            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.TYPE)) {
                annotate(cls, apiClass, setDefaultValues);
            }

            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.FIELD)) {
                JFieldVar fld = cls.field(JMod.NONE, cm.INT, "annotatedField");
                annotate(fld, apiClass, setDefaultValues);
            }

            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.LOCAL_VARIABLE)) {
                JMethod method = cls.method(JMod.NONE,cm.VOID,"localVarMethod");
                JVar localVar = method.body().decl(cm.INT, "localVariable");
                annotate(localVar, apiClass, setDefaultValues);
            }
            
            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.METHOD)) {
                JMethod method = cls.method(JMod.NONE, cm.VOID, "annotatedMethod");
                annotate(method, apiClass, setDefaultValues);
            }

            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.PARAMETER)) {
                JMethod method = cls.method(JMod.NONE, cm.VOID, "parameterMethod");
                JVar param = method.param(cm.INT, "param");
                annotate(param, apiClass, setDefaultValues);
            }

            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.CONSTRUCTOR)) {
                JMethod method = cls.constructor(JMod.NONE);
                annotate(method, apiClass, setDefaultValues);
            }

            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.ANNOTATION_TYPE)) {
                String name = setDefaultValues ? "AnnotationTypeDV" : "AnnotationType";
                JDefinedClass annotation = cm._class(name, ClassType.ANNOTATION_TYPE_DECL);
                annotate(annotation, apiClass, setDefaultValues);
            }
            
            if(apiClass.getAnnotationTargets().contains(APIClass.AnnotationTargets.PACKAGE)) {
                String packageName = setDefaultValues ? currentPackageName + ".annotatedPackageDV" : currentPackageName + ".annotatedPackage";
                JPackage a = cm._package(packageName);
                annotate(a, apiClass, setDefaultValues);
            }

        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private JAnnotationUse annotate(JAnnotatable item, APIClass annotation, boolean setDefaults) {
        JAnnotationUse result = item.annotate(getClassRef(annotation.getFullName()));
        for(APIMethod method : annotation.getMethods()) {
            if(setDefaults || method.getAnnotationDefaultValue() == null) {
                result.param(method.getName(), getDefaultAnotationParamValue(method.getReturnType()));
            }
        }
        return result;
    }

    public JExpression getDefaultAnotationParamValue(String name) {
        name = name.trim();
        if(name.equals("byte")) return JExpr.lit(0);
        if(name.equals("short")) return JExpr.lit(0);
        if(name.equals("int")) return JExpr.lit(0);
        if(name.equals("long")) return JExpr.lit(0);
        if(name.equals("float")) return JExpr.lit(0.0);
        if(name.equals("double")) return JExpr.lit(0.0);
        if(name.equals("boolean")) return JExpr.lit(false);
        if(name.equals("char")) return JExpr.lit('a');
        if(name.equals("java.lang.String")) return JExpr.lit("A");
        if(name.equals("java.lang.Class")) return JExpr.dotclass(getClassRef("java.io.File"));
        if(name.endsWith("]")) {
            String arrayType = name.substring(0, name.indexOf("["));
            return JExpr.newArray(getClassRef(arrayType)).add(getDefaultAnotationParamValue(arrayType));
        }
        return JExpr.lit(3);
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
