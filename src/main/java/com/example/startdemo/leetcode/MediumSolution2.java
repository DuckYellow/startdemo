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

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] > target) {
                if (i == 0) {
                    return false;
                }
                int ii = i - 1;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[ii][j] == target) {
                        return true;
                    }
                }
            }
            if (i == matrix.length - 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }


    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();

            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, t, ans);
        return ans;
    }

    public void dfs(int cur, int[] nums, List<Integer> t, List<List<Integer>> ans) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums, t, ans);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums, t, ans);
    }


    public static void main(String[] args) {

        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}
