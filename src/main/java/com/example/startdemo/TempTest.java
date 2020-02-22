package com.example.startdemo;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuweihang@qbb.com
 * @date 2019-08-13 20:41
 */
public class TempTest {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode lastNode = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }

            lastNode = lastNode.next;
        }
        lastNode.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }


    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();

        while (head.next != null) {
            head = head.next;
            if (set.contains(head.val)) {
                return true;
            }
            set.add(head.val);
        }
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    public int trailingZeroes(int n) {
        return 0;
    }

    public int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;

        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }

            if (count > majorityCount) {
                return num;
            }

        }

        return -1;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        HashSet<Integer> set = new HashSet();
        for (int number : numbers) {
            set.add(number);
        }

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (set.contains(target - number)) {
                res[0] = i + 1;
                int b = target - number;
                for (int i1 = i + 1; i1 < numbers.length; i1++) {
                    if (b == numbers[i1]) {
                        res[1] = i1 + 1;
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }
}
