package github.eddy.jvm;

import org.junit.Test;

public class ClassLoadTest {

  /**
   * {BootstrapClassLoader} 加载 java.* 开头的基本类
   * {@link sun.misc.Launcher.ExtClassLoader} 加载 jre/lib/ext
   * {@link sun.misc.Launcher.AppClassLoader} 加载其他
   *
   * 系统内置的只能从本地文件系统 (的classpath) 中加载类 ,通过自定义类加载器 ,可以加载远程的/动态的类
   */

  @Test
  public void classLoadTest() {
    /**
     * 访问Child2.b时 ,检查到b引用的父类的a变量
     * 检查a是Father2的常量 ,初始化为0
     * 根据static代码段和赋值操作的顺序 ,为a进行赋值
     * 然后返回a给Child ,Child把b放入自己的常量池 ,然后初始化自己
     */
    System.out.println(Child2.b);
  }
}

class Father2{
  static{
    a = 2;
    System.out.println("Father静态语句");
  }
  public static int a = 1;
}

class Child2 extends Father2{
  public static int b = a;
  static {
    System.out.println("Child静态语句");
  }
}
