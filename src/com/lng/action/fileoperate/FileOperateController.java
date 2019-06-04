package com.lng.action.fileoperate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lng.util.Util;

 
@Controller
@RequestMapping(value="/fileOperate")
public class FileOperateController {
	
    @RequestMapping(value="/upload.action")
    public String upload(HttpServletRequest request){
    	
    	System.out.println("-----------------uploadfile------------");
    	
        init(request);
        
        String fileData0 = request.getParameter("image0_data");
        String fileData1 = request.getParameter("image1_data");
        String fileData2 = request.getParameter("image2_data");
        
        if(fileData0 != null && fileData0.length() > 0) {
        	System.out.println("------fileData0-------------" + fileData0);
    		int dataindex = fileData0.indexOf("base64,") + "base64,".length();
        	FileOperateUtil.saveBase64AsJPG(fileData0.substring(dataindex), "img0.jpg");
        }
        
        if(fileData1 != null && fileData1.length() > 0) {
        	System.out.println("------fileData1-------------" + fileData1);
        	int dataindex = fileData1.indexOf("base64,") + "base64,".length();
        	FileOperateUtil.saveBase64AsJPG(fileData1.substring(dataindex), "img1.jpg");
        }
        
        if(fileData2 != null && fileData2.length() > 0) {
        	System.out.println("------fileData2-------------" + fileData2);
        	int dataindex = fileData1.indexOf("base64,") + "base64,".length();
        	FileOperateUtil.saveBase64AsJPG(fileData2.substring(dataindex), "img2.jpg");
        }
        
        
        try {
            FileOperateUtil.upload(request);
            request.setAttribute("msg", "ok");
            request.setAttribute("map", getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return "redirect:list";
    }
    
    @RequestMapping(value="/list.action")
    public ModelAndView list(HttpServletRequest request){       
        init(request);
        request.setAttribute("map", getMap());
        return new ModelAndView("fileOperate/list");
    }
    
    @RequestMapping(value="/download.action")
    public void download(HttpServletRequest request, HttpServletResponse response){
        init(request);
        try {
            String downloadfFileName = request.getParameter("filename");
            downloadfFileName = new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8");
            String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_")+1);
            String userAgent = request.getHeader("User-Agent");
            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); 
            fileName = new String(bytes, "ISO-8859-1");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            FileOperateUtil.download(downloadfFileName, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void init(HttpServletRequest request) {
        if(FileOperateUtil.FILEDIR == null){
            FileOperateUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "file/";
        }
    }
    
    private Map<String, String> getMap(){
        Map<String, String> map = new HashMap<String, String>();
        File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
        if(files != null){
            for (File file : files) {
                if(file.isDirectory()){
                    File[] files2 = file.listFiles();
                    if(files2 != null){
                        for (File file2 : files2) {
                            String name = file2.getName();
                            map.put(file2.getParentFile().getName() + "/" + name, name.substring(name.lastIndexOf("_")+1));
                        }
                    }
                }
            }
        }
        return map;
    }   

	@RequestMapping(value = "/exprotExcel.do")
	public void exprotExcel(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter out = null;
		try {
			String fileName = request.getParameter("fileName");
			if(StringUtils.isBlank(fileName)) {
				fileName = "excel导出";
			}
			fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
			fileName += Util.datetoStr(new Date());

			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");// 设定输出文件头
			response.setContentType("Application/ms-excel");// 定义输出类型

			out = response.getWriter();
			out.println("<html>\\n<head></head>\\n");
			out.println("<body>\n" + request.getParameter("txtConent") + "\n</body>\n</html>");
			out.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}
}
