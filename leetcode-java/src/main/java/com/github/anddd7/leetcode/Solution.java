package com.github.anddd7.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
    List<Integer> result = new ArrayList<>();
    List<Set<String>> favorites = favoriteCompanies.stream()
        .map(HashSet::new)
        .collect(Collectors.toList());

    for (int i = 0; i < favorites.size(); i++) {
      Set<String> left = favorites.get(i);
      boolean isNotSub = true;
      for (int j = 0; j < favorites.size(); j++) {
        if (i == j) {
          continue;
        }
        Set<String> right = favorites.get(j);

        // left {= right
        if (left.size() <= right.size() && right.containsAll(left)) {
          isNotSub = false;
          break;
        }
      }
      if (isNotSub) {
        result.add(i);
      }
    }

    return result;
  }

  public List<Integer> peopleIndexes_2(List<List<String>> favoriteCompanies) {
    int[] parents = new int[favoriteCompanies.size()];
    for (int i = 0; i < parents.length; i++) {
      parents[i] = i;
    }

    List<Set<String>> favorites = favoriteCompanies.stream()
        .map(HashSet::new)
        .collect(Collectors.toList());

    for (int i = 0; i < favorites.size() - 1; i++) {
      if (parents[i] != i) {
        continue;
      }
      for (int j = i + 1; j < favorites.size(); j++) {
        if (parents[j] != j) {
          continue;
        }
        Set<String> left = favorites.get(i);
        Set<String> right = favorites.get(j);
        // right =} left
        if (left.size() <= right.size() && right.containsAll(left)) {
          parents[i] = j;
          break;
        }
        // left =} right
        else if (left.size() >= right.size() && left.containsAll(right)) {
          parents[j] = i;
        }
        // left x right
      }
    }

    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < parents.length; i++) {
      if (parents[i] == i) {
        indexes.add(i);
      }
    }
    return indexes;
  }
}
