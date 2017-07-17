import github.eddy.common.PrintUtil;
import java.util.Arrays;
import org.junit.Test;


public class ArrayTest {

  @Test
  public void test() {
    int[] a = new int[]{1, 2, 3, 4, 5};
    int[] b = new int[]{2, 3, 4, 5, 6};
    System.out.println(PrintUtil.printArray(Arrays.copyOfRange(a, 2, 5)));
  }


}
