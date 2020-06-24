package com.github.anddd7.compiler

class SimpleCompiler(private val source: String) {
  private var lastPos = 0
  private var line = 0
  private val symbols = mutableMapOf<String, Any>()

  /**
   * lexical analysis
   */
  fun next(): Token {
    while (lastPos < source.length) {
      // ignore newline
      if (source[lastPos] == '\n') {
        line++
        lastPos++
      }
      // Identifier
      else if (source[lastPos].isLetter() || source[lastPos] == '_') {
        val startPos = lastPos++
        while (source[lastPos].isLetter() || source[lastPos] == '_') {
          lastPos++
        }
        val name = source.substring(startPos, lastPos + 1)
        symbols.getOrPut(name) { name }
        return Token.Id
      }
    }
    // EOF
    return Token.EOF
  }

  fun program() {
    TODO()
  }


  fun eval() {
    TODO()
  }

  fun expression(level: Int) {
    TODO()
  }
}
