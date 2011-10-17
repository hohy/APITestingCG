package testlib1app;

import testlib1.ClassA;
import testlib1.ClassB;

/**
 *
 * @author Jan Hybl
 */
public class Testlib1App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Test of ClassA from TestLib1
         */
        testlib1.ClassA clsA = new ClassA();
        int a = clsA.apiMethodAdd(1, 2);        
        
        // Because apiMethodAdd is defined only once in ClassA, we can call it with 
        // null params and it should works.
        clsA.apiMethodAdd(null, null);
        System.out.println(a);
        
        // Class can be used by exteding with other class. We have to test it.
        ClassAExtender clsAex = new ClassAExtender();
        clsAex.printApiMethodAdd();
        
        
        /**
         * Test of ClassB from TestLib1
         */
        ClassB clsB = new ClassB();
        Object object = clsB.someMethod(Double.NaN, Double.NaN);
        object = clsB.someMethod(Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        // someMethod can be called with Double or with Integer params. So it can't be called with null
        //object = clsB.someMethod(null, null);
    }

}





/**
 * 
 * @author Jan Hýbl
 */
class ClassAExtender extends ClassA {
    public void printApiMethodAdd() {
        System.out.println(apiMethodAdd(1, 2));
        System.out.println(apiMethodAdd(null, null));
    }
        
    public void testProtectedMethods() {
        super.apiMethodSub(1, 2);
    }
    
    @Override
    public int apiMethodAdd(Integer a, Integer b) {
        return super.apiMethodAdd(a, b);
    }

    @Override
    protected int apiMethodSub(int a, int b) {
        return super.apiMethodSub(a, b);
    }
    
}


class ClassBExender extends ClassB {

    @Override
    public Object someMethod(Integer param1, Integer param2) {
        return super.someMethod(param1, param2);
    }

    @Override
    public Object someMethod(Double param1, Double param2) {
        return super.someMethod(param1, param2);
    }
    
}
