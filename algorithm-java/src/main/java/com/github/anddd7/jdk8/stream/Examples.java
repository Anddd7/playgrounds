package com.github.anddd7.jdk8.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Examples {

  public <K, V> List<Map<K, V>> sort(List<Map<K, V>> source) {
    return source.stream()
        .sorted(this::buildMapComparator)
        .collect(Collectors.toList());
  }

  private <V, K> int buildMapComparator(Map<K, V> map1, Map<K, V> map2) {
    // TODO compare map then return -1,0,1
    return 0;
  }
}
