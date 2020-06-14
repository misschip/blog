<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Form</title>
</head>
<body>
	<form action="fileUpload.jsp" method="POST" enctype="mulitpart/form-data">
		<table border="1">
			<tr>
				<td colspan="2" align="center"><h4>File Upload Form</h4></td>
			</tr>
			<tr>
				<td>Name: </td><td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>File Name: </td><td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="업 로 드"></td>
			</tr>
		</table>
	</form>
</body>
</html>