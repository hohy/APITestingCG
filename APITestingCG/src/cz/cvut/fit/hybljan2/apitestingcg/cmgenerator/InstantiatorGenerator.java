package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 11.2.12
 * Time: 17:00
 */
public class InstantiatorGenerator extends ClassGenerator {

    public InstantiatorGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void visit(APIClass apiClass) {
        // check if extender for this class is enabled in jobConfiguration.
        if(!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.INSTANTIATOR)) return;

        try {        
            visitingClass = apiClass;
            
            // declare new class
            cls = cm._class(currentPackageName + '.' + generateName(configuration.getInstantiatorClassIdentifier(), apiClass.getName()));

            // visit all constructors
            for(APIMethod constructor : apiClass.getConstructors()) {
                visitConstructor(constructor);
            }

            // genetate test of extending - cant be performed if tested class has no constructors
            if(apiClass.getExtending() != null && !apiClass.getConstructors().isEmpty()) {
                addCreateInstanceMethod(apiClass.getExtending(),generateName(configuration.getCreateSuperInstanceIdentifier(), apiClass.getExtending()),apiClass.getConstructors().first().getParameters(), false);
            }

            // genetate test of implementing - cant be performed if tested class has no constructors
            if(!apiClass.getImplementing().isEmpty() && !apiClass.getConstructors().isEmpty()) {
                for(String implementing : apiClass.getImplementing()) {
                    addCreateInstanceMethod(implementing,generateName(configuration.getCreateSuperInstanceIdentifier(), implementing),apiClass.getConstructors().first().getParameters(), false);
                }
            }
            
            // visit all methods
            for(APIMethod method : apiClass.getMethods()) {
                method.accept(this);
            }

            if(!apiClass.getFields().isEmpty()) {
                JMethod fieldsMethod = cls.method(JMod.PUBLIC,cm.VOID,configuration.getFieldTestIdentifier());
                fieldsMethodBlock = fieldsMethod.body();
            }
            // visit all fields
            for(APIField field : apiClass.getFields()) {
                field.accept(this);
            }        
            
        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Generates tests of given constructors.
     * Two createInstance methods are generated. First simply creates new instance using tested constructor.
     * Second method do same thing but with null parameters. Null constructor can't be generated in every
     * case. Only in cases when constructor is unique.
     * @param constructor
     */
    private void visitConstructor(APIMethod constructor) {
        // Check if constructor is enabled in job configuration.
        if(!isEnabled(methodSignature(constructor,visitingClass.getFullName()), WhitelistRule.RuleItem.INSTANTIATOR)) return;

        // create basic create new instance method
        addCreateInstanceMethod(visitingClass.getFullName(), generateName(configuration.getCreateInstanceIdentifier(), constructor.getName()), constructor.getParameters(), false);

        // if it is possible, create null version of previous constructor
        // nonparam constructor can't be tested with null values.
        if(constructor.getParameters().isEmpty()) return;

        // Check if there is no other same constructor
        boolean unique = true;           
        for(APIMethod c : visitingClass.getConstructors()) {
            // if the tested constructor is equal to c constructor, it's not unique.
            unique = !equalsNullParams(c.getParameters(), constructor.getParameters());
            if(!unique) return; // if it's not unique constructor, skip generating of null constructor.
        }

        // generate null constructor (same as previous, but params are NULLs).
        addCreateInstanceMethod(constructor.getName(), generateName(configuration.getCreateInstanceIdentifier(), constructor.getName()), constructor.getParameters(), true);
    }

    @Override
    public void visit(APIField apiField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(APIMethod method) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean equalsNullParams(List<String> paramsA, List<String> paramsB) {
        if(paramsA.size() != paramsB.size()) return false;
        Iterator<String> itA = paramsA.iterator();
        Iterator<String> itB = paramsB.iterator();
        while(itA.hasNext()) {
            String paramA = itA.next();
            String paramB = itB.next();
            if(!getDefaultPrimitiveValue(paramA).equals(getDefaultPrimitiveValue(paramB))) return false;
        }
        return true;
    }
    
    private void addCreateInstanceMethod(String instanceClassName, String methodName, List<String> args, boolean nullParams) {
        JClass returnCls = cm.directClass(instanceClassName);
        JMethod result = cls.method(JMod.PUBLIC, returnCls, methodName);
        JInvocation newInstance = JExpr._new(returnCls);
        char argName = 'a';
        for(String arg : args) {
            result.param(cm.ref(arg), String.valueOf(argName));
            if(nullParams) newInstance.arg(getDefaultPrimitiveValue(arg));
            else newInstance.arg(JExpr.ref(String.valueOf(argName)));
            argName++;
        }
        result.body()._return(newInstance);
    }
}
