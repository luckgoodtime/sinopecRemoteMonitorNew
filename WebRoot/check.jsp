<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%
	String signature = request.getParameter("signature");
	String timestamp = request.getParameter("timestamp");
	String nonce = request.getParameter("nonce");
	String echostr = request.getParameter("echostr");
	
	System.out.println("signature:"+signature);
	System.out.println("timestamp:"+timestamp);
	System.out.println("nonce:"+nonce);
	System.out.println("echostr:"+echostr);
	
	System.out.println("request.getRequestURL():"+request.getRequestURL());
	System.out.println("request.getQueryString():"+request.getQueryString()); 


	
%><%=echostr %>