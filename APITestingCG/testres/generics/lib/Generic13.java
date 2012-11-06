package lib;

import java.util.List;
import java.lang.Integer;

public class Generic13 {

  public void noBound(List<?> noBound) {}

  public void upperBound(List<? extends Integer> upperBound) {}
  
  public void lowerBound(List<? super List<Integer>> lowerBound) {}

}
 