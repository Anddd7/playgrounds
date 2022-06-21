package com.github.anddd7.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static final Solution INSTANCE = new Solution();

    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        char[] chars = s.toCharArray();
        // start index (exclude) of previous valid parentheses
        stack.push(-1);

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
