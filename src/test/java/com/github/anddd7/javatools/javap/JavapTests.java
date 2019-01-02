package com.github.anddd7.javatools.javap;

import com.github.anddd7.utils.JavaCommandLineTool;
import com.github.anddd7.utils.javap.BasicTypeAndMethod;
import com.github.anddd7.utils.javap.DifferenceBetweenIfAndTernaryExpression;
import com.github.anddd7.utils.javap.StringOperation;
import com.github.anddd7.utils.javap.SynchronizedMethodAndBlock;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * 用 javap 工具生成汇编码
 */
class JavapTests {

  @Test
  void generate_DifferenceBetweenIfAndTernaryExpression() throws IOException {
    JavaCommandLineTool.javap(DifferenceBetweenIfAndTernaryExpression.class);
  }

  @Test
  void generate_BasicTypeAndMethod() throws IOException {
    JavaCommandLineTool.javap(BasicTypeAndMethod.class);
  }

  @Test
  void generate_SynchronizedMethodAndBlock() throws IOException {
    JavaCommandLineTool.javap(SynchronizedMethodAndBlock.class);
  }

  @Test
  void generate_StringOperation() throws IOException {
    JavaCommandLineTool.javap(StringOperation.class);
  }
}
