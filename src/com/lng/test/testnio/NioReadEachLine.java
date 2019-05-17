 package com.lng.test.testnio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;


public class NioReadEachLine {

    public static void main(String[] args) throws Exception {
    	
    	long s = System.currentTimeMillis();
		Date startDate = new Date();
    	
        //指定读取文件所在位置
        File file = new File("C:\\工作资料\\其他\\数据库\\数据档案csv.txt");
        FileChannel fileChannel = new RandomAccessFile(file,"r").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
        
        //使用temp字节数组用于存储不完整的行的内容
        int lineCount = 0;
        byte[] temp = new byte[0];
        int readCount = 0;
        while(fileChannel.read(byteBuffer) != -1) {
        	
        	readCount++;
        	
            byte[] bs = new byte[byteBuffer.position()];
            byteBuffer.flip();
            byteBuffer.get(bs);
            byteBuffer.clear();
            int startNum=0;
    		int LF = 10;//换行符
			int CR = 13;//回车符
            //判断是否出现了换行符，注意这要区分LF-\n,CR-\r,CRLF-\r\n,这里判断\n
            boolean isNewLine = false;
            for(int i=0;i < bs.length;i++) {
                if(bs[i] == LF) {
                    isNewLine = true;
                    startNum = i;
                    lineCount++;
                }
            }

            if(isNewLine) {
                //如果出现了换行符，将temp中的内容与换行符之前的内容拼接
                byte[] toTemp = new byte[temp.length + startNum];
                System.arraycopy(temp, 0, toTemp, 0, temp.length);
                System.arraycopy(bs, 0 , toTemp, temp.length, startNum);
                System.out.println(new String(toTemp));
                //将换行符之后的内容(去除换行符)存到temp中
                temp = new byte[bs.length - startNum - 1];
                System.arraycopy(bs,startNum+1,temp,0,bs.length-startNum-1);
                //使用return即为单行读取，不打开即为全部读取
//                return;
            } else {
                //如果没出现换行符，则将内容保存到temp中
                byte[] toTemp = new byte[temp.length + bs.length];
                System.arraycopy(temp, 0, toTemp, 0, temp.length);
                System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                temp = toTemp;
            }
        }

        if(temp.length>0) {
            System.out.println(new String(temp));
        }
		Date endDate = new Date();
        long e = System.currentTimeMillis();
        
        System.out.println("共计:" + lineCount + "行");
        System.out.println("共计读取:" + readCount + "次");
        System.out.println("共计耗时:" + (e - s) + "微秒");
		System.out.print(startDate+"|"+endDate);//测试执行时间
        
        byteBuffer = null;
    }
}

