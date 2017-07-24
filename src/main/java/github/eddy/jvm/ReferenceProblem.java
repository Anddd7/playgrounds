package github.eddy.jvm;

import java.lang.ref.SoftReference;

public class ReferenceProblem {

  /**
   * 'for' has some different between two written-style
   *
   * 'softRef' in test2 is equals 'softRef = softRefs[i]' ,it's a reference of element in array
   * so , use new connect 'softRef' with new Object ,but softRefs[i] is not change .
   */
  int size = 100000;

  public void test1() {
    SoftReference[] softRefs = new SoftReference[size];
    for (int i = 0; i < size; i++) {
      softRefs[i] = new SoftReference(new String("soft"));
    }
    System.out.println(softRefs[0].get());
  }

  public void test2() {
    SoftReference[] softRefs = new SoftReference[size];
    for (SoftReference softRef : softRefs) {
      softRef = new SoftReference(new String("soft"));
    }
    System.out.println(softRefs[0].get());
  }

  /**
   * 基本类型的值表示值本身 ; 引用类型的值表示值所在的(堆内存的)地址
   * 在方法中定义的基本类型存放在方法栈中 ;  在类中定义的基本类型随类存放在堆中 (通过new操作)
   *     栈                                                                   堆
   * |  102   |  (int)
   * |  23L   |  (long)
   * |  true  |  (boolean)                     |    1    |
   * |  *ref   | (Object)           ->         |  false |
   *                                                 |   *ref  |         ->    |  'a'   |
   *
   * 包装类是为了方便面向对象操作 ,编译器会加上 装箱/拆箱 操作 :  Integer i=10 -> Integer.valueOf(10); int j = i -> i.intValue;
   * String 是比较特殊的包装类 :
   * String str = "123";  在栈中创建一个"123"的字符串 ,str指向它 ; 如果栈中已存在相同的字串 ,直接指向
   * String str = new String("123");   堆中创建一个String对象 ,里面包含"123"的字符串 ,str保存其引用地址
   */
  class Wrapper {

    Integer i;

    public Wrapper(Integer i) {
      this.i = i;
    }

    void add(Integer j) {
      i += j;
    }
  }

  public void test3() {
    Wrapper w = new Wrapper(0);
    Wrapper w1 = w, w2 = w;

    w1.add(1);
    System.out.println(w.i);
    w2.add(10);
    System.out.println(w.i);
  }

  public static void main(String[] a) {
    ReferenceProblem lab = new ReferenceProblem();
    //lab.test1();
    //lab.test2();
    lab.test3();
  }
}