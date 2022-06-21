package com.github.anddd7.datastructure;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * loop-find or map.get
 */
public class LoopAndMap {

  static public List<String> forFind(List<Province> provinces, List<City> cities) {
    return provinces.stream()
        .map(p ->
            p.name
                + "[" +
                cities.stream()
                    .filter(c -> c.provinceId == p.id)
                    .map(c -> c.name)
                    .collect(Collectors.joining(", "))
                + "]"
        )
        .collect(Collectors.toList());
  }

  static public List<String> mapFind(List<Province> provinces, List<City> cities) {
    Map<Integer, List<City>> citiesOfProvince = cities.stream().collect(Collectors.groupingBy(c -> c.provinceId));

    return provinces.stream()
        .map(p ->
            p.name
                + "[" +
                citiesOfProvince.getOrDefault(p.id, Collections.emptyList())
                    .stream()
                    .map(c -> c.name)
                    .collect(Collectors.joining(", "))
                + "]"
        )
        .collect(Collectors.toList());
  }


  static class Province {

    public int id;
    public String name;

    public Province(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }


  static class City {

    public int id;
    public int provinceId;
    public String name;

    public City(int id, int provinceId, String name) {
      this.id = id;
      this.provinceId = provinceId;
      this.name = name;
    }
  }
}
