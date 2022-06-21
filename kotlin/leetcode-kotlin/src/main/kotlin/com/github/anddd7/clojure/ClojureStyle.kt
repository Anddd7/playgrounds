package com.github.anddd7.clojure

/*
(def vowel? (set "aeiou"))

(defn pig-latin [word]                                      ; 全局变量
  ; define variable = first(word)
  (let [first-letter (first word)]                          ; 当前域的变量, let <vector>, let [<name> value]
    ; if set.contains
    (if (vowel? first-letter)
      ; then str = word + "ay"
      (str word "ay")
      ; else str = word[1:] + word[0] + "ay"
      (str (subs word 1) first-letter "ay"))))
 */

val vowel = "aeiou".toCharArray().toSet()

fun pigLatin(word: String): String {
    val firstLetter = word[0]
    return if (firstLetter in vowel) {
        word + "ay"
    } else {
        word.substring(1) + firstLetter + "ay"
    }
}

fun ifThen(predicate: () -> Boolean, then: () -> String, els: () -> String): String {
    return if (predicate()) then() else els()
}

fun pigLatinFn(word: String) {
    return with(word[0]) {
        if (this in vowel) word + "ay" else word.substring(1) + this + "ay"
    }
}

