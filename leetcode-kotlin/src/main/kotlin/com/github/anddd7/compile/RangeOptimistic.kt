package com.github.anddd7.compile

@Good
fun case01() {
    for (i in 1..10) {
        // something
    }
}

@DontUse
fun case02() {
    val range = 1..10
    for (i in range) {
        // something
    }
}

@Good
fun case03() {
    for (i in 10 downTo 1) {
        // something
    }
}

@Good
fun case04() {
    for (i in (1..10).reversed()) {
        // something
    }
}

@Good
fun case05() {
    for (i in 1 until 11) {
        // something
    }
}


