package com.example.startdemo.leetcode;

import org.springframework.util.StringUtils;

import java.util.*;

public class MediumSolution2 {
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

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    public int uniquePathsV2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    public static String simplifyPath(String path) {
        List<String> res = new ArrayList<>();
        if (path == null || path.length() == 0) {
            return "/";
        }
        String[] pathList = path.split("/");
        for (String singlePath : pathList) {
            if (singlePath == null || singlePath.length() <= 0) {
                continue;
            }
            switch (singlePath) {
                case ".":
                    break;
                case "..":
                    if (res.size() > 0) {
                        res.remove(res.size() - 1);
                    }
                    break;
                default:
                    res.add(singlePath);
            }
        }
        return "/" + String.join("/", res);
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        //竖着的
        int m = matrix.length;
        //恨着
        int n = matrix[0].length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (matrix[i][i1] == 0) {

                    for (int j = 0; j < n; j++) {
                        if (!map.containsKey(i)) {
                            map.put(i, new HashSet<>());
                        }
                        map.get(i).add(j);
                    }
                    for (int j = 0; j < m; j++) {
                        if (!map.containsKey(j)) {
                            map.put(j, new HashSet<>());
                        }
                        map.get(j).add(i1);
                    }
                }
            }
        }
        for (Map.Entry<Integer, Set<Integer>> integerSetEntry : map.entrySet()) {
            for (Integer integer : integerSetEntry.getValue()) {
                matrix[integerSetEntry.getKey()][integer] = 0;
            }
        }
        System.out.println("a");
    }

    public static void main(String[] args) {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 1;

        matrix[1][0] = 0;
        matrix[1][1] = 1;
        setZeroes(matrix);
    }
}
