
package test.lib;

import java.util.List;
import lib.Arrays;

public class ArraysInstantiator {


    public Arrays createArrays() {
        return new Arrays();
    }

    public void method2(Arrays instance, List<List> [][][] param) {
        instance.method2(param);
    }

    public byte[][][] method(Arrays instance, Object[][][] paramArray) {
        return instance.method(paramArray);
    }

    public void fields(Arrays instance) {
        int[][] array2DValue = null;
        instance.array2D = array2DValue;
        int[][][][][] arrayXValue = null;
        instance.arrayX = arrayXValue;
        int[] simpleArrayValue = null;
        instance.simpleArray = simpleArrayValue;
    }

}
