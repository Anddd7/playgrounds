package github.eddy.stepbystep;

import github.eddy.common.TimeUtil;
import org.junit.Test;

public class LoopAndRecursionTest {

  LoopAndRecursion test = new LoopAndRecursion();

  @Test
  public void calculateTest() throws Exception {
    TimeUtil.execute("calculateWithMockStack the sum of 1-n",
        () -> test.calculateByLoop(10000),
        () -> test.calculateByRecursion(10000));
  }

  @Test
  public void findTest() throws Exception {
    Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    Integer value = 5;

    TimeUtil.execute("find element",
        () -> test.findByLoop(array, value),
        () -> test.findByRecursion(array, value));
  }

  @Test
  public void mockRecursionProcessTest() throws Exception {
    test.calculateWithMockStack(10);
  }
}