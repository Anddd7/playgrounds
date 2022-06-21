package com.github.anddd7.jdk8.generics;

import com.github.anddd7.utils.JavaCommandLineTool;
import java.io.IOException;
import java.util.Date;

public class DateInter extends Pair<Date> {

  public DateInter(Date obj) {
    super(obj);
  }

  /**
   * 函数签名(返回值)变了, 重写, 非重载
   *
   * JVM会为此生成一个桥方法 Object getObj();
   * 实际是就变成了 (Date)super.getObj()
   */
  @Override
  public Date getObj() {
    return super.getObj();
  }

  @Override
  public void setObj(Date obj) {
    super.setObj(obj);
  }

  public static void main(String[] args) throws IOException {
    JavaCommandLineTool.javap(DateInter.class);
  }
}
