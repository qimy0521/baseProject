package com.gcx.api.leetcode;

import org.elasticsearch.index.mapper.SourceToParse;

/**
 * @auther : root
 * @date :  2018/11/12 16:45
 * @description :
 */
public class Test {


    public static void main(String[] args) {
        Long maxId=700000L;
        maxId = maxId+60000;
        Long minId=1L;

        for(Long i=minId;i<=maxId;){
            System.out.println(i);

            i=i+50000;
        }
    }
}
