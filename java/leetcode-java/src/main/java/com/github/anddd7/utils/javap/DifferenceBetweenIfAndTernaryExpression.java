package com.github.anddd7.utils.javap;

public class DifferenceBetweenIfAndTernaryExpression {

  /**
   * if_icmple - load&return
   * () - load&return
   */
  private int compareWithIfElse(int a, int b) {
    if (a > b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * 相比上面, 使用goto进行跳转
   *
   * if_icmple - load - goto - return
   * () - load - return
   */
  private int compareWithTernary(int a, int b) {
    return a > b ? a : b;
  }

  /**
   * @see DifferenceBetweenIfAndTernaryExpression#compareWithIfElse(int, int)
   * = 号编译成 istore_1 指令
   */
  private int assignWithIf(int a, int b) {
    if (a < b) {
      a = b;
    }
    return a;
  }
}
