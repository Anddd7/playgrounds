package com.github.anddd7.compiler

import java.util.TreeMap

/**
 * 解释器
 */
class SimpleCompiler(private val source: String) {
  private var lastPos = 0
  private var line = 0
  private val symbols = TreeMap<String, Any>()

  /**
   * lexical analysis
   */
  fun next(): Any {
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
        val name = source.substring(startPos, ++lastPos)
        symbols.getOrPut(name) { name }
        return Token.Id
      }
      // Number
      else if (source[lastPos].isDigit()) {
        val first = source[lastPos++]
        var value = first - '0'
        // decimal
        if (first != '0') {
          while (source[lastPos].isDigit()) {
            value = value * 10 + (source[lastPos++] - '0')
          }
        }
        return value
      }
      // parse string literal, currently, the only supported escape
      else if (source[lastPos] == '"') {
        val startPos = lastPos++
        while (source[lastPos] != '"') {
          lastPos++
        }
        return source.substring(startPos + 1, lastPos++)
      }
      // parse '==' and '='
      else if (source[lastPos] == '=') {
        if (source[++lastPos] == '=') {
          return Token.Eq;
        }
        return Token.Assign
      }
      // directly return the character as token
      else if (
        source[lastPos] == '~' || source[lastPos] == ';' ||
        source[lastPos] == '{' || source[lastPos] == '}' ||
        source[lastPos] == '(' || source[lastPos] == ')' ||
        source[lastPos] == ']' || source[lastPos] == ',' ||
        source[lastPos] == ':'
      ) {
        return source[lastPos++]
      }
    }
    // EOF
    return Token.EOF
  }

  internal fun lexicalSymbols() = symbols.toMap()

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
