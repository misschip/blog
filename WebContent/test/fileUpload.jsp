<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String uploadPath = request.getRealPath("/images");


	int size = 10*1024*1024;
	String name = "";
	String subject = "";
	String filename1 = "";
	String filename2 = "";
	String origfilename1 = "";
	String origfilename2 = "";
	
	try {
		MultipartRequest multi = new MultipartRequest(request,
						uploadPath,
						size,
						"UTF-8",
						new DefaultFileRenamePolicy());
		
		name = multi.getParameter("name");
		subject = multi.getParameter("subject");
		
		// 아래 Enumeration<String> files가 갖고 있는 문자열 값은 결국 html 페이지의 file 파라메터 이름이다!
		// 즉, <input type="file" name="xxx"> 안에 있던 xxx자리의 값인 fileName1과 fileName2임!
		Enumeration<String> files = multi.getFileNames();
		
		// 따라서 아래에서는 앞 페이지에서의 파라메터 이름을 바로 써서
		// multi.getFilesystemName("fileName1");
		// multi.getOriginalFileName("fileName1");
		// 과 같이 써도 된다. 이게 순서 유지를 위해서는 오히려 낫다.
		
		String file1 = files.nextElement();
		System.out.println(file1);	// 2개 이상일 경우에 출력되는 순서는 넣는 순서와 상관없음. 내부에 Hashtable에 저장했다가 꺼내기 때문임
		filename1 = multi.getFilesystemName(file1);
		origfilename1 = multi.getOriginalFileName(file1);
		
		String file2 = files.nextElement();
		System.out.println(file2);	// 2개 이상일 경우에 출력되는 순서는 넣는 순서와 상관없음. 내부에 Hashtable에 저장했다가 꺼내기 때문임
		filename2 = multi.getFilesystemName(file2);
		origfilename2 = multi.getOriginalFileName(file2);
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
	<form name="filecheck" action="fileCheck.jsp" method="post">
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="subject" value="<%=subject%>">
		<input type="hidden" name="filename1" value="<%=filename1%>">
		<input type="hidden" name="filename2" value="<%=filename2%>">
		<input type="hidden" name="origfilename1" value="<%=origfilename1%>">
		<input type="hidden" name="origfilename2" value="<%=origfilename2%>">
	</form>
	<a href="#" onclick="javascript:filecheck.submit()">업로드 확인 및 다운로드 페이지 이동</a>
</body>
</html>