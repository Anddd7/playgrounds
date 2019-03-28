package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class GenerateParenthesesTest {

  private GenerateParentheses instance = new GenerateParentheses();

  @Test
  void case1() {
    System.out.println(instance.generateParenthesis(3));
    assertThat(instance.generateParenthesis(3)).hasSize(5);
  }

  @Test
  void case2() {
    System.out.println(instance.generateParenthesis(4));
    assertThat(instance.generateParenthesis(4)).hasSize(14);
  }
}