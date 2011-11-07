package testScannerLib.pkga;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jan Hýbl
 */
public class BasicClass {
  public static final int CONST = 65;
  public static final List<Character> WORD = null;
  public List<File> files;
  
  protected static final int PROTECTED_CONST = 66;
  protected static final List<Character> PROTECTED_WORD = null;
  protected final List<File> protectedFiles = null;
    
  public void mathodA() {}
  public static void methodB(){}
  public int methodC(){return 1;}
  public File methodD(){return null;}
  public void methodE(int number) {}
  public void methodF(File f) {}
  protected void methodG() {}
  public void methodH(int a, int b, float c, double d, File e, List<Integer> f) {}
  
  public void methodI() throws IOException {}
  public void methodJ() throws IOException, Exception {}  
}
