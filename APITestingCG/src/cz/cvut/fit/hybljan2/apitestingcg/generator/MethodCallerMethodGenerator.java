package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.GeneratorConfiguration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 16.1.12
 * Time: 21:12
 */
public class MethodCallerMethodGenerator extends MethodGenerator {

    public MethodCallerMethodGenerator(APIMethod method, APIClass cls, String nullParamString, GeneratorConfiguration cfg) {
        configuration = cfg;
        List<String[]> params = new LinkedList<String[]>();

        // if method isn't static, add instance param to param list
        if(!method.getModifiers().contains(APIModifier.Modifier.STATIC)) {
            params.add(new String[] {cls.getName(), configuration.getInstanceIdentifier()});
        }

        // add params of tested method
        params.addAll(getMethodParamList(method));

        setParams(params);
        setModifiers("public");
        // generated method return result of test method, so it has to have same return type
        setReturnType(method.getReturnType());

        setName(generateName(configuration.getMethodNullCallIdentifier(), method.getName()));
        setBody(generateCallerBody(method, cls, nullParamString));
    }

    public MethodCallerMethodGenerator(APIMethod method, APIClass cls, GeneratorConfiguration cfg) {
        configuration = cfg;
        List<String[]> params = new LinkedList<String[]>();

        // if method isn't static, add instance param to param list
        if(!method.getModifiers().contains(APIModifier.Modifier.STATIC)) {
            params.add(new String[] {cls.getName(), configuration.getInstanceIdentifier()});
        }

        // add params of tested method
        params.addAll(getMethodParamList(method));

        setParams(params);
        setModifiers("public");
        // generated method return result of test method, so it has to have same return type
        setReturnType(method.getReturnType());
        setName(generateName(configuration.getMethodCallIdentifier(), method.getName()));
        setBody(generateCallerBody(method, cls));
    }

    private String generateCallerBody(APIMethod method, APIClass cls, String paramsString) {

        // String builder for creating command line with following structure: ("return ")? instance.method(params);
        StringBuilder cmdSB = new StringBuilder("\t\t");

        // if method return something, command starts with return.
        if(!method.getReturnType().equals("void")) cmdSB.append("return ");
        // instance of tested class that will be used to call method.
        cmdSB.append(getInstance(method.getModifiers(), cls));
        cmdSB.append('.');
        // name of tested method
        cmdSB.append(method.getName());
        cmdSB.append('(');
        cmdSB.append(paramsString);
        cmdSB.append(");");

        // if method throws any exception, surround command with try-catch command.
        if(!method.getThrown().isEmpty()) {
            cmdSB.insert(0, "\t\ttry {\n\t");
            for(String exception : method.getThrown()) {
                cmdSB.append("\n\t\t} catch (").append(exception).append(" ex) {");
            }
            cmdSB.append("}\n");
            // Because return statement is surrounded by try-catch block, there have to be another return statement
            // returning default value at the end of generated method. (But only if method isn't void.)
            if(!method.getReturnType().equals("void")) cmdSB.append("\t\t").append("return ").append(getDefaultPrimitiveValue(method.getReturnType())).append(';');
        }
        return cmdSB.toString();
    }

    private String generateCallerBody(APIMethod method, APIClass cls) {
        return generateCallerBody(method, cls, getMethodParamNameList(getMethodParamList(method)));
    }


}
