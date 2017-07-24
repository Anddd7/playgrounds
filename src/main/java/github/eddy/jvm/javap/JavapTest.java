package github.eddy.jvm.javap;

import github.eddy.common.JavaCLIUtil;
import github.eddy.jvm.ReferenceProblem;

public class JavapTest {

  public static void main(String[] a) {
    //JavaCLIUtil.javapToDir(TwoSum.class, "machine-code");
    JavaCLIUtil.javapToDir(ReferenceProblem.class, "machine-code");
  }
}
