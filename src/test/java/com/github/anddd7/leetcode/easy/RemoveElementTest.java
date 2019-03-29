package com.github.anddd7.leetcode.easy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveElementTest {

  private RemoveElement instance = new RemoveElement();

  @Test
  void case1() {
    int[] array = new int[]{1, 1, 2};
    Assertions.assertThat(instance.removeElement(array, 1)).isEqualTo(1);
    Assertions.assertThat(array[0]).isEqualTo(2);
  }

  @Test
  void case2() {
    int[] array = new int[]{3, 2, 2, 3};
    Assertions.assertThat(instance.removeElement(array, 3)).isEqualTo(2);
    Assertions.assertThat(array[0]).isEqualTo(2);
    Assertions.assertThat(array[1]).isEqualTo(2);
  }

  @Test
  void case3() {
    int[] array = new int[]{3, 3};
    Assertions.assertThat(instance.removeElement(array, 3)).isEqualTo(0);
  }
}