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
		
		name = multi.getParameter("name");

		// Enumeration<String> files = multi.getFileNames();
		// String file = files.nextElement();	// Enumeration을 통해 갖고오는 값은 결국 "filename"임
		originalFileName = multi.getOriginalFileName("filename");
		fileName = multi.getFilesystemName("filename");
		System.out.println("originalFileName : " + originalFileName);
		System.out.println("fileName : " + fileName);
		
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
	<form name="uploadCheck" action="fileUploadCheck.jsp" method="POST">
		<input type="hidden" name="name" value="<%=name %>">
		<input type="hidden" name="filename" value="<%=fileName %>">
		<input type="hidden" name="origfilename" value="<%=originalFileName %>">
	</form>
	<a href="#" onclick="javascript:fileUploadCheck.submit()">업로드 체크 및 다운로드 페이지로 이동</a>
</body>
</html>