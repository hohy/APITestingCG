package testAPIClassRes;

import javax.swing.JFrame;
import java.io.File;
import java.util.List;
import java.io.IOException;

public class TestAPIClassB extends JFrame implements Runnable {
  public TestAPIClassB() {};
  public TestAPIClassB(int a) {};
  public TestAPIClassB(File a, int b) {};

  public static final int SIZE = 100;
  protected File source = null;
  public void run() {}
  protected List<Integer> getList(String a, int b) throws IOException {
    return null;
  }
}