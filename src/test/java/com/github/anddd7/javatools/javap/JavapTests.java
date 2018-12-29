package com.github.anddd7.javatools.javap;

import com.github.anddd7.utils.JavaCommandLineUtil;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * 用 javap 工具生成汇编码
 */
class JavapTests {

  @Test
  void generate_DifferenceBetweenIfAndTernaryExpression() throws IOException {
    JavaCommandLineUtil.javap(DifferenceBetweenIfAndTernaryExpression.class);
  }

  @Test
  void generate_BasicTypeAndMethod() throws IOException {
    JavaCommandLineUtil.javap(BasicTypeAndMethod.class);
  }

  @Test
  void generate_SynchronizedMethodAndBlock() throws IOException {
    JavaCommandLineUtil.javap(SynchronizedMethodAndBlock.class);
  }

  @Test
  void generate_StringOperation() throws IOException {
    JavaCommandLineUtil.javap(StringOperation.class);
  }
}
