package com.github.anddd7.datastructure;

import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.anddd7.datastructure.LoopAndMap.City;
import com.github.anddd7.datastructure.LoopAndMap.Province;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoopAndMapTest {

  private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  static Stream<Arguments> sourceDate() {
    return of(
        asList(10, 10),
        asList(10, 10),


        asList(100, 10),
        asList(100, 10),

        asList(10, 100),
        asList(10, 100),

        asList(100, 100),
        asList(100, 100),

        asList(100, 1000),
        asList(100, 1000),

        asList(1000, 100),
        asList(1000, 100),

        asList(1000, 1000),
        asList(1000, 1000)
    ).map(numbers -> provideProvinces(numbers.get(0), numbers.get(1)));
  }

  static Arguments provideProvinces(int pNum, int cNum) {
    List<Province> provinces = new ArrayList<>(pNum);
    List<City> cities = new ArrayList<>(pNum * cNum);
    for (int i = 0; i < pNum; i++) {
      provinces.add(new Province(i, "p-" + i));
      cities.addAll(provideCities(i, cNum));
    }
    return Arguments.arguments("Province:" + pNum + ", City:" + cNum, provinces, cities);
  }

  static List<City> provideCities(int provinceId, int cNum) {
    List<City> cities = new ArrayList<>(cNum);
    for (int j = 0; j < cNum; j++) {
      cities.add(new City(j, provinceId, "c-" + provinceId + "-" + j));
    }
    return cities;
  }


  @ParameterizedTest(name = "[{index}]")
  @MethodSource("sourceDate")
  public void forFind(String title, List<Province> provinces, List<City> cities) {
    long start = System.currentTimeMillis();
    assertThat(LoopAndMap.forFind(provinces, cities)).hasSize(provinces.size());
    logger.info(((System.currentTimeMillis() - start)) + " - " + title);
  }

  @ParameterizedTest(name = "[{index}]")
  @MethodSource("sourceDate")
  public void mapFind(String title, List<Province> provinces, List<City> cities) {
    long start = System.currentTimeMillis();
    assertThat(LoopAndMap.mapFind(provinces, cities)).hasSize(provinces.size());
    logger.info(((System.currentTimeMillis() - start)) + " - " + title);
  }
}