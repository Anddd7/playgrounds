package com.github.anddd7.algorithm.sort;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class TopologicalSortingTest {

  private List<Task> topologicalDAG = new ArrayList<>();
  private List<Task> circleDependencies = new ArrayList<>();

  @BeforeAll
  void setUp() {
    topologicalDAG.add(new Task(1, emptyList()));
    topologicalDAG.add(new Task(2, singletonList(1)));
    topologicalDAG.add(new Task(3, singletonList(1)));
    topologicalDAG.add(new Task(4, singletonList(2)));
    topologicalDAG.add(new Task(5, Arrays.asList(3, 4)));
    topologicalDAG.add(new Task(6, singletonList(1)));
    topologicalDAG.add(new Task(7, Arrays.asList(1, 5)));
    topologicalDAG.add(new Task(8, Arrays.asList(1, 2, 3)));
    topologicalDAG.add(new Task(9, singletonList(8)));
    topologicalDAG.add(new Task(10, emptyList()));
    topologicalDAG.add(new Task(11, emptyList()));
    topologicalDAG.add(new Task(12, singletonList(10)));

    circleDependencies.add(new Task(1, emptyList()));
    circleDependencies.add(new Task(2, singletonList(3)));
    circleDependencies.add(new Task(3, singletonList(4)));
    circleDependencies.add(new Task(4, singletonList(2)));
  }

  @Test
  void sorting_successful() {
    List<Task> result = TopologicalSorting.sort(
        topologicalDAG,
        e -> e.id, e -> e.dependencies,
        comparingInt(o -> o.id)
    );
    List<Integer> sequence = result.stream().map(task -> task.id).collect(Collectors.toList());

    assertThat(sequence)
        .containsSequence(
            1, 10, 11, 2, 3, 6, 12, 4, 8, 5, 9, 7
        );
  }

  @Test
  void sorting_failed() {
    assertThrows(
        IllegalArgumentException.class,
        () -> TopologicalSorting.sort(circleDependencies, e -> e.id, e -> e.dependencies, comparingInt(o -> o.id))
    );
  }

  static class Task {

    Integer id;
    List<Integer> dependencies;

    Task(Integer id, List<Integer> dependencies) {
      this.id = id;
      this.dependencies = dependencies;
    }
  }
}