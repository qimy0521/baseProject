package com.gcx.api.file;

import java.io.IOException;

/**
 * @Auther: qimy
 * @Date: 2018/9/27 15:25
 * @Description:
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        int bufferSize=1024*1024;//缓冲区大小
        FileSliceReadUtil fileSliceReadUtil=new FileSliceReadUtil(1, bufferSize, 0, "UTF-8");
        fileSliceReadUtil.readFile("f:/test.log", line -> System.out.println(">>>>>>>>"+line));
    }
}
