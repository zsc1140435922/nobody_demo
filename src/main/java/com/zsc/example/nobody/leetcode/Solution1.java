//package com.zsc.example.nobody.leetcode;
//
//import sun.lwawt.macosx.CSystemTray;
//
///**
// * @program: nobody_demo
// * @description: 11. 盛最多水的容器
// * @author: zhangsc
// * @create: 2022-08-17 13:42
// * @Copyright: HOSE合思｜易快报
// **/
//public class Solution1 {
//    public static void main(String[] args) {
//
//
//        int[] height = {1,8,6,2,5,4,8,3,7};
//        System.out.println(maxArea(height));
//    }
//
//    public static int maxArea(int[] height) {
//        if (height.length == 0){
//            return 0;
//        }
//        int max = 0;
//        int l=0,r=height.length-1;
//        while(l < r){
//            int sum = (r-l) * Math.min(height[l], height[r]);
//            int minH = Math.min(height[l], height[r]);
//            max = Math.max(sum, max);
//            while (height[l] <= minH && l < r) {
//                l++;
//            }
//            while (height[r] <= minH && l < r) {
//                r--;
//            }
//
//        }
//
//        return max;
//    }
//}
