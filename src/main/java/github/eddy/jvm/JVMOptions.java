package github.eddy.jvm;

public class JVMOptions {
  /**
   * List<String> 泛型在编译后会被擦除 ,只是作为编译时的约束
   *
   * javac : .java->.class ,被成为前端编译器
   *        语法/词法分析 填充符号表 语义分析
   * JIT (just in time) : .class->机器码 ,单一的解释字节码然后执行效率很低 ,对于一些使用频率很高的代码 ,直接编译成机器码运行
   *        热点探测
   */

}
