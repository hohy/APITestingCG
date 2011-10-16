package cz.cvut.fit.hybljan2.apitestingcg;

import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.TreeScanner;

/**
 *
 * @author Jan Hýbl
 */
public class SourceScanner extends TreeScanner{
    private int stack = 0;
    @Override
    public void visitClassDef(JCClassDecl jccd) {
        stack++;
        System.out.println(getStackSpace() + "Class declaration: " + jccd.name);
        super.visitClassDef(jccd);        
        stack--;
    }

    @Override
    public void visitMethodDef(JCMethodDecl jcmd) {
        stack++;
        System.out.println(getStackSpace() + "Method declaration:" + jcmd.name);
        super.visitMethodDef(jcmd);
        stack--;
    }
    
    private String getStackSpace() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= stack; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
}
