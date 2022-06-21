package com.github.anddd7.jdk8.generics;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GetClassTest {
  @Test
  public void should_return_different_class_instance() {
    Number intNumber = 1;
    Number longNumber = 1L;

    Class<? extends Number> integerClass = intNumber.getClass();
    Class<? extends Number> longNumberClass = longNumber.getClass();

    Assertions.assertThat(integerClass).isNotEqualTo(longNumberClass);

    Assertions.assertThat(integerClass).isEqualTo(Integer.class);
    Assertions.assertThat(longNumberClass).isEqualTo(Long.class);

//    Class<Number> numberClass = intNumber.getClass(); // error
//    Class<Integer> realIntegerClass = intNumber.getClass(); // error
  }
}
