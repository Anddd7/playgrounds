package github.eddy.jvm;

import github.eddy.common.JavaCLIUtil;

public class JavapTest {
  public static void main(String[] a) {
    //JavaCLIUtil.javapToDir(TwoSum.class, "machine-code");
    JavaCLIUtil.javapToDir(ProblemOfFOR.class, "machine-code");
  }
}
