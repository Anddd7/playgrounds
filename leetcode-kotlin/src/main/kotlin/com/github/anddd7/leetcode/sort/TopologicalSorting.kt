package com.github.anddd7.leetcode.sort

class TopologicalSorting<T, K>(
    objects: List<T>,
    keyExtractor: T.() -> K,
    dependenciesExtractor: T.() -> List<K>,
    groupComparator: Comparator<T>? = null
) {
    /**
     * wrapper object into element to process sorting
     */
    private val elements = objects.map {
        Element(it, it.keyExtractor(), it.dependenciesExtractor())
    }
    /**
     * sort elements in a group (which has same dependencies)
     */
    private val comparator = Comparator<Element> { o1, o2 ->
        groupComparator?.compare(o1.obj, o2.obj) ?: o1.compareTo(o2)
    }
    private val graph = mutableMapOf<K, MutableList<Element>>()

    fun sort(): List<T> {
        val initialVertices = prepareGraphAndInitialVertices()
        val result = resolveDependencies(initialVertices)
        checkAllDependenciesHasBeenResolved()
        return result.map { it.obj }
    }

    /**
     * check dependencies and store edges into a map
     */
    private fun prepareGraphAndInitialVertices(): List<Element> {
        val (setOfZeroIndegree, hasDependencies) = elements.partition { it.dependencies.isEmpty() }

        for (element in hasDependencies) {
            for (dependency in element.dependencies) {
                graph.getOrPut(dependency) { mutableListOf() }.add(element)
            }
        }
        return setOfZeroIndegree.sortedWith(comparator)
    }

    private fun resolveDependencies(vertices: List<Element>): List<Element> {
        if (vertices.isEmpty()) return emptyList()

        val nextGroup = vertices
            .flatMap { graph[it.key] ?: emptyList<Element>() }
            .filter { --it.unresolvedDependencies == 0 }
            .sortedWith(comparator)

        return vertices.toMutableList().apply {
            addAll(resolveDependencies(nextGroup))
        }
    }

    private fun checkAllDependenciesHasBeenResolved() {
        val circleDependencies = elements.filter { it.unresolvedDependencies > 0 }
            .map { it.key }
            .sortedBy { it.hashCode() }
        if (circleDependencies.isNotEmpty()) {
            throw IllegalArgumentException(
                "Cannot sort these elements, because of circle dependencies between: $circleDependencies"
            )
        }
    }

    inner class Element(
        val obj: T,
        val key: K,
        val dependencies: List<K>,
        var unresolvedDependencies: Int = dependencies.size
    ) : Comparable<Element> {
        override fun compareTo(other: Element) = key.hashCode().compareTo(other.key.hashCode())
    }
}
