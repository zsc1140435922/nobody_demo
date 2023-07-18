package com.zsc.example.nobody.leetcode;

import java.util.Stack;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-11-05 13:27
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 **/
public class Solution {
    public static int trap(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }

            stack.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
      // int[] height = {0,1,0,2,1,0,1,3,2,1,2,1}; //6
        int[] height = {1,8,6,2,5,4,8,3,7};
        //height = [4,2,0,3,2,5] //9
        System.out.println(trap(height));

    }
}