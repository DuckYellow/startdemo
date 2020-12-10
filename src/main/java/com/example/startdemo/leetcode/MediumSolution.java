package com.example.startdemo.leetcode;

import com.btime.util.StringUtil;
import lombok.val;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class MediumSolution {
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

    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == target) {
                end = i;
                if (start == -1) {
                    start = i;
                }
            }
        }
        return new int[]{start, end};
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        if (l1 != null) {
            res.val = res.val + l1.val;
        }
        if (l2 != null) {
            res.val = res.val + l2.val;
        }
        while (res.val >= 10) {
            res.val = res.val - 10;
            if (l1.next != null) {
                l1.next.val = l1.next.val + 1;
            } else {
                l1.next = new ListNode(1);
            }
        }
        res.next = addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next);
        return res;
    }

    public static String convert(String s, int numRows) {
        if (s.length() <= 2 || numRows < 2) {
            return s;
        }
        int xunhaun = numRows * 2 - 2;
        Map<Integer, List<String>> sMap = new HashMap<>();
        String[] strings = s.split("");
        for (int i = 0; i < strings.length; i++) {
            int index = i % xunhaun;
            if (!sMap.containsKey(index)) {
                sMap.put(index, new ArrayList<>());
            }
            if (index >= numRows) {
                int newIndex = numRows * 2 - 2 - index;
                sMap.get(newIndex).add(strings[i]);
            } else {
                sMap.get(index).add(strings[i]);
            }

        }
        StringBuffer res = new StringBuffer();
        for (Integer i = 0; i < numRows; i++) {
            if (sMap.get(i) == null) {
                continue;
            }
            for (String s1 : sMap.get(i)) {
                res.append(s1);
            }
        }
        return res.toString();
    }

    public static int myAtoi(String s) {

        if (s == null || s.length() < 1) {
            return 0;
        }
        s = s.trim();
        if (s == null || s.length() < 1) {
            return 0;
        }
        int flag = 1;
        int i = 0;
        //判断符号
        if (s.charAt(i) == '-') {
            flag = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }
        long result = 0;
        //字符转换，判断是否超出整数范围
        while (s.length() > i && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            result = result * 10 + (s.charAt(i) - '0');
            if (result * flag > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (result * flag < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int) result * flag;
    }

    public static int maxArea(int[] height) {
        Integer maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                Integer min = Math.min(height[i], height[j]);
                Integer area = min * (j - i);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static int maxAreaV2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }

    static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static int threeSumClosest(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        if (target < 0) {
            res = Integer.MIN_VALUE;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int i1 = i + 1; i1 < nums.length - 1; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                    int num = nums[i] + nums[i1] + nums[i2];
                    if (Math.abs(target - num) < Math.abs(target - res)) {
                        res = num;
                    }
                }
            }
        }
        return res;
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> combinations = new ArrayList<>();

        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                int four = n - 1;
                for (int third = second + 1; third < n; third++) {
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }
                    while (third < four && nums[first] + nums[second] + nums[third] + nums[four] > target) {
                        --four;
                    }

                    if (third == four) {
                        break;
                    }

                    if (nums[first] + nums[second] + nums[third] + nums[four] == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        list.add(nums[four]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        if (n == 0) {
            return combinations;
        }
        if (n==1){
            return Arrays.asList("()");
        }
        for (int i = 0; i < n; i++) {
            combinations = addCombinations(combinations, i, n);
        }

        return combinations;
    }

    private static boolean validCombinations(String combinations) {
        int balance = 0;

        for (char c : combinations.toCharArray()) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    private static List<String> addCombinations(List<String> combinations, int index, int length) {
        if (index >= length) {
            return combinations;
        }
        if (index == 0) {
            combinations.add("((");
            combinations.add("()");
            return combinations;
        }
        List<String> temp = new ArrayList<>();
        for (String combination : combinations) {
            String temp1 = combination + "((";
            String temp2 = combination + "()";
            String temp3 = combination + "))";
            String temp4 = combination + ")(";
            if (index != length-1) {
                temp.add(temp1);
                temp.add(temp2);
                temp.add(temp3);
                temp.add(temp4);
            } else {
                if (validCombinations(temp1)) {
                    temp.add(temp1);
                }
                if (validCombinations(temp2)) {
                    temp.add(temp2);
                }

                if (validCombinations(temp3)) {
                    temp.add(temp3);
                }
                if (validCombinations(temp4)) {
                    temp.add(temp4);
                }
            }
        }
        return temp;
    }


    public static void main(String[] args) {
        System.out.println(generateParenthesis(3).toString());
    }


}
