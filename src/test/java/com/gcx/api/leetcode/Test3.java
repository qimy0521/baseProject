package com.gcx.api.leetcode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 求一个字符串中不重复的字符串最长的长度
 */
public class Test3 {
    public static void main(String[] args) {
        String s="abcdefa";
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int leftIndex = 0;
        for (int j = 0; j < chars.length; j++) {
            for (int innerIndex = leftIndex; innerIndex < j; innerIndex++) {
                if (chars[innerIndex] == chars[j]) {
                    maxLength = Math.max(maxLength, j - leftIndex);
                    leftIndex = innerIndex + 1;
                    break;
                }
            }
        }
        System.out.println(Math.max(chars.length-leftIndex,maxLength));
    }
}
