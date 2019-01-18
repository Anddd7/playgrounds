package com.github.anddd7.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NullEqualsTest {

    @Test
    fun `trick if you equals something in function 'run' of the nullable type`() {
        val nullInstance: Int? = 1
        assertThat(nullInstance).isEqualTo(1)
        assertThat(nullInstance.run { equals(1) }).isFalse()
        assertThat(nullInstance?.run { equals(1) }).isTrue()
    }
}