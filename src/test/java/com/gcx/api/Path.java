package com.gcx.api;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: qimy
 * @Date: 2018/9/26 17:53
 * @Description:
 */
public class Path {
    public static void main(String[] args) throws IOException {
        Path a=new Path();
        String s = a.getClass().getClassLoader().getResource("").toString();
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
        System.out.println(s);
    }

}
