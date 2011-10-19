/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.hybljan2.apitestingcg;

/**
 *
 * @author hohy
 */
public class VariableNameManager {
    
    int variableCounter = 0;
    
    private VariableNameManager() {
    }
    
    public static VariableNameManager getInstance() {
        return VariableNameManagerHolder.INSTANCE;
    }
    
    private static class VariableNameManagerHolder {

        private static final VariableNameManager INSTANCE = new VariableNameManager();
    }

    public String getVariableName() {
        variableCounter++;
        return "var" + variableCounter;
    }
        
}
