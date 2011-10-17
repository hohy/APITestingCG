package testlib1;

/**
 *
 * @author Jan Hýbl
 */
public class ClassA {
    public static final int CONST = 999;
    public static final String WORD = "Hello";
    
    public int apiMethodAdd(Integer a, Integer b) {
        return a + b;
    }
    
    protected int apiMethodSub(int a, int b) {
        return a - b;
    }
    
    public void exceptionMethod() throws RuntimeException {
        throw new RuntimeException();
    }
}
