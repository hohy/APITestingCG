package lib;

import java.util.List;

public abstract class AbstractMethods implements AAbstractMethods {

  @Override
  public <T> T execute2(List<? extends T> l) {
      return null;
  } 
  
  @Override
  public final void finalMethod() {
  } 
  
}

interface AAbstractMethods {

  public <T> T execute2(List<? extends T> l);
  
  public void finalMethod();    

  public abstract int types1(int a);
  public abstract int[] types2(int[] a);
  public abstract String types3(String a);
  public abstract String[] types3(String[] a);
}