package com.github.anddd7.leetcode.medium;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.anddd7.leetcode.medium.RemoveNthFromEnd.ListNode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RemoveNthFromEndTest {

  public static ListNode data(String input) {
    if (input == null || input.isEmpty()) {
      return null;
    }
    String[] strs = input.split("->");
    if (strs.length == 1) {
      return new ListNode(Integer.valueOf(strs[0]));
    }

    List<String> values = Arrays.asList(strs);
    Collections.reverse(values);
    return values.stream()
        .map(s -> new ListNode(Integer.valueOf(s)))
        .reduce((a, b) -> {
          b.next = a;
          return b;
        })
        .get();
  }

  public static String string(ListNode head) {
    StringBuilder sb = new StringBuilder(String.valueOf(head.val));
    ListNode current = head.next;
    while (current != null) {
      sb.append("->").append(current.val);
      current = current.next;
    }
    return sb.toString();
  }

  private RemoveNthFromEnd instance = new RemoveNthFromEnd();

  @Test
  void case1() {
    assertThat(
        string(
            instance.removeNthFromEnd(data("1->2->3->4->5"), 2)
        )
    ).isEqualTo("1->2->3->5");
  }
}