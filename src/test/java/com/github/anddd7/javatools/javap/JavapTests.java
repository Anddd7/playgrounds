package com.github.anddd7.javatools.javap;

import com.github.anddd7.utils.JavaCommandLineUtil;
import java.io.IOException;
import org.junit.Test;

/**
 * @author Anddd7
 * 用 javap 工具生成汇编码
 */
public class JavapTests {

  @Test
  public void generate_DifferenceBetweenIfAndTernaryExpression() throws IOException {
    JavaCommandLineUtil.javap(DifferenceBetweenIfAndTernaryExpression.class);
  }

  @Test
  public void generate_BasicTypeAndMethod() throws IOException {
    JavaCommandLineUtil.javap(BasicTypeAndMethod.class);
  }

  @Test
  public void generate_SynchronizedMethodAndBlock() throws IOException {
    JavaCommandLineUtil.javap(SynchronizedMethodAndBlock.class);
  }

  @Test
  public void generate_StringOperation() throws IOException {
    JavaCommandLineUtil.javap(StringOperation.class);
  }
}
