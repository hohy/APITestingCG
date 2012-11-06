package lib;

import java.io.Serializable;

public class TypeParams {

  public <T extends Number & Runnable, S extends Serializable> T
  getValue1(T t, S s) {
    return t;
  }

  public <S extends Number & Runnable, T extends Serializable> T
  getValue2(T t, S s) {
    return t;
  }
  
  public <S extends Serializable, T extends Number & Runnable> T
  getValue3(S t, T s) {
    return s;
  }

  public <S extends Serializable, T extends Number & Runnable> T
  getValue4(S t, T s) {
    return s;
  }  
  
}
 