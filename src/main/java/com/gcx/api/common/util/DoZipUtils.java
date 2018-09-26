package com.gcx.api.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DoZipUtils {
    public static void main(String[] args) {
        DoZipUtils a=new DoZipUtils();
		a.doZipDir(new File("G:\\FTP\\20180115\\67AC16547C0BFBC948257FE600293F7A"), "aaa.zip");
	}
	private static Logger log = LogManager.getLogger(DoZipUtils.class);
    private ZipOutputStream zipOut; //压缩Zip
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private FileInputStream fileIn;
    private ZipEntry zipEntry;
    private byte[]   buf;
    private int      readedBytes;

    /**
     * 生成文件压缩包
     * @param attachList
     * @param zipDir
     * @param fileName
     * @return
     */
	public String doZipDir(File zipDir, String fileName){
		
        String zipFileName = zipDir.getPath()+"\\"+fileName;//压缩后生成的zip文件名 
        try{
        	this.fos=new FileOutputStream(zipFileName);
        	this.bos=new BufferedOutputStream(fos);
            this.zipOut = new ZipOutputStream(bos); 
            zipOut.setEncoding("gbk");//设置压缩文件的编码的格式
            List<File> files=new ArrayList<File>();
//    	    for (int i = 0; i < attachList.size(); i++) {
//    	    	// 新建File对象，过滤掉不需要打包的附件
//    	    	String attachBody = attachList.get(i).getAttachBody();
//    	    	if(null!=attachBody){
//    	    		if(attachBody.equals("0")||attachBody.equals("1")||attachBody.equals("2")){//说明该附件需要打包
//    	    			files.add(new File(attachList.get(i).getAttachLocalPath()+attachList.get(i).getAttachName()));
//        	    	}else{
//        	    		log.info("PAPER FileName===={}",attachList.get(i).getAttachName());
//        	    	}
//    	    	}else{
//    	    		log.info("PAPER FileName===={}",attachList.get(i).getAttachName());
//    	    	}
//			}
            handleDir(zipDir,this.zipOut,zipFileName,files); 
            return zipFileName;
        }catch(IOException ioe){
            ioe.printStackTrace();
            return "fail";
        }finally{
    		try {
    			this.zipOut.close();
    			this.bos.close();
    			this.fos.close();
    			if(null !=this.fileIn){
    				this.fileIn.close();	
    			}
			} catch (IOException e) {
				this.fos=null;
				this.bos=null;
				this.zipOut=null;
				this.fileIn=null;
				e.printStackTrace();
			}
        }
	}
	/**
	 * 由doZip调用,递归完成目录文件读取 
	 * @param dir
	 * @param zipOut
	 * @throws IOException
	 */
    private void handleDir(File dir,ZipOutputStream zipOut,String zipFileName, List<File> files)throws IOException{
            //如果目录为空,则单独创建空的压缩包
            if(files.size() == 0){
                //ZipEntry的isDirectory()方法中,目录以"/"结尾. 
                this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/")); 
                this.zipOut.closeEntry(); 
            }else{ 
                for(File fileName : files){ //如果目录不为空,则分别处理目录和文件.
                    if(fileName.isDirectory()){
                    	//继续跳进目录中读取文件
                        handleDir(fileName , this.zipOut,zipFileName,files); 
                    }else{
                		 fileIn = new FileInputStream(fileName);
                		 zipEntry = new ZipEntry(fileName.getName());
                         this.zipOut.putNextEntry(zipEntry);
                         buf=new byte[1024*10];
                         while((this.readedBytes = fileIn.read(buf))>0){ 
                             this.zipOut.write(buf,0,this.readedBytes); 
                         }
                         this.zipOut.closeEntry(); 
                    } 
                } 
            } 
		}
    }