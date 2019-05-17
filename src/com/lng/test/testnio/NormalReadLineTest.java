package com.lng.test.testnio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NormalReadLineTest {
	
	public static void main(String[] args) {
		
    	long s = System.currentTimeMillis();
		
		String fileName = "C:\\工作资料\\其他\\数据库\\数据档案csv.txt";
		
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(fileName));
			String line = null;  
			while((line = input.readLine()) != null) {  
				System.out.println(line);
			}  
			  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
        long e = System.currentTimeMillis();
        System.out.println("共计耗时:" + (e - s) + "微秒");
		
		
	}

}
