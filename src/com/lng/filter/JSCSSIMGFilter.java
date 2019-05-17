package com.lng.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSCSSIMGFilter implements Filter{
	
	private FilterConfig filterConfig;



	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("开始资源图片JS过滤了"); 
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		
		System.out.println("---uri--:" + uri);
		
		System.out.println("---url--:" + url);
		
		String remotehost = req.getRemoteHost();
		int remoteport = req.getRemotePort();
		String contextPath = req.getContextPath();
		String type = req.getContentType();
		
		String requesturlhost = url.substring(0,url.indexOf(contextPath)) + uri;
		
		System.out.println("---remotehost--:" + remotehost);
		System.out.println("---remoteport--:" + remoteport);
		System.out.println("---contextPath--:" + contextPath);
		System.out.println("---type--:");
		
		System.out.println("-------Content-Type------" + req.getHeader("Content-Type"));

		
		System.out.println("---requesturlhost--:" + requesturlhost);
		
		
		Map map = new HashMap();
		
		String newuri = "";
		String resourceType = "";
		
		boolean isImage = false;
				
		if(uri.indexOf("images/") > 0) {
			
			isImage = true;
			newuri = uri.substring(uri.indexOf("images/"));
			if(newuri.endsWith(".gif"))
				resourceType = "image/gif";
			else if(newuri.endsWith(".png"))
				resourceType = "image/png";
		}

		else if(uri.indexOf("js/") > 0) {
			newuri = uri.substring(uri.indexOf("js/"));
			resourceType = "text/javascript";
		} else if(uri.indexOf("css/") > 0) {
			newuri = uri.substring(uri.indexOf("css/"));
			resourceType = "text/stylesheet";
		}

		
//		System.out.println("--newuri--1--" + req.getServletContext().getRealPath(newuri));
//		
//		newuri = "/" + newuri;
//		System.out.println("--newuri----" + newuri);
//		
//		System.out.println("--newuri--2--" + req.getServletContext().getRealPath(newuri));
		
		//req.getRequestDispatcher("/images/logo.png").forward(request, response);
		
//		if(!map.containsKey(newuri)) {
//			map.put(newuri, newuri);			
//			req.getRequestDispatcher(newuri).forward(request, response);			
//		} else {
//			chain.doFilter(request, response);			
//		}
//		

		
		
//		String filePath = req.getServletContext().getRealPath(newuri);
//		
//		File file = new File(filePath);
//		if(file.exists())
//			System.out.println("----------------file is exist");
//		else 
//			System.out.println("----------------file is not exist");
////		
//		if(isImage)
//			getImage( req,  rep, file , resourceType);
//		else
//			getGeneralResource( req,  rep, file , resourceType);
		
		chain.doFilter(request, response);

		
	}

	public void init(FilterConfig arg0) throws ServletException {
        System.out.println("JSCSSIMG filter 初始化了"); 
        this.filterConfig = filterConfig; 		
	}
	
	public void destroy() {
		System.out.println("JSCSSIMG filter 销毁了"); 
	}
	
	public void getImage(HttpServletRequest request, HttpServletResponse response,File file, String imageType)throws ServletException, IOException{		

		 response.setContentType(imageType);
		 response.setCharacterEncoding("utf-8");

		 //String path = filePath;//"e:/maprequest.png";

		 BufferedImage bi = ImageIO.read(file);
		 
		 OutputStream toClient = response.getOutputStream(); //得到向客户端输出二进制数据的对象 
		 ImageIO.write(bi, imageType, toClient);	
		 
		 toClient.flush();
		 toClient.close();

	 }
	
	public void getGeneralResource(HttpServletRequest request, HttpServletResponse response,File file, String resourceType) throws ServletException, IOException{

		 try{ 

			 //FileInputStream hFile = new FileInputStream("e:\\map.GIF"); // 以byte流的方式打开文件 d:\1.gif 
			 
			 FileInputStream hFile = new FileInputStream(file); // 以byte流的方式打开文件 d:\1.gif 

			 int i = hFile.available(); //得到文件大小 
			 
			 System.out.println("----------------file size:" + i);

			 byte data[]=new byte[i]; 

			 hFile.read(data); //读数据 

			 hFile.close(); 

			 //response.setContentType("image/png"); //设置返回的文件类型 
			 response.setContentType(resourceType);
			 response.setCharacterEncoding("utf-8");
			 response.setContentLength(i);
			 

			 OutputStream toClient = response.getOutputStream(); //得到向客户端输出二进制数据的对象 

			 toClient.write(data); //输出数据 
			 
			 toClient.flush();

			 toClient.close(); 

			 } 

			 catch(IOException e) //错误处理 

			 { 

				 PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象 
	
				 response.setContentType("text/html;charset=UTF-8"); 
	
				 toClient.write("无法打开文件!"); 
	
				 toClient.close(); 

			 } 

	 }
	
	


}
