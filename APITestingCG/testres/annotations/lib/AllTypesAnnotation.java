package lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.io.Serializable;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AllTypesAnnotation {    
    boolean bo();
    byte by();
    char ch();
    short sh();
    int i();
    long l();
    float f();
    double d();
    String st();
    Class cl();
    Class<List> clf();
    Class<? extends Serializable> cls();
    Class<? super List> cll();
    Class<?> clq();
    TestEnum en();
   
    boolean[] boa();
    byte[] bya();
    char[] cha();
    short[] sha();
    int[] ia();
    long[] la();
    float[] fa();
    double[] da();
    String[] sta();
    Class[] cla();
    Class<List>[] clfa();
    TestEnum[] ena();    
}