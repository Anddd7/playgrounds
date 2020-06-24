package com.github.anddd7.utils.javap;

/**
 * Java 不提供运算符重载, 但 String 可以使用+进行连接
 * 其实是编译器级别的语法糖, +被编译成 StringBuilder
 * 所以在支持这个语法糖的版本, 直接使用+号不会影响效率
 */
public class StringOperation {

  /**
   * ldc: LoaD Constant
   */
  private String newString() {
    return "string";
  }

  /**
   * 不推荐, 因为在使用"string"时, 相当于已经新建了一个字符串, 造成重复工作
   */
  private String newString1() {
    return new String("string");
  }

  /**
   * 这种会被编译器自动组合到一起 iamString
   */
  private String string() {
    return "i" + "am" + "String";
  }

  /**
   * 这里的+号会转化为 String Builder 的 append
   * 这里的=号会转化为 String Builder 的 toString 并赋值
   */
  private String string1() {
    String source = "i";
    // new StringBuilder - append(source) - append("am") - toString - 赋值
    source = source + "am";
    source = source + "String";
    return source;
  }

  /**
   * 同上, 因为是连续的+号, 会转化成连续的 append
   */
  private String stringWithParam(String a) {
    // new StringBuilder - append - append - append - toString
    return "i" + a + "String";
  }

  /**
   * @see StringOperation#string1
   */
  private String stringWithParam1(String a) {
    String source = "i";
    source = source + a;
    source = source + "String";
    return source;
  }


  /**
   * StringBuilder基本用法
   */
  private String stringBuilder() {
    return new StringBuilder()
        .append("i")
        .append("am")
        .append("String")
        .toString();
  }
}
