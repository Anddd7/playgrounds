import github.eddy.common.PrintUtil;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;


public class ArrayTest {

  int[] a;
  int[] b;

  @Before
  public void before() {
    a = new int[]{1, 2, 3, 4, 5};
    b = new int[]{2, 3, 4, 5, 6};
  }

  @Test
  public void copyOfRangeTest() {
    System.out.println(PrintUtil.printArray(Arrays.copyOfRange(a, 2, 5)));
  }

  @Test
  public void copyArrayTest() {
    int[] temp = new int[10];
    System.arraycopy(a, 0, temp, 0, a.length);
    System.out.println(PrintUtil.printArray(temp));

    temp = new int[10];
    System.arraycopy(a, 0, temp, 0, a.length - 1);
    System.out.println(PrintUtil.printArray(temp));

    temp = new int[10];
    System.arraycopy(a, 1, temp, 3, a.length - 1);
    System.out.println(PrintUtil.printArray(temp));

    temp = new int[10];
    int size = a.length;
    System.arraycopy(a, 0, temp, 0, 3);
    System.arraycopy(a, 4, temp, 3, --size - 3);
    System.out.println(PrintUtil.printArray(temp));
  }

  @Test
  public void multiArray() {
    int[][] table = new int[5][8];

    System.out.println(table.length);
    System.out.println(table[0].length);

  }

}
