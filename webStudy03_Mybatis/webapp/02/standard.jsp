<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>JSP(Java Server Page)</h4>
<pre>
	: 서블릿 스펙에서 파생, JSP container 에 의해 운영되는 객체의 형태, UI 템플릿 구성에 사용되는 스크립트 형태의 스펙.
	
	표준 구성 요소
	1. 정적 텍스트(Front-End, Client side)
		1) 텍스트
		2) 언어 : HTML, Javascript, css, jQuery...
	2. 동적(Back-End, Server side) : 
		1) scriptlet : 
		<%
		//java code 
		String today = new Date().toString();
		%>
		2) expression : <%="표현식" %>, <%=today %>
		3) declaration(선언부) : 
<%! 
// java code 
		public String data = "";
		public static String test(){
			return "";
		}
%>
		4) directive(지시자) : <%--@ --%>: Jsp 페이지에 대한 설정값(속성의 형태).
			page (default) : Jsp 페이지에 대한 설정, 동적 페이지에 대한 환경값.
							(pageEncoding, mime, import, session flag, buffer size)
			taglib (optional)
			include (optional)
		5) comment 
			client side comment : html, css, javascript
<!-- 			<img /> -->
			<script>
// 				comment
			</script>
			<style>
/* 				hr */
			</style>
			server side comment : java, jsp
			<%
// 				comment
			%>
<%-- 			<% comment %> --%>
		6) action tag(custom tag)
		7) EL(expression language)
		8) JSTL(java standard tag library)
</pre>
</body>
</html>













