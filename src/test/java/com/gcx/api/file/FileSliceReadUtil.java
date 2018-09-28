package com.gcx.api.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: qimy
 * @Date: 2018/9/27 14:31
 * @Description: 文件随机读取
 */
public class FileSliceReadUtil {

    private int sliceSum;//分片大小

    private File file;

    private long fileLength;//文件总大小

    private RandomAccessFile rAccessFile;//文件随机流

    private int bufferSize=1024*1024;//缓冲区大小

    private byte[] readBuff;

    private long start;//开始位置

    private StartEndPair startEndPairs;

    private long sliceSize;//分片大小

    private String charset=null;//文件编码

    private IHandle handle;//回调

    private AtomicLong counter = new AtomicLong(0);


    public FileSliceReadUtil(int sliceSum, int bufferSize, long start, String charset) {
        this.sliceSum = sliceSum;
        this.bufferSize = bufferSize;
        this.start = start;
        this.charset = charset;
    }

    /**
     * 读取文件
     */
    public void readFile(String filepath,IHandle iHandle) throws IOException {
        this.file = new File(filepath);
        if(!this.file.exists())
            throw new IllegalArgumentException("文件不存在！");
        this.handle = iHandle;
        this.readBuff = new byte[bufferSize];

        this.fileLength=this.file.length();

        long everySize = this.fileLength / this.sliceSum;//先用除来计算

        this.rAccessFile = new RandomAccessFile(this.file,"r");

        calculateStartEnd(this.start, everySize);

        rAccessFile.seek(startEndPairs.start);
        this.sliceSize = startEndPairs.end-startEndPairs.start+1;

        MappedByteBuffer mapBuffer = rAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,startEndPairs.start,sliceSize);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for(int offset=0;offset<sliceSize;offset+=bufferSize){
            int readLength;
            if(offset+bufferSize<=sliceSize){
                readLength = bufferSize;
            }else{
                readLength = (int) (sliceSize-offset);
            }
            mapBuffer.get(readBuff, 0, readLength);

            for(int i=0;i<readLength;i++){
                byte tmp = readBuff[i];
                if(tmp=='\n' || tmp=='\r'){//遇到换行，重置，打印
                    handle(bos.toByteArray());
                    bos.reset();
                }else{
                    bos.write(tmp);
                }
            }
        }
    }


    /**
     * 计算文件起始位置
     * @param start 开始位置
     * @param size 分片大小
     * @throws IOException
     */
    private void calculateStartEnd(long start,long size) throws IOException{
        if(start>fileLength-1){
            return;
        }
        startEndPairs = new StartEndPair();
        startEndPairs.start=start;
        long endPosition = start+size-1;
        if(endPosition>=fileLength-1){
            startEndPairs.end=fileLength-1;
            return;
        }
        startEndPairs.end=endPosition;
    }


    private static class StartEndPair{
        public long start;
        public long end;
    }

    private void handle(byte[] bytes) throws UnsupportedEncodingException {
        String line = null;
        if(this.charset==null){
            line = new String(bytes);
        }else{
            line = new String(bytes,charset);
        }
        if(line!=null && !"".equals(line)){
            this.handle.handle(line);
            counter.incrementAndGet();
        }
    }

}
