package cz.cvut.fit.hybljan2.apitestingcg.generator;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIModifier.Modifier;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;

/**
 *
 * @author Jan Hybl
 */
public class ExtenderGenerator extends Generator {

    @Override
    public void generate(API api) {
        for(APIPackage pkg : api.getPackages()) {
            for(APIClass cls : pkg.getClasses()) {
                // filter out final classes
                if(!cls.getModifiers().contains(Modifier.FINAL)) {
                    ClassGenerator cgen = new ClassGenerator();
                    cgen.addImport(cls.getFullName());                    
                    cgen.setPackageName(pkg.getName());
                    
                    String subname = null;
                    if(cls.getType() == Kind.INTERFACE) {
                        subname = "Implementator";
                        cgen.addImplemening(cls.getName());
                    } else {
                        subname = "Extender";
                        cgen.setExtending(cls.getName());
                    }
                    
                    cgen.setName(cls.getName() + subname);
                    
                    // constructors tests
                    for(APIMethod constructor : cls.getConstructors()) {
                        MethodGenerator cnstr = new MethodGenerator();
                        cnstr.setModifiers("public");
                        cnstr.setName(cgen.getName());
                        cnstr.setParams(getMethodParamList(constructor));
                        
                        StringBuilder sb = new StringBuilder();
                                                
                        sb.append("\t\tsuper(").append(getMethodParamNameList(cnstr.getParams())).append(");");
                        
                        cnstr.setBody(sb.toString());
                        cgen.addConstructor(cnstr);
                    }
                    
                    // method overriding tests
                    for(APIMethod method : cls.getMethods()) {
                        MethodGenerator mgen = new MethodGenerator();
                        mgen.setModifiers("public");
                        mgen.setName(method.getName());
                        mgen.setReturnType(method.getReturnType());
                        mgen.setModifiers(method.getModifiers());
                        mgen.setThrown(method.getThrown());
                        mgen.addAnotation("Override");
                        mgen.setParams(getMethodParamList(method));
                        mgen.setBody("\t\tthrow new UnsupportedOperationException();");
                        cgen.addMethod(mgen);
                    }
               
                    cgen.generateClassFile();
                }
            }
        }
    }
    
}
