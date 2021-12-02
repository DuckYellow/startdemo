package com.example.startdemo.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuweihang@qbb.com
 * @date 2020-04-09 14:52
 */
public class Solution3 {
    public static boolean Find(int target, int[][] array) {
        //行数
        int a = array.length;
        //列数
        int b = array[0].length;
        if (a == 0 || b == 0) {
            return false;
        }
        for (int i = 0; i < a; i++) {
            if (array[i][0] == target) {
                return true;
            }
            if (array[i][b - 1] >= target) {
                for (int i1 = 0; i1 < b; i1++) {
                    if (array[i][i1] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        res.add(listNode.val);
        while (listNode.next != null) {
            listNode = listNode.next;
            res.add(listNode.val);
        }

        ArrayList<Integer> res2 = new ArrayList<>(res.size());
        for (int i = res.size() - 1; i > 0; i--) {
            res2.add(res.get(i));
        }
        return res2;
    }

    Stack<Integer> stack1 = new Stack<Integer>();

    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() <= 0) {
            while (stack1.size() != 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return array[array.length - 1];
    }

    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            //降坡
            if (nums[i] > nums[i + 1]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == nums.length - 1) {
                        i = j;
                        break;
                    } else if (nums[j + 1] > nums[j]) {
                        i = j - 1;
                        break;
                    } else {
                        res--;
                    }
                }
            }
            //升破
            else if (nums[i] < nums[i + 1]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == nums.length - 1) {
                        i = j;
                        break;
                    } else if (nums[j + 1] < nums[j]) {
                        i = j - 1;
                        break;
                    } else {
                        res--;
                    }
                }
            } else {
                res--;
            }
        }
        return res;
    }

    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int x = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
            x = Math.min(Math.abs(nums[i]), Math.abs(x));
            if (nums[i] < 0) {
                x = -x;
            }
            sum = sum + nums[i];
        }

        if (k % 2 > 0) {
            sum = sum - 2 * x;
        }
        return sum;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] cha = new int[gas.length];

        int gasint = 0;
        int costint = 0;
        for (int i = 0; i < gas.length; i++) {
            cha[i] = gas[i] - cost[i];
            gasint += gas[i];
            costint += cost[i];
        }
        if (gasint < costint) {
            return -1;
        }
        if (gas.length < 2) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < cha.length; i++) {
            if (cha[i] <= 0) {
                continue;
            }
            if (i == cha.length - 1) {
                return cha.length - 1;
            }
            index = i;
            int sum1 = cha[i];
            int j = i + 1;
            while (j != i) {
                sum1 += cha[j];
                if (sum1 < 0) {
                    break;
                }
                j = (j + 1) % cha.length;
            }
            if (sum1 >= 0) {
                return index;
            }
        }
        return -1;
    }

    public static boolean lemonadeChange(int[] bills) {
        int f1 = 0;
        int f2 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                f1++;
            } else if (bill == 10) {
                if (f1 >= 1) {
                    f1--;
                    f2++;
                } else {
                    return false;
                }
            } else if (bill == 20) {
                //找一张10快 一张5快
                if (f2 >= 1 && f1 >= 1) {
                    f2--;
                    f1--;
                } else if (f1 >= 3) {
                    f1 -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;

    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0] && person1[0] > person2[0]) {
                    return 1;
                } else if (person1[0] == person2[0]
                        && person1[1] != person2[1]
                        && person1[1] > person2[1]) {
                    return 1;
                }
                return -1;
            }
        });

        int num = 0;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            if (max > points[i][1]) {
                max = points[i][1];
            }
            if (max >= points[i][0]) {
                continue;
            }
            num++;
            max = points[i][1];
        }
        return num;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0] && person1[0] > person2[0]) {
                    return 1;
                } else if (person1[0] == person2[0]
                        && person1[1] != person2[1]
                        && person1[1] > person2[1]) {
                    return 1;
                }
                return -1;
            }
        });

        int num = 0;
        long max = Long.MAX_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            if (max > points[i][1]) {
                max = points[i][1];
            }
            if (max >= points[i][0]) {
                continue;
            }
            num++;
            max = points[i][1];
        }
        return num;

    }

    public static void main(String[] args) {
        int[][] array1 = {{1, 2}, {4, 5}, {1, 5}};
        int[] array2 = new int[]{4, 4, 1, 5, 1};
        System.out.println(findMinArrowShots(array1));
        System.out.println();

    }
}
