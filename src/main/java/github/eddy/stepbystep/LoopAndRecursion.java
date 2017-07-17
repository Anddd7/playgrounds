package github.eddy.stepbystep;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Loop And Recursion is used frequently.It's the first operator in Coding.
 */
class LoopAndRecursion {

  /**
   * Get result = 1 + 2 + .... + n
   */
  int calculateByLoop(int n) {
    if (n < 0) {
      return -1;
    }
    int count = 0;
    for (int i = 0; i <= n; i++) {
      count += i;
    }
    return count;
  }

  /**
   * Get result = 1 + 2 + .... + n by recursion
   */
  int calculateByRecursion(int n) {
    if (n < 0) {
      return -1;
    }
    /**
     * The most important point in Recursion : export condition
     */
    if (n == 0) {
      return 0;
    }
    return n + calculateByRecursion(n - 1);
  }

  /**
   * Search element
   */
  <T> int findByLoop(T[] array, T value) {
    if (null == array || 0 == array.length) {
      return -1;
    }
    for (int index = 0; index < array.length; index++) {
      if (value == array[index]) {
        return index;
      }
    }
    return -1;
  }

  /**
   * Search element by recursion
   */
  <T> int findByRecursion(T[] array, T value) {
    if (null == array || 0 == array.length) {
      return -1;
    }
    return findByRecursion(0, array, value);
  }

  private <T> int findByRecursion(int start, T[] array, T value) {
    if (start == array.length) {
      return -1;
    }
    if (array[start] == value) {
      return start;
    }
    return findByRecursion(start + 1, array, value);
  }


  /**
   * Recursion is still a function ,but it call itself until get 'export condition'
   *
   * call(1) -> call(2) -> ... -> call(n)
   * return call(1) <- return call(2) <- ... <- return call(n)
   *
   * the every call-step is stored in 'Program Stack' and wait for next part return.
   */
  int calculateWithMockStack(int n) {
    return calculateWithMockStack(new ArrayDeque<>(), n);
  }

  private int calculateWithMockStack(Deque<Integer> stack, int n) {
    while (n > 0) {
      System.out.println("not export ,push " + n + " into stack ,waiting .");
      stack.push(n);
      n--;
    }
    System.out.println("export");

    int count = 0;
    while (stack.size() > 0) {
      int returnValue = stack.pop();
      count += returnValue;
      System.out.println("return " + returnValue);
    }

    return count;
  }

}
