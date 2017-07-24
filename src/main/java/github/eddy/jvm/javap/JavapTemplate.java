package github.eddy.jvm.javap;

public class JavapTemplate {

  public int i1 = 10;
  protected int i2 = 10;
  private int i3 = 10;
  int i4 = 10;

  public static int j1 = 10;
  protected static int j2 = 10;
  private static int j3 = 10;
  static int j4 = 10;


  String str = "Hello JVM";

  public JavapTemplate() {
    System.out.println(str);
  }

  public void getAll() {
    int m;
    m = i1;
    m = i2;
    m = i3;
    m = i4;
    m = j1;
    m = j2;
    m = j3;
    m = j4;
  }
}
