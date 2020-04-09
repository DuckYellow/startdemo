package com.example.startdemo.leetcode;

import java.util.*;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {

    }
}
