
package test.lib;

import java.util.List;
import lib.Arrays;

public class ArraysExtender extends Arrays {


    public ArraysExtender() {
        super();
    }

    @Override
    public void method2(List<List> [][][] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[][][] method(Object[][][] paramArray) {
        throw new UnsupportedOperationException();
    }

    public void fields() {
        int[][] array2DValue = null;
        array2D = array2DValue;
        int[][][][][] arrayXValue = null;
        arrayX = arrayXValue;
        int[] simpleArrayValue = null;
        simpleArray = simpleArrayValue;
    }

}
