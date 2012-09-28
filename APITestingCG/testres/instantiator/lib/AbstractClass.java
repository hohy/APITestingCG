package lib;

import java.util.List;

public abstract class AbstractClass {

  public AbstractClass() {}
  public AbstractClass(int x) {}

  public int fldInt;
  public List<String> fldList;
  
  public abstract void abstractMethod();
  public void implementedMethod() {}
    
}