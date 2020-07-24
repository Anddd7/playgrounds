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
    Assertions.assertThat("just string" == str).isTrue();
    Assertions.assertThat("a final string" == finalStr).isTrue();

    Assertions.assertThat("a static string" == staticStr).isTrue();
    Assertions.assertThat("a static final string" == staticFinalStr).isTrue();
  }

  /**
   * -XX:+PrintGCDetails
   */
  @Test
  public void print_gc_detail() {
    byte[] allocation1, allocation2;
    allocation1 = new byte[30900 * 1024];
    /*
    Heap
      PSYoungGen      total 76288K, used 59773K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)
        eden space 65536K, 91% used [0x000000076ab00000,0x000000076e55f4c8,0x000000076eb00000)
        from space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)
        to   space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)
      ParOldGen       total 175104K, used 0K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
        object space 175104K, 0% used [0x00000006c0000000,0x00000006c0000000,0x00000006cab00000)
      Metaspace       used 8331K, capacity 9250K, committed 9600K, reserved 1056768K
        class space    used 1099K, capacity 1298K, committed 1408K, reserved 1048576K
    */
    // 第一次分配的对象会全部存放在eden区

    allocation2 = new byte[30900 * 1024];
    /*
    [GC (Allocation Failure) [PSYoungGen: 57127K->3272K(76288K)] 57127K->34188K(251392K), 0.0301650 secs]
    [Times: user=0.15 sys=0.02, real=0.03 secs]

    Heap
     PSYoungGen      total 76288K, used 36748K [0x000000076ab00000, 0x0000000774000000, 0x00000007c0000000)
      eden space 65536K, 51% used [0x000000076ab00000,0x000000076cbb1108,0x000000076eb00000)
      from space 10752K, 30% used [0x000000076eb00000,0x000000076ee321c0,0x000000076f580000)
      to   space 10752K, 0% used [0x0000000773580000,0x0000000773580000,0x0000000774000000)
     ParOldGen       total 175104K, used 30916K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
      object space 175104K, 17% used [0x00000006c0000000,0x00000006c1e31010,0x00000006cab00000)
     Metaspace       used 8330K, capacity 9246K, committed 9600K, reserved 1056768K
      class space    used 1099K, capacity 1298K, committed 1408K, reserved 1048576K
     */
    // allocation2创建时, 发现eden区的空间不足, 发生一次MinorGC
    // MinorGC时发现S0/S1的空间也不足以放下allocation1/2, 因此使用 分配担保机制 直接移动到老年区
  }
}
