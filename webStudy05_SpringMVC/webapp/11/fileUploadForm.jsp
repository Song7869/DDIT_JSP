<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/fileUploadSpec3.do" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader" placeholder="업로더 이름"/>
	<input type="file" name="uploadFile" accept="image/*"/>
	<input type="file" name="uploadFile" accept="image/*"/>
	<input type="submit" value="업로드"  />
</form>
<div>
	<c:if test="${not empty  uploader}">
		${uploader }
		<c:remove var="uploader" scope="session"/>
	</c:if>
	<c:if test="${not empty  uploadFile_0}">
		<img src="${pageContext.request.contextPath }${uploadFile_0 }" />
		<c:remove var="uploadFile_0" scope="session"/>
	</c:if>
	<c:if test="${not empty  uploadFile_1}">
		<img src="${pageContext.request.contextPath }${uploadFile_1 }" />
		<c:remove var="uploadFile_1" scope="session"/>
	</c:if>
</div>
</body>
</html>




