package com.github.anddd7.jdk8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstantStringPoolTest {

  /**
   * Constant pool:
   *    #2 = String             #36            // just string
   * > String类型的只是一个index, 指向#36
   *
   *    #4 = String             #38            // a final string
   *   #10 = String             #46            // a static string
   *   #20 = String             #50            // a static final string
   *   #36 = Utf8               just string
   * > 储存实际的字符串常量
   *   #38 = Utf8               a final string
   *   #46 = Utf8               a static string
   *   #50 = Utf8               a static final string
   */
  /**
   *          0: aload_0
   *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
   *          4: aload_0
   *  > 构造函数创建对象分配内存, 会调用ldc读取字面量
   *  > ldc是lazy resolution:
   *    > 到当前类的runtime constant pool(HotSpot是ConstantPool + ConstantPoolCache)去查找该index对应的项
   *    > 如果该项尚未resolve则resolve之，并返回resolve后的内容
   *    > resolve String类型的变量时, 会getOrPut到字符串常量池
   *          5: ldc           #2                  // String just string
   *          7: putfield      #3                  // Field str:Ljava/lang/String;
   *         10: aload_0
   *         11: ldc           #4                  // String a final string
   *         13: putfield      #5                  // Field finalStr:Ljava/lang/String;
   *         16: return
   */
  private String str = "just string";
  private final String finalStr = "a final string";
  private static String staticStr = "a static string";
  private static final String staticFinalStr = "a static final string";

  @Test
  public void test() {
    Assertions.assertThat("just string" ==str  ).isTrue();
    Assertions.assertThat( "a final string"==finalStr).isTrue();

    Assertions.assertThat( "a static string" == staticStr ).isTrue();
    Assertions.assertThat("a static final string" == staticFinalStr).isTrue();
  }
}
