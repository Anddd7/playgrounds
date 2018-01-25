package github.eddy.jvm.javap;

/**
 * @author and777
 * @date 2018/1/24
 */
public class DifferenceWithIfAnd3Operation {

  /**
   * 0: iload_1
   * 1: iload_2
   * 2: if_icmple     7
   * 5: iload_1
   * 6: ireturn
   * 7: iload_2
   * 8: ireturn
   */
  public int testIf(int a, int b) {
    if (a > b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * 0: iload_1
   * 1: iload_2
   * 2: if_icmple     9
   * 5: iload_1
   * 6: goto          10
   * 9: iload_2
   * 10: ireturn
   */
  public int test3Operation(int a, int b) {
    return a > b ? a : b;
  }

  /**
   * 0: iload_1
   * 1: iload_2
   * 2: if_icmpge     7
   * 5: iload_2
   * 6: istore_1
   * 7: iload_1
   * 8: ireturn
   */
  public int test2If(int a, int b) {
    if (a < b) {
      a = b;
    }
    return a;
  }

  /**
   * 0: iload_1
   * 1: iload_2
   * 2: if_icmple     9
   * 5: iload_1
   * 6: goto          10
   * 9: iload_2
   * 10: istore_1
   * 11: iload_1
   * 12: ireturn
   */
  public int test23Operation(int a, int b) {
    a = a > b ? a : b;
    return a;
  }
}
