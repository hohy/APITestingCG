package testlib1;

/**
 *
 * @author Jan Hýbl
 */
public class ClassA {
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
