package com.example.startdemo.leetcode;

import com.btime.util.StringUtil;
import lombok.val;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        if (n == 1) {
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
            if (index != length - 1) {
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

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            if (!res.contains(new ArrayList<>(path))) {
                res.add(new ArrayList<>(path));
            }

            return;
        }
        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
//            if (i !=0 && candidates[i] == candidates[i-1]){
//                continue;
//            }
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i + 1, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        Arrays.sort(nums);
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sort = new String(chars);
            if (!map.containsKey(sort)) {
                map.put(sort, new ArrayList<>());
            }
            map.get(sort).add(str);

        }
        res.addAll(map.values());
        return res;
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);

    }

    private static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] param = new int[4][2];
        param[0][0] = 1;
        param[0][1] = 3;
        param[1][0] = 2;
        param[1][1] = 6;
        param[2][0] = 8;
        param[2][1] = 10;
        param[3][0] = 15;
        param[3][1] = 18;
        System.out.println(merge(param));
    }


}
