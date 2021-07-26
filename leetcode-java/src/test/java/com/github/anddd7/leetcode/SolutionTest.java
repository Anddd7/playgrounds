package com.github.anddd7.leetcode;

class SolutionTest {

    public static void main(String[] args) {
        final Solution.ListNode head = new Solution.ListNode(
                1
//                ,
//                new Solution.ListNode(
//                        2,
//                        new Solution.ListNode(
//                                3,
//                                new Solution.ListNode(4)
//                        )
//                )
        );
        Solution.INSTANCE.reorderList(head);
        System.out.println();
    }
}
