package github.eddy.jvm;

import org.junit.Test;

public class ClassInitTest {

  @Test
  public void classLoadTest() {
    /**
     * 主动引用 : 子类中的变量是引用的父类中的静态变量 ,必须对父类进行加载
     * 被动引用 : 子类中的变量只是一个引用 ,不需要初始化子类
     */
    System.out.println(Child.m);
    /**
     * 父类被初始化
     * 33
     */
  }

  @Test
  public void finalTest() {
    /**
     * 编译阶段会将常量存储到调用它的类的常量池中 ,调用时直接到调用类的常量池获取 ,不再经过原类
     */
    System.out.println(Const.NAME);
  }

  @Test
  public void newArrayTest() {
    /**
     * new 数组并不会初始化对象
     */
    Const[] consts = new Const[5];
    System.out.println("数组初始化完成");
    consts[0] = new Const();
  }
}

class Super {

  public static int m = 11;

  static {
    System.out.println("执行了super类静态语句块");
  }
}

class Father extends Super {

  public static int m = 33;

  static {
    System.out.println("执行了父类静态语句块");
  }
}

class Child extends Father {

  static {
    System.out.println("执行了子类静态语句块");
  }
}

class Const {

  public static final String NAME = "我是常量";

  static {
    System.out.println("初始化Const类");
  }
}

