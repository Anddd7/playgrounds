package com.github.anddd7.compile

class Something {

    private val hello = 0

    /**
     * 伴生对象被编译成一个单例, 而不是static method
     */
    companion object {
        @DontUse
        fun newInstance() = Something()

        /**
         * 会被编译成类的public final static方法
         */
        @JvmStatic
        @DontUse
        fun getInstance() = Something()

        /**
         * 基本类型 内联到调用处
         */
        @Good
        private const val LABLEA = "A"
        /**
         * 基本类型 public final static
         */
        private val LABLE0 = 0
        const val LABLEB = "B"

        /**
         * 非基本类型 private final static
         */
        private val LIST0 = listOf<String>()
        val LIST1 = listOf<String>()
    }

    fun lable0() = LABLE0
    fun lableA() = LABLEA
    fun list0() = LIST0
}

fun getLabel0() = Something.getInstance().lable0()
