package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jan Hybl
 */
public abstract class Generator {

    protected GeneratorConfiguration configuration;

    public abstract void generate(API api, GeneratorDirector director);
    
    protected List<String[]> getMethodParamList(APIMethod method) {
        // String builder for list of params for instantiator constructor
        List<String[]> result = new LinkedList<String[]>();
        char paramName = 'a';

        for(String className : method.getParameters()) {
            String[] p = new String[2];
            p[0] = className;
            p[1] = Character.toString(paramName);
            paramName++;
            result.add(p);
        }
        return result;
    }

    protected String getMethodParamNameList(List<String[]> params) {
        // String builder for list of params for tested constructor
        StringBuilder paramListSb = new StringBuilder();

        for(String[] param : params) {
            paramListSb.append(param[1]).append(',');
        }

        // remove last ',' if there is any
        if(params.size() > 0) {
            paramListSb.deleteCharAt(paramListSb.length()-1);
        }        
        return paramListSb.toString();
    }

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }
}
