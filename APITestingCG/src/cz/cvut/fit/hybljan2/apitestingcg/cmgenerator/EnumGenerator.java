package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 12.2.12
 * Time: 22:22
 */
public class EnumGenerator extends InstantiatorGenerator {

    public EnumGenerator(GeneratorConfiguration configuration) {
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
                if(field.getVarType().equals(apiClass.getName())) { // test if field is enum field or just variable
                    visitEnumField(field);
                } else {  // it's not a enum field but constant or variable. Test it in same way as in Instantiator or Extender.
                    field.accept(this);
                }
            }

        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void visitEnumField(APIField field) {
        JClass returnCls = getClassRef(visitingClass.getFullName());
        JMethod result = cls.method(JMod.PUBLIC, returnCls, generateName(configuration.getCreateInstanceIdentifier(), field.getName()));

        JClass instance = getClassRef(visitingClass.getFullName());
        result.body()._return(instance.staticRef(field.getName()));
    }


}
