package com.example.startdemo.leetcode;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2020-03-04 21:44
 */
public class Solution {

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

    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }


    public static int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

    public static int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }
            return i;
        }
        if (set.size() == nums.length) {
            return nums.length;
        }
        return 0;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.isEmpty()) {
            return true;
        }
        if (magazine == null || magazine.isEmpty()) {
            return false;
        }
        char[] a = ransomNote.toCharArray();
        char[] b = magazine.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (char c : a) {
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }

        }
        for (char c : b) {
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) - 1);
                if (map.get(key).equals(0)) {
                    map.remove(key);
                }
            }
            if (map.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int firstUniqChar(String s) {
        char[] a = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (char c : a) {
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        for (int i = 0; i < a.length; i++) {
            String key = String.valueOf(a[i]);
            if (map.get(key).equals(1)) {
                return i;
            }
        }
        return -1;
    }

    public static String getHint(String secret, String guess) {
        if (secret.length() == 0 || guess.length() == 0) return "";
        int l1 = secret.length();
        int l2 = guess.length();
        int i = 0;
        int cnt1 = 0;
        int[] s = new int[10];//记录0~9个数
        int[] g = new int[10];
        while (i < l1 && i < l2) {
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;
            if (secret.charAt(i) == guess.charAt(i)) { //公牛
                cnt1++;
            }
            i++;
        }
        i = 0;
        int cnt2 = 0;
        while (i < 10) {
            cnt2 += Math.min(s[i], g[i]);
            i++;
        }
        cnt2 = cnt2 - cnt1;//减去完全猜对的公牛才是奶牛
        return cnt1 + "A" + cnt2 + "B";
    }

    public static char findTheDifference(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Map<String, Integer> map = new HashMap<>();
        for (char c : a) {
            map.put(String.valueOf(c), map.getOrDefault(String.valueOf(c), 0) + 1);
        }
        for (char c : b) {
            if (map.containsKey(String.valueOf(c))) {
                if (map.get(String.valueOf(c)) == 0) {
                    return c;
                }
                map.put(String.valueOf(c), map.get(String.valueOf(c)) - 1);
            } else {
                return c;
            }
        }
        return a[0];
    }


    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] sChar = s.toCharArray();
        int res = 0;
        //重复下标记录
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < sChar.length; i++) {
            String reS = String.valueOf(sChar[i]);
            if (set.contains(reS)) {
                if (set.size() > res) {
                    res = set.size();
                }
                set = new HashSet<>();
                i = map.get(reS);
            } else {
                set.add(reS);
                map.put(reS, i);
            }
        }

        if (set.size() > res) {
            res = set.size();
        }
        return res;
    }

    public static boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        if (t == null || t.isEmpty()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int sIndex = 0;

        for (char c : tChar) {
            if (c == sChar[sIndex]) {
                sIndex++;
                if (sIndex == sChar.length) {
                    return true;
                }
            }

        }
        return false;
    }

    public static int longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int res = 0;
        char[] sChar = s.toCharArray();
        HashMap<String, Integer> map = new HashMap<>(sChar.length);

        for (char c : sChar) {
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                if (value == 1) {
                    res += 2;
                    map.remove(key);
                }
            } else {
                map.put(key, 1);
            }
        }
        if (!map.isEmpty()) {
            res++;
        }
        return res;

    }

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean is3 = is3(i);
            boolean is5 = is5(i);

            if (is3 && is5) {
                res.add("FizzBuzz");
                continue;
            } else if (is3) {
                res.add("Fizz");
                continue;
            } else if (is5) {
                res.add("Buzz");
                continue;
            }

            res.add("" + i);
        }
        return res;
    }

    public static boolean is3(int n) {
        String s = String.valueOf(n);

        char[] sChar = s.toCharArray();
        int res = 0;
        for (char c : sChar) {
            res += Integer.valueOf(c);
        }
        if (res % 3 == 0) {
            return true;
        }
        return false;
    }

    public static boolean is5(int n) {
        String s = String.valueOf(n);

        char[] sChar = s.toCharArray();
        char end = sChar[sChar.length - 1];

        if (String.valueOf(end).equals("5") || String.valueOf(end).equals("0")) {
            return true;
        }
        return false;
    }

    public static int thirdMax(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        List<Integer> res = new ArrayList<>(nums.length);
        for (int num : nums) {
            res.add(num);
        }
        Collections.sort(res);
        Set<Integer> a = new HashSet<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            a.add(res.get(i));
            if (a.size() >= 3) {
                return res.get(i);
            }
        }
        return res.get(res.size() - 1);
    }

    public static void main(String[] args) {
        int[] shuzu = new int[4];

        shuzu[0] = 2;
        shuzu[1] = 2;
        shuzu[2] = 3;
        shuzu[3] = 1;
        System.out.println(thirdMax(shuzu));
    }
}
