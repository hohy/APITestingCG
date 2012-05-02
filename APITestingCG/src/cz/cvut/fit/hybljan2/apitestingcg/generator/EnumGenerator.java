package cz.cvut.fit.hybljan2.apitestingcg.generator;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
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
        // enum test should be generated only for Enums
        if (!apiClass.getType().equals(APIItem.Kind.ENUM)) return;

        // check if extender for this class is enabled in jobConfiguration.
        if (!isEnabled(apiClass.getFullName(), WhitelistRule.RuleItem.INSTANTIATOR)) return;

        // only public classes can be tested.
        if (!apiClass.getModifiers().contains(APIModifier.PUBLIC)) {
            return;
        }

        // check if class is not deprecated. If it does and in job configuration
        // are deprecated items disabled, this class is skipped.
        if (apiClass.isDepreacated() && jobConfiguration.isSkipDeprecated()) {
            return;
        }

        try {
            visitingClass = apiClass;

            // declare new class
            cls = cm._class(currentPackageName + '.' + generateName(configuration.getInstantiatorClassIdentifier(), apiClass.getName()));

            // visit all methods
            for (APIMethod method : apiClass.getMethods()) {
                method.accept(this);
            }

            if (!apiClass.getFields().isEmpty()) {
                JMethod fieldsMethod = cls.method(JMod.PUBLIC, cm.VOID, configuration.getFieldTestIdentifier());
                fieldsMethodBlock = fieldsMethod.body();
            }
            // visit all fields
            for (APIField field : apiClass.getFields()) {
                if (field.getVarType().equals(apiClass.getName())) { // test if field is enum field or just variable
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
