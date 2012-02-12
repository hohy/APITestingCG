package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

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
            visitingClassName = apiClass.getFullName();
            
            // declare new class
            cls = cm._class(currentPackageName + '.' + generateName(configuration.getInstantiatorClassIdentifier(), apiClass.getName()));

            // visit all constructors
            for(APIMethod constructor : apiClass.getConstructors()) {
                visitConstructor(constructor);
            }

            // visit all methods
            for(APIMethod method : apiClass.getMethods()) {
                method.accept(this);
            }

            if(!apiClass.getFields().isEmpty()) {
                JMethod fieldsMethod = cls.method(JMod.PUBLIC,cm.VOID,"fields");   // TODO: add this method name to configuration
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
     * For every constructor in 
     * @param constructor
     */
    private void visitConstructor(APIMethod constructor) {
        // Check if constructor is enabled in job configuration.
        if(!isEnabled(methodSignature(constructor,visitingClassName), WhitelistRule.RuleItem.INSTANTIATOR)) return;

        // create basic create new instance method
        addCreateInstanceMethod(constructor.getName(), generateName(configuration.getCreateInstanceIdentifier(), constructor.getName()), constructor.getParameters(), false);

        // if it is possible, create null version of previous constructor
    }

    @Override
    public void visit(APIField apiField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(APIMethod method) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    
    
    private void addCreateInstanceMethod(String instanceClassName, String methodName, List<String> args, boolean nullParams) {
        JMethod result = cls.method(JMod.PUBLIC, cm.ref(instanceClassName),methodName);
        JInvocation newInstance = JExpr._new(cm.ref(instanceClassName));
        char argName = 'a';
        for(String arg : args) {
            result.param(cm.ref(arg), String.valueOf(argName));
            if(nullParams) newInstance.arg(JExpr._null());
            else newInstance.arg(JExpr.ref(String.valueOf(argName)));
            argName++;
        }
        result.body()._return(newInstance);
    }
}
