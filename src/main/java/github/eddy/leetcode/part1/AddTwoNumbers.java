package github.eddy.leetcode.part1;

import org.junit.Test;

public class AddTwoNumbers {

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    ListNode add(ListNode node) {
      next = node;
      return this;
    }
  }

  /**
   * 2017/07/13
   *
   * add one by one ;
   * if both a'next and b'next is null , exit ;
   * use ListNode(0) instead of null when another is not null ;
   *
   * put remainder of mod(10) into next sum
   * a + b + c(0) = n * 10 + m  ->  (m)->(n)+
   * a1 + b1 + c1(n) = n1 * 10 + m1  -> (m1)->(n1)+
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = new ListNode(0);

    ListNode current = result;
    while (true) {
      //补0
      if (l1 == null) {
        l1 = new ListNode(0);
      }
      if (l2 == null) {
        l2 = new ListNode(0);
      }

      //计算 赋值
      int val = (current.val + l1.val + l2.val) % 10;
      int add = (current.val + l1.val + l2.val) / 10;
      current.val = val;
      current.next = new ListNode(add);

      //下一轮
      l1 = l1.next;
      l2 = l2.next;
      if (l1 == null && l2 == null) {
        //如果停止 而最后一位是0 则直接删除节点
        if (current.next.val == 0) {
          current.next = null;
        }
        break;
      }
      current = current.next;
    }
    return result;
  }

  /**
   * Thinking is same with me ,but it use less new/Object
   */
  public ListNode _addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = new ListNode(0);
    ListNode current = result;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int x = (l1 != null) ? l1.val : 0;
      int y = (l2 != null) ? l2.val : 0;
      int sum = carry + x + y;
      carry = sum / 10;
      current.next = new ListNode(sum % 10);
      current = current.next;
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    if (carry > 0) {
      current.next = new ListNode(carry);
    }
    /**
     *  !!! the result is a Link started with 0 ,it always create next and put sum into next val
     *  so it avoid [set val -> create next with carry] to append a [0] in the end
     */
    return result.next;
  }

  @Test
  public void test() {
    ListNode l1 = new ListNode(3);
    ListNode l2 = new ListNode(4).add(l1);
    ListNode l3 = new ListNode(2).add(l2);

    ListNode r1 = new ListNode(4);
    ListNode r2 = new ListNode(6).add(r1);
    ListNode r3 = new ListNode(5).add(r2);

    addTwoNumbers(l3, r3);
  }
}
