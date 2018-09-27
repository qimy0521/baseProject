package com.gcx.api;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: qimy
 * @Date: 2018/9/26 17:53
 * @Description:
 */
public class Path {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        System.out.println("读取开始");

        File inFile = new File("F://bjTodoSync.log");

        File outFile = new File("F://back.log");
        FileInputStream fis=new FileInputStream(inFile);
        FileOutputStream fos=new FileOutputStream(outFile);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer byteBuf=ByteBuffer.allocate(60000);
        byte[] array;

//        int bytes = channel.read(byteBuf);

        while (inChannel.read(byteBuf)!= -1) {
//            array = new byte[bytes];// 字节数组长度为已读取长度
            byteBuf.flip();

            outChannel.write(byteBuf);
//            byteBuf.get(array);// 从ByteBuffer中得到字节数组
            byteBuf.clear();
        }
        fis.close();
//        fos.close();
        long end = (System.currentTimeMillis()-start);

        System.out.println("读取完毕，耗时>>>>>>>>"+end+"ms");

    }

}
