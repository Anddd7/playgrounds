package com.github.anddd7.jdk8.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ReflectTests {

  @Test
  void should_get_generic_type_of_list() {
    List<Map> list = new ArrayList<>();

    ParameterizedType listGenericType = (ParameterizedType) list.getClass().getGenericSuperclass();
    Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
    System.out.println(listActualTypeArguments[listActualTypeArguments.length - 1]);
    for (int i = 0; i < listActualTypeArguments.length; i++) {
      System.out.println(listActualTypeArguments[i]);
    }
  }
}
