package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeScanner;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;

import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @author Jan Hýbl
 */
public class SourceTreeScanner extends TreeScanner {
    private Map<String, String> currentClassImports = new HashMap<String, String>();
    private APIPackage currentPackage;
    private APIClass currentClass;
    private Stack<APIClass> classes = new Stack<APIClass>();
    private Map<String, APIPackage> pkgs = new HashMap<String, APIPackage>();
    private JavacTypes types;

    SourceTreeScanner(JavacTypes types) {
        this.types = types;
    }

    public API getAPI() {
        API api = new API("");
        // Add all packages to API. We don't want default package in API, we can't import it!
        for (APIPackage p : pkgs.values()) if (!p.getName().equals("")) api.addPackage(p);
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
            currentClassImports = new HashMap<String, String>();
        }
    }

    @Override
    public void visitAnnotation(JCTree.JCAnnotation jcca) {
        super.visitAnnotation(jcca);    //To change body of overridden methods use File | Settings | File Templates.
        if (jcca.annotationType.type.toString().equals("java.lang.annotation.Target")) {
            for (JCTree.JCExpression e : jcca.getArguments()) {
                JCTree.JCAssign a = (JCTree.JCAssign) e;
                System.out.println(a);
                if (a.rhs instanceof JCTree.JCFieldAccess) {
                    currentClass.setAnnotationTargets(new LinkedList<ElementType>());
                    try {
                        ElementType target = APIClass.parseAnnotationTarget(a.rhs.toString());
                        currentClass.getAnnotationTargets().add(target);
                    } catch (Exception e1) {
                        System.err.println(e1.getMessage());
                    }
                } else if (a.rhs instanceof JCTree.JCNewArray) {
                    currentClass.setAnnotationTargets(new LinkedList<ElementType>());
                    JCTree.JCNewArray array = (JCTree.JCNewArray) a.rhs;
                    for (JCTree.JCExpression fld : array.elems) {
                        try {
                            currentClass.getAnnotationTargets().add(APIClass.parseAnnotationTarget(fld.toString()));
                        } catch (Exception e1) {
                            System.err.println(e1.getMessage());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void visitMethodDef(JCMethodDecl jcmd) {
        MethodSymbol ms = jcmd.sym;
        if ((ms.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {
            // if default constructor should not be part of api, uncomment this.
            //if ((ms.flags() & Flags.GENERATEDCONSTR) == 0) {

            APIMethod mth = new APIMethod(jcmd, currentClassImports, types);
            if (mth.getType() == Kind.CONSTRUCTOR) {
                mth.setName(currentClass.getName());
                currentClass.addConstructor(mth);
            } else currentClass.addMethod(mth);
            super.visitMethodDef(jcmd);
            //}
        }
    }

    @Override
    public void visitVarDef(JCVariableDecl jcvd) {
        VarSymbol vs = jcvd.sym;
        if ((vs.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {
            currentClass.addField(new APIField(jcvd, currentClassImports));
            super.visitVarDef(jcvd);
        }
    }

    @Override
    public void visitImport(JCImport jci) {
        String importClassName = jci.getQualifiedIdentifier().toString();
        String simpleClassName = importClassName.substring(importClassName.lastIndexOf('.') + 1);
        currentClassImports.put(simpleClassName, importClassName);
        super.visitImport(jci);
    }


}
