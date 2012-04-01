package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeScanner;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.*;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem.Kind;

import java.lang.annotation.ElementType;
import java.util.*;

/**
 * @author Jan Hýbl
 */
public class SourceTreeScanner extends TreeScanner {
    private APIPackage currentPackage;
    private APIClass currentClass;
    private Stack<APIClass> classes = new Stack<APIClass>();
    private Map<String, APIPackage> pkgs = new HashMap<String, APIPackage>();
    private JavacTypes types;
    private API api = new API("");

    SourceTreeScanner(JavacTypes types) {
        this.types = types;
    }

    public API getAPI() {
        //API api = new API("");
        // Add all packages to API. We don't want default package in API, we can't import it!
        //for (APIPackage p : pkgs.values()) if (!p.getName().equals("")) api.addPackage(p);
        return api;
    }

    @Override
    public void visitTopLevel(JCCompilationUnit jccu) {
        String n = jccu.packge.fullname.toString();
        currentPackage = pkgs.get(n);
        if (currentPackage == null) {
            currentPackage = new APIPackage(n);
            pkgs.put(n, currentPackage);
            if (!currentPackage.getName().equals("")) {
                api.addPackage(currentPackage);
            }
        }
        super.visitTopLevel(jccu);
        currentPackage = null;
    }

    @Override
    public void visitClassDef(JCClassDecl jccd) {

        ClassSymbol cs = jccd.sym;
        //if (((cs.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) || ((cs.flags() & Flags.PRIVATE) == 0)) {
        currentClass = new APIClass(jccd);
        classes.push(currentClass);
        super.visitClassDef(jccd);

        for (Attribute.Compound compound : cs.getAnnotationMirrors()) {
            if (compound.type.toString().equals("java.lang.Deprecated")) {
                currentClass.setDepreacated(true);
            }
        }

        classes.pop();
        if (!classes.empty()) {
            currentClass.setNested(true);
            classes.peek().addNestedClass(currentClass);
            currentClass = classes.peek();
        } else {
            currentPackage.addClass(currentClass);
        }
        //}
    }

    @Override
    public void visitAnnotation(JCTree.JCAnnotation jcca) {
        super.visitAnnotation(jcca);
        if (jcca.annotationType.type.toString().equals("java.lang.annotation.Target")) {
            for (JCTree.JCExpression e : jcca.getArguments()) {
                JCTree.JCAssign a = (JCTree.JCAssign) e;
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
                } else if (a.rhs instanceof JCTree.JCIdent) {
                    try {
                        JCTree.JCIdent i = (JCTree.JCIdent) a.rhs;
                        String s = i.type.toString().substring(21) + "." + i.getName();
                        ElementType target = APIClass.parseAnnotationTarget(s);
                        List<ElementType> targetsList = new LinkedList<>();
                        targetsList.add(target);
                        currentClass.setAnnotationTargets(targetsList);
                    } catch (Exception e1) {
                        System.err.println(e1.getMessage());
                    }

                }
            }
        }
    }

    @Override
    public void visitMethodDef(JCMethodDecl jcmd) {
        MethodSymbol ms = jcmd.sym;
        if ((ms.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0 || currentClass.getType().equals(Kind.INTERFACE)) {
            // if default constructor should not be part of api, uncomment this.
            //if ((ms.flags() & Flags.GENERATEDCONSTR) == 0) {

            APIMethod mth = new APIMethod(jcmd, types);
            if (currentClass.getType().equals(Kind.INTERFACE)) {
                mth.setModifiers(APIModifier.getInterfaceMethodModifierList());
            }
            if (mth.getType() == Kind.CONSTRUCTOR) {
                mth.setName(currentClass.getName());
                currentClass.addConstructor(mth);
            } else {
                currentClass.addMethod(mth);
            }

            super.visitMethodDef(jcmd);

            for (Attribute.Compound compound : ms.getAnnotationMirrors()) {
                if (compound.type.toString().equals("java.lang.Deprecated")) {
                    mth.setDepreacated(true);
                }
            }

            //}
        }
    }

    @Override
    public void visitVarDef(JCVariableDecl jcvd) {
        VarSymbol vs = jcvd.sym;
        if ((vs.flags() & (Flags.PUBLIC | Flags.PROTECTED)) != 0) {

            APIField fld = new APIField(jcvd, currentClass.getType().equals(Kind.INTERFACE));

            currentClass.addField(fld);
            super.visitVarDef(jcvd);

            for (Attribute.Compound compound : vs.getAnnotationMirrors()) {
                if (compound.type.toString().equals("java.lang.Deprecated")) {
                    fld.setDepreacated(true);
                }
            }

        }
    }

}
