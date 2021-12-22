<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
${pageContext.errorData.requestURI } 에서 예외 발생
${pageContext.errorData.throwable }
</pre>
</body>
</html>