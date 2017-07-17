package github.eddy.common;

import java.util.ArrayList;
import java.util.List;

public class PrintUtil {

  public static String printArray(int[] arr) {
    List<String> list = new ArrayList<>();
    for (int a : arr) {
      list.add(String.valueOf(a));
    }
    return String.join(",", list);
  }
}
