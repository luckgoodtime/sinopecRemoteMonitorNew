package com.lng.util.template;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CodeTemplate {

	private String name = "article";
	private String encode = "UTF-8";

	public static void main(String[] args) throws Exception {
		new CodeTemplate().genCodeTemplate();
	}

	public void genCodeTemplate() throws Exception {

		String nameUpper = name.replaceFirst(name.substring(0, 1), name
				.substring(0, 1).toUpperCase());
		String nameLower = name.replaceFirst(name.substring(0, 1), name
				.substring(0, 1).toLowerCase());

		// 生成service
		File serviceFile = new File(CodeTemplate.class.getResource(
				"Service.txt").toURI());
		String dir = System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "com" + File.separator + "lng"
				+ File.separator + "service" + File.separator + "biz"
				+ File.separator + "order" + File.separator;
		String content = read(serviceFile.getPath());
		String targetStr = dir + nameUpper + "Service.java";
		write(targetStr, content);
		System.out.println("已生成文件：" + targetStr);

		// 生成Control
		File controlFile = new File(CodeTemplate.class.getResource(
				"Controller.txt").toURI());
		String controlDir = System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "com" + File.separator + "lng"
				+ File.separator + "action" + File.separator + "businessexec"
				+ File.separator;
		targetStr = controlDir + nameUpper + "Controller.java";
		write(targetStr, read(controlFile.getPath()));
		System.out.println("已生成文件：" + targetStr);

		// 生成addOrUpdate.jsp
		File addOrUpdateFile = new File(CodeTemplate.class.getResource(
				"addOrUpdate.txt").toURI());
		String addOrUpdateDir = System.getProperty("user.dir") + File.separator
				+ "WebRoot" + File.separator + "jsp" + File.separator
				+ "businessexec" + File.separator + nameLower + File.separator;
		File f = new File(addOrUpdateDir);
		if (!f.exists())
			f.mkdirs();
		targetStr = addOrUpdateDir + "addOrUpdate.jsp";
		write(targetStr, read(addOrUpdateFile.getPath()));
		System.out.println("已生成文件：" + targetStr);

		// 生成list.jsp
		File listFile = new File(CodeTemplate.class.getResource("list.txt")
				.toURI());
		String listDir = addOrUpdateDir;
		f = new File(listDir);
		if (!f.exists())
			f.mkdirs();
		targetStr = listDir + "list.jsp";
		write(targetStr, read(listFile.getPath()));
		System.out.println("已生成文件：" + targetStr);

	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public String read(String filePath) {
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();

		try {
			// 根据文件路径创建缓冲输入流
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					new File(filePath)), encode);
			br = new BufferedReader(isr);
			// br = new BufferedReader(new FileReader(filePath));
			String nameUpper = name.replaceFirst(name.substring(0, 1), name
					.substring(0, 1).toUpperCase());
			String nameLower = name.replaceFirst(name.substring(0, 1), name
					.substring(0, 1).toLowerCase());
			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("SourcePoint", nameUpper);
				buf.append(line.replaceAll("sourcePoint", nameLower));

				// 此处根据实际需要修改某些行的内容
				/*
				 * if (line.startsWith("a")) { buf.append(line).append(" start
				 * with a"); } else if (line.startsWith("b")) {
				 * buf.append(line).append(" start with b"); } // 如果不用修改,
				 * 则按原来的内容回写 else { buf.append(line); }
				 */
				buf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}

		return buf.toString();
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 */
	public void write(String filePath, String content) {
		BufferedWriter bw = null;

		try {

			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(filePath), encode); // 指定以UTF-8编码输出
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(osw);
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
	}

}
