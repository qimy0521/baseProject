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
        String s=" ";
        char[] chars = s.toCharArray();
        //储存所有直到下一个重复的字符串的长度
        int length=0;
        //储存所有的字符串，每次遇到重复的字符串，清空list，然后记录长度
        List<Character> temp =new ArrayList<>();
        for(int i=0;i< chars.length;i++){
            //处理空的字符串
            if(chars.length ==1){
                length=chars.length;
                break;
            }
            char c = chars[i];
            if(temp.contains(c)){
                //记录现有的长度
               if(length < temp.size()){
                   length=temp.size();
               }
               temp.clear();
            }
            temp.add(c);
        }
        //取出最大的长度
        System.out.print(length);
    }
}
