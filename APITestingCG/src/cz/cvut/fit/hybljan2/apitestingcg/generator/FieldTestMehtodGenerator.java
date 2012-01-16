package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 9.1.12
 * Time: 21:58
 */
public class FieldTestMehtodGenerator extends MethodGenerator {
    /**
     * TODO: write docs
     * @param cls
     * @param field
     * @param varName    name of object that is tested in method
     */
    FieldTestMehtodGenerator(APIClass cls, APIField field, String varName, GeneratorConfiguration cfg) {
        setModifiers("public");
        setName(generateName(cfg.getFieldTestIdentifier(), field.getName()));
        setReturnType("void");
        // if method is not static, instance is param.
        if(!field.getModifiers().contains(APIModifier.Modifier.STATIC)) {
            List<String[]> params = new LinkedList<String[]>();
            params.add(new String[] {cls.getName(), cfg.getInstanceIdentifier()});
            setParams(params);
        }
        StringBuilder sb = new StringBuilder();
        // if field is final, print it, if not, write something into it.
        if(field.getModifiers().contains(APIModifier.Modifier.FINAL)) {
            sb.append("\t\tSystem.out.println(").append(varName).append(");");
        } else {
            sb.append("\t\t").append(varName).append(" = ").append(getDefaultPrimitiveValue(field.getVarType())).append(';');
        }
        setBody(sb.toString());
    }

}
