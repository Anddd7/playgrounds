package com.github.anddd7.algorithm.sort;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * DAG 有向无环图排序
 */
class TopologicalSorting<T, K> {

  private List<Element> elements;
  private Map<K, List<Element>> graph;
  private Comparator<Element> comparator;

  static <T, K> List<T> sort(
      List<T> objects,
      Function<T, K> keyExtractor, Function<T, List<K>> dependenciesExtractor,
      Comparator<T> groupComparator
  ) {
    TopologicalSorting<T, K> sorting = new TopologicalSorting<>();
    sorting.wrapObject(objects, keyExtractor, dependenciesExtractor);
    sorting.wrapComparator(groupComparator);
    sorting.initGraph();
    return sorting.process();
  }

  private void wrapObject(List<T> objects, Function<T, K> keyExtractor, Function<T, List<K>> dependenciesExtractor) {
    this.elements = objects.stream().map(it ->
        new Element(it, keyExtractor.apply(it), dependenciesExtractor.apply(it))
    ).collect(toList());
  }

  private void wrapComparator(Comparator<T> groupComparator) {
    this.comparator = (o1, o2) ->
        groupComparator != null ?
            groupComparator.compare(o1.object, o2.object)
            : o1.compareTo(o2);
  }

  private void initGraph() {
    this.graph = new HashMap<>();
    this.elements.stream()
        .filter(e -> e.unresolvedDependencies != 0)
        .forEach(e ->
            e.dependencies.forEach(dep ->
                graph.computeIfAbsent(dep, key -> new ArrayList<>()).add(e)
            )
        );
  }

  private List<T> process() {
    List<Element> setOfZeroIndegree = findVerticesOfZeroIndegree();
    List<Element> result = resolveDependencies(setOfZeroIndegree);
    checkAllDependenciesHasBeenResolved();
    return result.stream().map(e -> e.object).collect(toList());
  }


  private List<Element> findVerticesOfZeroIndegree() {
    return elements.stream().filter(e -> e.unresolvedDependencies == 0).sorted(comparator).collect(toList());
  }


  private List<Element> resolveDependencies(List<Element> setOfZeroIndegree) {
    if (setOfZeroIndegree.isEmpty()) {
      return emptyList();
    }

    List<Element> nextGroup = setOfZeroIndegree.stream()
        .flatMap(e ->
            ofNullable(graph.get(e.key))
                .orElse(emptyList())
                .stream()
        )
        .filter(e -> e.resolve() == 0)
        .sorted(comparator)
        .collect(toList());

    List<Element> result = new ArrayList<>(elements.size());
    result.addAll(setOfZeroIndegree);
    result.addAll(resolveDependencies(nextGroup));
    return result;
  }

  private void checkAllDependenciesHasBeenResolved() {
    List<K> circleDependencies = elements.stream()
        .filter(e -> e.unresolvedDependencies > 0)
        .map(e -> e.key)
        .sorted(Comparator.comparingInt(Object::hashCode))
        .collect(toList());
    if (!circleDependencies.isEmpty()) {
      throw new IllegalArgumentException(
          "Circle dependencies:" + Arrays.toString(circleDependencies.toArray(new Object[0]))
      );
    }
  }

  class Element implements Comparable<Element> {

    private T object;
    private K key;
    private List<K> dependencies;
    private int unresolvedDependencies;

    Element(T object, K key, List<K> dependencies) {
      this.object = object;
      this.key = key;
      this.dependencies = dependencies;
      this.unresolvedDependencies = dependencies.size();
    }

    int resolve() {
      return --unresolvedDependencies;
    }

    @Override
    public int compareTo(@NotNull TopologicalSorting<T, K>.Element o) {
      return key.hashCode() - o.key.hashCode();
    }
  }
}
