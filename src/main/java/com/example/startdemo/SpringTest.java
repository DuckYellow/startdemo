package com.example.startdemo;


import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuweihang@qbb.com
 * @date 2019-05-18 21:23
 */
public class SpringTest {


    public int rob(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private boolean isPalindrome(String s) {
        if (null == s || s.length() < 2) {//如果字符串的长度小于2，直接返回true
            return true;
        }
        int size = s.length() - 1;//记载的总长度
        char left, right;//左右指针指向的字符
        for (int i = 0; i < s.length() - 1; i++) {//此处不使用size是因为size作为变量需要在下面进行实时修改
            if (!isvalid(s.charAt(i))) {//判断是否为需要判断的字符
                continue;
            }
            left = s.charAt(i);
            while (size >= 0 && !isvalid(s.charAt(size))) {//右角标大于0并且字符无效时，右指针左移
                size--;
            }
            right = s.charAt(size);
            if (!issame(left, right)) {//判断左右角标对应的字符是否相等
                return false;
            }
            size--;
        }
        return true;
    }

    private boolean issame(char left, char right) {
        if (left < 65 || right < 65) {//判断字符是否小于65
            if (left == right) {//小于65代表字符为数字，直接判断是否相等就可以了
                return true;
            }
        } else {
            if (left == right || Math.abs(left - right) == 32) {//字符大于等于65代表此处是字母，差值为32代表字母相等，一个是大写一个是小写
                return true;
            }
        }
        return false;
    }

    private boolean isvalid(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
