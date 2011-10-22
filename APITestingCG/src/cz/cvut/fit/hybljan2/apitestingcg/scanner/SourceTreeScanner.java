package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeScanner;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Jan Hýbl
 */
public class SourceTreeScanner extends TreeScanner{
    private int stack = 0;
    private API api;
    private APIPackage currentPackage;
    private APIClass currentClass;
    private Stack<APIClass> classes = new Stack<APIClass>();    
    private Map<String, APIPackage> pkgs = new HashMap<String, APIPackage>();    
    
    public API getAPI() {
        api = new API("");
        for(APIPackage p : pkgs.values()) api.addPackage(p);
        return api;
    }
    
    @Override
    public void visitTopLevel(JCCompilationUnit jccu) {
        String n = jccu.packge.fullname.toString();
        currentPackage = pkgs.get(n);
        if (currentPackage == null) {
            currentPackage = new APIPackage(n);
            pkgs.put(n, currentPackage);
        }
        super.visitTopLevel(jccu);
        currentPackage = null;        
    }      
    
    @Override
    public void visitClassDef(JCClassDecl jccd) {
        ClassSymbol cs = jccd.sym;
        if ((cs.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {
            classes.push(currentClass);
            currentClass = new APIClass(jccd);
            super.visitClassDef(jccd);
            currentPackage.addClass(currentClass);
            currentClass = classes.pop();
        }
    }

    @Override
    public void visitMethodDef(JCMethodDecl jcmd) {
        MethodSymbol ms = jcmd.sym;
        if ((ms.flags() & Flags.DEPRECATED) != 0) {
            System.out.println("deprecated: " + jcmd);
        }
        if ((ms.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {
            if ((ms.flags() & Flags.GENERATEDCONSTR) == 0) {
                currentClass.addMethod(new APIMethod(jcmd));
                super.visitMethodDef(jcmd);
            }
        }
    }
    
    private String getStackSpace() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= stack; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    public void visitVarDef(JCVariableDecl jcvd) {
        VarSymbol vs = jcvd.sym;
        if ((vs.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {
            currentClass.addField(new APIField(jcvd));
            super.visitVarDef(jcvd);
        }
    }
    }
