package com.example.startdemo.leetcode;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.*;

/**
 * @author xuweihang@qbb.com
 * @date 2020-04-09 14:52
 */
public class Solution2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // Iterate over each of the elements in the original array
        for (int i = 0; i < nums.length; i++) {

            // Treat the value as the new index
            int newIndex = Math.abs(nums[i]) - 1;

            // Check the magnitude of value at this new index
            // If the magnitude is positive, make it negative
            // thus indicating that the number nums[i] has
            // appeared or has been visited.
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that have positive magnitude in the array
        for (int i = 1; i <= nums.length; i++) {

            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }
        return result;
    }

    public static int minMoves(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            moves += num;
            min = Math.min(min, num);
        }
        return moves - min * nums.length;
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gIndex = 0;
        for (int i : s) {
            for (int i1 = gIndex; i1 < g.length; i1++) {
                if (i >= g[i1]) {
                    gIndex = i1 + 1;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1)
                distance += 1;
            xor = xor >> 1;
        }
        return distance;
    }

    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int lines = 4;
                    //判断这个岛旁边连接了多少个岛
                    if (i > 0 && grid[i - 1][j] == 1) lines--;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) lines--;
                    if (j > 0 && grid[i][j - 1] == 1) lines--;
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1) lines--;
                    sum += lines;
                }
            }
        }
        return sum;
    }

    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }

    public static boolean detectCapitalUse(String word) {
        String wordUpperCase = word.toUpperCase();
        if (wordUpperCase.equals(word)) {
            return true;
        }

        String wordLowerCase = word.toLowerCase();
        if (wordLowerCase.equals(word)) {
            return true;
        }
        char[] wardChar = word.toCharArray();

        if (wardChar.length <= 1) {
            return true;
        }
        for (int i = 0; i < wardChar.length; i++) {
            if (i == 0) {
                if (wardChar[i] > 90 || wardChar[i] < 65) {
                    return false;
                }
            } else {
                if (wardChar[i] < 97 || wardChar[i] > 122) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int findLUSlength(String a, String b) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : new String[]{a, b}) {
            for (int i = 0; i < (1 << s.length()); i++) {
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0)
                        t += s.charAt(j);
                }
                if (map.containsKey(t))
                    map.put(t, map.get(t) + 1);
                else
                    map.put(t, 1);
            }
        }
        int res = -1;
        for (String s : map.keySet()) {
            if (map.get(s) == 1)
                res = Math.max(res, s.length());
        }
        return res;
    }

    public static int findPairs(int[] nums, int k) {

        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        if (k < 0) {
            return count;
        }
        //首先想的是map键存储的值是什么，数组元素
        //value存储的是什么  是数组元素在数组中出现的次数。
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        //遍历的逻辑分成两种情况k=0时，出现大于一次时 就是一种结果。
        //k!=0时，map中包含i+k的key时就是一种情况。
        for (int i : map.keySet()) {
            if (k == 0) {
                if (map.get(i) > 1) {
                    count++;
                }
            } else if (k != 0) {
                if (map.containsKey(i + k)) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {

        //重量
        Float a = 1.00f;
        String aStr = String.valueOf(a.intValue());

        Integer ai = Integer.valueOf(aStr);
        System.out.println(ai);
    }
}
