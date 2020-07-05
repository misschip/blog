<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %> <%--  이 한줄 코드는
	java.lang.IllegalStateException: getoutputstream has already been called for this response()
	예외 발생을 방지하기 위한 임시조치. 근본적으로는 JSP 페이지에서는 텍스트 출력을 위해서 getWriter()만 하는게 바람직하고
	getOutputStream()으로 바이너리 출력하는 코드는 Servlet에 들어가도록 하는 게 맞다고 함 --%>
    
<%
	String fileName = request.getParameter("file_name");

	String savePath = "images";
	ServletContext context = getServletContext();
	String sDownloadPath =  context.getRealPath(savePath);
	
	System.out.println("file_down.jsp : sDownloadPath : " + sDownloadPath);
	
	String sFilePath = sDownloadPath + "\\" + fileName;
	
	System.out.println("file_down.jsp : sFilePath : " + sFilePath);
	
	byte[] b = new byte[4096];
	FileInputStream in = new FileInputStream(sFilePath);
	String sMimeType = getServletContext().getMimeType(sFilePath);
	
	System.out.println("sMimeType>>>" + sMimeType);
	
	if (sMimeType == null) {
		sMimeType = "application/octet-stream";
	}
	
	response.setContentType(sMimeType);
	String agent = request.getHeader("User-Agent");
	boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
	
	if (ieBrowser) {
		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	} else {
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
	}
	
	response.setHeader("Content-Disposition", "attachment; filename= " + fileName);
	
	ServletOutputStream out2 = response.getOutputStream();
	int numRead;
	
	while ((numRead = in.read(b,0,b.length)) != -1) {
		out2.write(b,0,numRead);
	}
	
	out2.flush();
	out2.close();
	in.close();
%>
