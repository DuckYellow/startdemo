package com.example.startdemo.leetcode;

import org.springframework.util.CollectionUtils;

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

    public boolean checkRecord(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A')
                count++;
        }
        return count < 2 && s.indexOf("LLL") < 0;
    }

    public String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int[][] res = new int[r][c];
        if (nums.length == 0 || r * c != nums.length * nums[0].length)
            return nums;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                queue.add(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = queue.remove();
            }
        }
        return res;
    }

    public int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        int max = candies.length / 2;
        Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            if (set.size() >= max) {
                return max;
            }
            set.add(candy);
        }
        return set.size();
    }

    public static int findUnsortedSubarray(int[] nums) {
        int[] b = nums.clone();
        Arrays.sort(nums);
        int start = nums.length;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != b[i]) {
                start = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != b[i]) {
                end = i;
                break;
            }
        }
        if (end > start) {
            return end - start + 1;
        }
        return 0;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> a = Arrays.asList(list1);
        List<String> b = Arrays.asList(list2);
        String c = a.stream().filter(x -> b.contains(x)).findFirst().get();
        return new String[]{c};
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0) {
                if (flowerbed.length > 1) {
                    if (flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                        n--;
                        flowerbed[i] = 1;
                    }
                } else {
                    if (flowerbed[i] == 0) {
                        n--;
                        flowerbed[i] = 1;
                    }
                }

            } else if (i == flowerbed.length - 1) {
                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            } else {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
        }
        return n <= 0;
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    public double findMaxAverage(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];
        double res = sum[k - 1] * 1.0 / k;
        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return res;
    }


    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[i + 1]) {
                res[0] = nums[i];
            }
            if (nums[i] != nums[i + 1]) {
                res[1] = i + 1;
            }
        }
        return res;
    }

    public static boolean judgeCircle(String moves) {
        int ud = 0;
        int rl = 0;
        for (char c : moves.toCharArray()) {
            if ('U' == c) {
                ud++;
            } else if ('D' == c) {
                ud--;
            } else if ('R' == c) {
                rl++;
            } else if ('C' == c) {
                rl--;
            }
        }
        return ud == 0 && rl == 0;
    }

    public static int findLengthOfLCIS(int[] nums) {
        int res = 0;
        if (nums.length == 0) {
            return res;
        }
        int max = 1;
        int before = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == 0) {
                res++;
            } else {
                if (num > before) {
                    res++;
                } else {
                    if (res > max) {
                        max = res;
                    }
                    res = 1;
                }
            }
            before = num;
        }
        return Math.max(max, res);
    }

    public static int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();
        for (String op : ops) {
            switch (op) {
                case "+":
                    Integer a = list.get(list.size() - 1);
                    Integer b = list.get(list.size() - 2);
                    list.add(a + b);
                    break;
                case "C":
                    list.remove(list.size() - 1);
                    break;
                case "D":
                    Integer c = list.get(list.size() - 1);
                    list.add(c * 2);
                    break;
                default:
                    list.add(Integer.valueOf(op));
            }
        }

        Integer res = 0;
        for (Integer integer : list) {
            res += integer;
        }
        return res;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public static int getImportance(List<Employee> employees, int id) {

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.id, employee);
        }
        return getImportance(employeeMap, id);
    }

    private static int getImportance(Map<Integer, Employee> employeeMap, int id) {
        if (employeeMap.get(id) == null) {
            return 0;
        }
        int r = employeeMap.get(id).importance;
        if (employeeMap.get(id).subordinates == null || employeeMap.get(id).subordinates.isEmpty()) {
            return r;
        }
        for (Integer id2 : employeeMap.get(id).subordinates) {
            r += getImportance(employeeMap, id2);
        }
        return r;
    }

    public static boolean hasAlternatingBits(int n) {
        String binary = Integer.toBinaryString(n);
        char a = 2;
        for (char c : binary.toCharArray()) {
            if (a != c) {
                a = c;
            } else {
                return false;
            }
        }
        return true;
    }

    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);

            if (!indexMap.containsKey(num)) {
                indexMap.put(num, new ArrayList<>());
            }
            indexMap.get(num).add(i);
        }

        int num = -1;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue() > num) {
                num = integerIntegerEntry.getValue();
            }
        }
        List<Integer> keyList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue() == num) {
                keyList.add(integerIntegerEntry.getKey());
            }
        }

        int returnA = Integer.MAX_VALUE;
        for (Integer key : keyList) {
            List<Integer> index = indexMap.get(key);
            int res = index.get(index.size() - 1) - index.get(0) + 1;
            returnA = Math.min(returnA, res);
        }

        return returnA;
    }

    static class KthLargest {
        private int big;
        private List<Integer> list;

        public KthLargest(int k, int[] nums) {
            big = k;
            list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        }

        public int add(int val) {
            List<Integer> b = new ArrayList<>(list);
            b.add(val);
            b.sort((o1, o2) -> {
                if (o1 > o2) {
                    return 1;
                } else if (o1.equals(o2)) {
                    return 0;
                }
                return -1;
            });
            return b.get(big - 1);
        }
    }

    public int search(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) {
                return pivot;
            }
            if (target < nums[pivot]) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return -1;
    }

    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public String longestWord(String[] words) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.length())) {
                map.put(words.length, new ArrayList<>());
            }
            map.get(word.length()).add(word);
        }
        List<String> startList = map.get(1);
        if (CollectionUtils.isEmpty(startList)) {
            return null;
        }

        for (String s : startList) {
            int size = s.length();
            List<String> list = map.get(size);
        }
        return null;

    }

    static {
        int x = 5;
    }

    public static void myMethod() {
        y = x++ + ++x;
    }

    static int x, y;

    public static void main(String args[]) {
        x--;
        myMethod();
        System.out.println(x + y + ++x);
    }

    private static void calTable() {
        System.out.println(18002130 % 8);
        System.out.println(18002130 % 512);
        System.out.println(9244746 % 8);
        System.out.println(9244746 % 512);

        System.out.println(18002458 % 8);
        System.out.println(18002458 % 512);

        System.out.println(18002298 % 8);
        System.out.println(18002298 % 512);

        System.out.println(2375 % 8);
        System.out.println(2375 % 512);

        System.out.println(515970844 % 8);
        System.out.println(515970844 % 512);
    }
}
