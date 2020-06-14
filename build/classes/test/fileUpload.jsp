<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 아래 코드 출처 : https://nanstrong.tistory.com/54 --%>
    
<%
	String uploadPath = request.getRealPath("images");

	int size = 10 * 1024 * 1024;
	String name = "";
	String fileName = "";
	String originalFileName = "";
	
	try {
		MultipartRequest multi = new MultipartRequest(
						request,
						uploadPath,
						size,
						"UTF-8",
						new DefaultFileRenamePolicy()
				);
		
		// Enumeration<String> files = multi.getFileNames();
		String filename = multi.getOriginalFileName("filename");
		String origFilename = multi.getFilesystemName("filename");
		
	} catch (Exception e) {
		e.printStackTrace();
	}

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>