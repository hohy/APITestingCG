package cz.cvut.fit.hybljan2.apitestingcg.cmgenerator;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JDefinedClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 10.2.12
 * Time: 14:11
 */
public abstract class ClassGenerator extends Generator{

    protected JDefinedClass cls;
    protected APIClass visitingClass;
    protected JBlock fieldsMethodBlock;

    public ClassGenerator(GeneratorConfiguration configuration) {
        super(configuration);
    }
}
