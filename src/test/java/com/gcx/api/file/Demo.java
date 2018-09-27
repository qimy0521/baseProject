package com.gcx.api.file;

import java.io.IOException;

/**
 * @Auther: qimy
 * @Date: 2018/9/27 15:25
 * @Description:
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        int bufferSize=1024*1024*10;//缓冲区大小

        FileSliceReadUtil fileSliceReadUtil=new FileSliceReadUtil(50, bufferSize, 100000, "UTF-8");
        fileSliceReadUtil.readFile("f:/bjTodoSync.log", new IHandle() {
            public void handle(String line) {
                System.out.println(line);
            }
        });
    }
}
