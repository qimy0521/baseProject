package com.gcx.api;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther : root
 * @date :  2018/12/3 16:50
 * @description :
 */
public class DateDemo {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat1.format(new Date());
        String format2 = simpleDateFormat2.format(new Date());
        System.out.println(format1);
        System.out.println(format2);
    }
}
