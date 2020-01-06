package com.gcx.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求一个字符串中不重复的字符串最长的长度
 */
public class Test3 {
    public static void main(String[] args) {
        String s="abcabcbb";
        char[] chars = s.toCharArray();
        //储存所有直到下一个重复的字符串的长度
        int length=0;
        //游标，内部循环
        int b=0;
        //储存所有的字符串，每次遇到重复的字符串，清空list，然后记录长度
        List<Character> temp =new ArrayList<>();
        for(int i=0;i< chars.length;i++){
            if(chars.length ==1){
                length=chars.length;
                break;
            }
            temp.add(chars[i]);
            for(int j=i+1; j < chars.length; j++){
                char c = chars[j];
                if(temp.contains(c)){
                    length=Math.max(b-i,length);
                    temp.clear();
                    i=j;
                    b=j;
                    break;
                }
                b++;
                temp.add(c);
            }
        }
        //取出最大的长度
        System.err.print(length);
    }
}
