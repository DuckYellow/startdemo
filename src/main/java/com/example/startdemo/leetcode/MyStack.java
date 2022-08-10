package com.example.startdemo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xuweihang@qbb.com
 * @date 2020-03-29 15:41
 */
public class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }


    public int top() {
        return queue1.peek();
    }


    public boolean empty() {
        return queue1.isEmpty();
    }
}
