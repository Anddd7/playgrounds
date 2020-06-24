package com.github.anddd7.compiler

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SimpleCompilerTest {
  @Test
  fun `should ignore newline`() {
    val simpleCompiler = SimpleCompiler("\n")

    Assertions.assertThat(simpleCompiler.next()).isEqualTo(Token.EOF)
  }

  @Test
  fun `should parse Id successful`() {
    val simpleCompiler = SimpleCompiler("abc_identifier_3")

    Assertions.assertThat(simpleCompiler.next()).isEqualTo(Token.Id)
  }
}
