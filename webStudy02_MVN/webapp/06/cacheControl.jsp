<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>캐시 제어용 헤더</h4>
	<pre>
		: pragma, Cache-Control, Expires - 캐시 여부 결정
		Expires(공통, 캐시만료 제한)
		<%	
//			String cacheValue1 = "no-cache";
//			String cacheValue2 = "no-store";
			String cacheValue1 = "public";
			response.setHeader("Pragma", cacheValue1);
			response.setHeader("Cache-Control", cacheValue1);
//			response.addHeader("Cache-Control", cacheValue2);
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, 7);
			response.setDateHeader("Expires", now.getTimeInMillis());
		%>
	</pre>
</body>
</html>