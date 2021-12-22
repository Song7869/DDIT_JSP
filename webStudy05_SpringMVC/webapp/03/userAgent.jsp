<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
<h4> User-Agent Header </h4>
<!-- 클라이언트의 브라우저가 크롬이면, -->
<!-- "당신의 브라우저는 크롬입니다!" -->
<!-- 엣지면, -->
<!-- "당신의 브라우저는 엣지입니다!" -->
<!-- 사파리면, -->
<!-- "당신의 브라우저는 사파리입니다!" -->
<!-- 형태의 메시지를 alert 창으로 출력. -->
<%
	String agent = request.getHeader("User-Agent");
	String browserName = BrowserType.findBrowserName(agent);
	String pattern = "당신의 브라우저는 %s입니다!";
	String message = String.format(pattern, browserName);
%>
<%=agent %>
<script type="text/javascript">
	alert("<%=message %>");
</script>
</body>
</html>














