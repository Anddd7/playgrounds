package com.github.anddd7.leetcode.sort

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TopologicalSortingTest {

    private val topologicalDAG: List<Pair<Int, List<Int>>> = mutableListOf(
        Pair(1, emptyList()),
        Pair(2, listOf(1)),
        Pair(3, listOf(1)),
        Pair(4, listOf(2)),
        Pair(5, listOf(3, 4)),
        Pair(6, listOf(1)),
        Pair(7, listOf(1, 5)),
        Pair(8, listOf(1, 2, 3)),
        Pair(9, listOf(8)),
        Pair(10, emptyList()),
        Pair(11, emptyList()),
        Pair(12, listOf(10))
    )

    @Test
    fun `topological sorting with generic type`() {
        val result = TopologicalSorting(
            topologicalDAG.shuffled(),
            { first }, { second },
            Comparator.comparingInt { it.first }
        ).sort()
        val resultIds = result.map { it.first }

        assertThat(resultIds)
            .containsSequence(1, 10, 11, 2, 3, 6, 12, 4, 8, 5, 9, 7)
    }

    private val circleDependencies: List<Pair<Int, List<Int>>> = mutableListOf(
        Pair(1, emptyList()),
        Pair(2, listOf(3)),
        Pair(3, listOf(4)),
        Pair(4, listOf(2))
    )

    @Test
    fun `throw IllegalArgumentException while have circle dependencies`() {
        val exception = assertThrows<IllegalArgumentException> {
            TopologicalSorting(
                circleDependencies.shuffled(),
                { first }, { second },
                Comparator { o1, o2 -> o1.first - o2.first }
            )
                .sort()
        }
        assertThat(exception)
            .hasMessage("Cannot sort these elements, because of circle dependencies between: [2, 3, 4]")
    }
}
