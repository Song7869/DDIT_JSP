<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${pageContext.exception }
	${pageContext.errorData.requestURI }<br> 에서 예외 발생 <!-- 클라이언트가 어떤 요청을 발생했을 때 에러가 발생했는가? -->
	${pageContext.errorData.throwable } <!-- 발생한 예외의 정보 : 악용의 우려가 있어서 사용하지 않는다. -->
</body>
</html>