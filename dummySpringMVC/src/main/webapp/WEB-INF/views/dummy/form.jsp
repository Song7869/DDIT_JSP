<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" enctype="multipart/form-data">
		<input type="text" name="uploader" />
		<input type="file" name="uploadFile" />
		<input type="submit" value="전송" />		
	</form>
	
	
	<img src="${cPath }${saveURL }" />
</body>
</html>