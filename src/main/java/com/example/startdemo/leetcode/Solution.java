package com.example.startdemo.leetcode;

/**
 * @author xuweihang@qbb.com
 * @date 2020-03-04 21:44
 */
public class Solution {
    public static void main(String[] args) {

    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int countPrimes(int n) {

        if (n < 2)
            return 0;
        int count = 0;
        boolean[] nums = new boolean[n];

        for (int i = 2; i * i < n; i++) {
            if (!nums[i]) {
                for (int j = i * i; j < n; j += i) {
                    if (nums[j])
                        continue;
                    count++;
                    nums[j] = true;
                }
            }
        }
        return n - count - 2;
    }
}
