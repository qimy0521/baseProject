package com.gcx.api;


import java.util.HashMap;
import java.util.Map;

public class Test3 {
    public static void main(String[] args) {
        String s=" ";
        char[] chars = s.toCharArray();
        //存储长度
        int length=0;
        //HashSet
        Map<Character,Integer> map=new HashMap<>();
        //游标
        int j=0;
        for(int i=0;i< chars.length;i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                j=i-1;
            }
            length =Math.max(j - map.get(c),length) ;
            map.put(c,j);
            j++;
        }
        System.out.println(length);
    }
}
