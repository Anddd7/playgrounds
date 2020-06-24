package com.github.anddd7.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HowToUseStreamTest {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  void filterAndMapWithOfficialStream() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    List<Integer> result = list.stream()
        .map(s -> s.charAt(0))// lazy
        .filter(c -> c > 'd')// lazy
        .map(c -> (int) c)// lazy
        .collect(Collectors.toList());// execute immediately
    logger.info("result: {}", result);
  }

  @Test
  void filterAndMapWithDIYStream() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    List<Integer> result = HowToUseStream.buildStream(list)
        .map(s -> s.charAt(0))
        .filter(c -> c > 'd')
        .map(c -> (int) c)
        .collectToList();
    logger.info("result: {}", result);
  }

}