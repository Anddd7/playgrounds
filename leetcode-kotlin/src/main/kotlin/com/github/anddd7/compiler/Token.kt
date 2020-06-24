package com.github.anddd7.compiler


/**
 * 所有需要的关键字/运算符/数据类型, 按C语言优先级排序
 */
enum class Token {
  Num, Fun, Sys, Glo, Loc, Id, Char, Else, Enum, If, Int, Return, Sizeof, While,
  Assign, Cond, Lor, Lan, Or, Xor, And, Eq, Ne, Lt, Gt, Le, Ge,
  Shl, Shr, Add, Sub, Mul, Div, Mod, Inc, Dec, Brak, EOF
}
