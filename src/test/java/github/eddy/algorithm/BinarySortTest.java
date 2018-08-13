package github.eddy.algorithm;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class BinarySortTest {

  @Test
  public void sort() {
    Integer[] array = new Integer[]{1, 3, 20, 4, 53, 16, 7, 48, 9};
    Assert.assertEquals(
        "[1, 3, 4, 7, 9, 16, 20, 48, 53]",
        Arrays.toString(BinarySort.sort_loop(array))
    );
  }
}