package com.github.anddd7.compiler

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SimpleCompilerTest {
  @Test
  fun `should ignore newline`() {
    val simpleCompiler = SimpleCompiler("\n")

    assertThat(simpleCompiler.next()).isEqualTo(Token.EOF)
    assertThat(simpleCompiler.lexicalSymbols()).isEmpty()
  }

  @Test
  fun `should parse Id successful`() {
    val simpleCompiler = SimpleCompiler("abc_identifier_3")

    assertThat(simpleCompiler.next()).isEqualTo(Token.Id)
    assertThat(simpleCompiler.lexicalSymbols()).containsEntry(
      "abc_identifier_3",
      "abc_identifier_3"
    )
  }

  @Test
  fun `should parse Assign Number to Identifier successful`() {
    val simpleCompiler = SimpleCompiler("abc_identifier_3=1000;")

    assertThat(simpleCompiler.next()).isEqualTo(Token.Id)
    assertThat(simpleCompiler.next()).isEqualTo(Token.Assign)
    assertThat(simpleCompiler.next()).isEqualTo(Token.Num)
    assertThat(simpleCompiler.next()).isEqualTo(';')
    assertThat(simpleCompiler.lexicalSymbols()).containsEntry(
      "abc_identifier_3",
      1000
    )
  }
}
