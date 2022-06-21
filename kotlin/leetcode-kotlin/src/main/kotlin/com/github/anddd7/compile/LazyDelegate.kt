package com.github.anddd7.compile

import kotlin.reflect.KProperty

class ExampleLazy {
    // lazy (default SYNCHRONIZED)
    // 双重锁
    val l1: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { "Get from DB" }
    //
    val l2: String by lazy(LazyThreadSafetyMode.PUBLICATION) { "Get from DB" }
    // 单线程或无并发问题
    @Good
    val l3: String by lazy(LazyThreadSafetyMode.NONE) { "Get from DB" }
}

class Example {
    // 需要实例化一个委托类对象
    val p: String by StringDelegate()

    // 通过Map实现对象共用
    val a: Long by SharedDelegate
    val b: Long by SharedDelegate

    // 通过泛型实现类共用
    val c: Int by GenericDelegate()
    val d: Double by GenericDelegate()
}

class StringDelegate {
    operator fun getValue(example: Example, property: KProperty<*>): String {
        var result = cache
        if (result == null) {
            result = "Get from DB"
            cache = result
        }
        return result
    }

    private var cache: String? = null
}

class GenericDelegate<T> {
    operator fun getValue(example: Example, property: KProperty<*>): T {
        var result = cache
        if (result == null) {
            result = "a" as T // get from DB
            cache = result
        }
        return result as T
    }

    private var cache: T? = null
}

object SharedDelegate {
    operator fun getValue(example: Example, property: KProperty<*>): Long {
        return values[property.name] as Long
    }

    private val values: MutableMap<String, Any> = mutableMapOf()
}
