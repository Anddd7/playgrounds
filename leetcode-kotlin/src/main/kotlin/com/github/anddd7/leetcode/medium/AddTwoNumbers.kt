package com.github.anddd7.leetcode.medium


/**
 * 给定两个链表(不等长), 对值做加法, 包含进位
 */
class AddTwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        return addNumber(0, l1, l2)
    }

    fun addNumber(carry: Int, l: ListNode?, r: ListNode?): ListNode? {
        if (l == null && r == null) {
            return if (carry > 0) ListNode(carry) else null
        }

        val l1 = l ?: r!!
        val r1 = if (l == null) ListNode(0) else r ?: ListNode(0)

        val total = carry + l1.`val` + r1.`val`
        val newValue = total % 10
        val newCarry = total / 10

        l1.`val` = newValue
        l1.next = addNumber(newCarry, l1.next, r1.next)
        return l1
    }

    inner class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
}
