package github.eddy.leetcode.part1;

public class ZigZagConversion {

  /**
   * @thinking
   * mark char with  '0 1 2 ... n-1 n n-1 n-2 ... 2 1 0 1 2 ... ' index , the index is row which char should put in
   *
   * 1. create rows
   * 2. check string ,put char into row by law like '1232123'
   * 3. contact rows
   */
  public String convert(String s, int numRows) {
    if (numRows == 1 || s.length() <= 1) {
      return s;
    }

    StringBuilder[] temp = new StringBuilder[numRows];

    int nextQueueIndex = 0;
    int increase = 1;
    for (int i = 0; i < s.length(); i++) {
      if (temp[nextQueueIndex] == null) {
        temp[nextQueueIndex] = new StringBuilder();
      }
      temp[nextQueueIndex].append(s.charAt(i));

      if (nextQueueIndex == 0) {
        increase = 1;
      } else if (nextQueueIndex == numRows - 1) {
        increase = -1;
      }
      nextQueueIndex += increase;
    }

    for (int j = 1; j < numRows; j++) {
      if (temp[j] != null) {
        temp[0].append(temp[j]);
      }
    }
    return temp[0].toString();
  }

  public static void main(String[] a) {
    ZigZagConversion lab = new ZigZagConversion();
    System.out.println(lab.convert("AB", 3));
  }
}
