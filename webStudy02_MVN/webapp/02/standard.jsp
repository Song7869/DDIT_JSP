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
	<h4>JSP (Java Server Page)</h4>
	<pre>
	 : 	서블릿 스펙에서 파생되었다.
	 	JSP Container 에 의해 운영되는 객체의 형태,
	 	UI(View) 템플릿 구성에 사용되는 스크립트 형태의 스펙이다.
	 	
	 	표준 구성 요소
	 	1. 정적인 구성요소 : 텍스트 (Front-End 를 구성, Client-Side)
	 		1) 텍스트
	 		2) 언어 : HTML, JavaScript, css, jQuery ...
	 		
	 	2. 동적인 구성요소 : (Back-End, Server-Side)
	 		1) scriptlet : <% //javacode %>
	 		<%
	 		String today = new Date().toString();
	 		// 지역변수로 생성된다.
	 		

	 		%>
	 		
	 		2) expression(표현부) : <%="표현식" %>
	 		<%= today %>
	 		
	 		3) declaration(선언부) : <%!//javacode %>
	 		<%!
	 		public String data = "";
	 		public static String test(){
	 			// 그냥 적으면지역코드안에 지역코드라서 에러가 난다.
	 			// 이 구역은 전역 변수를 선언하는 부분이라 가능하다.
	 			// 하지만 이부분은 밖에서는 사용할 수가 없다(밖에서는 안보여서)
	 			// 그래서 아무 쓰임이 없다.
	 			// 대신
	 			// 그래서 서블릿에서는 request를 통해서 내보내주는 것이었다.
	 			// scope의 종류로는 request, session, 
	 			return "";
	 		}
	 		%>
	 		4) directive(지시자) : <%--@  --%> : Jsp 페이지에 대한 설정값(속성의 형태).
	 			1. page		: 기본지시자(default)	: Jsp 페이지에 대한 설정값, 동적 페이지에 대한 환경값.
	 							(pageEncoding, mime, import, session flag, buffer size)
	 							
	 			2. taglib	: 옵션지시자(optional)
	 			
	 			3. include	: 옵션지시자(optional)
	 			
	 		5) comment
	 			1. client side comment : html, css, javascript
	 			<!-- 클라이언트 사이드 주석은 데이터 용량을 잡아 먹는다. -->
	 			
	 			<script type="text/javascript">
	 			 //comment
	 			</script>
	 			
	 			<style>
	 			 /* hr */
	 			</style>
	 			
	 			2. server side comment : java, jsp
	 			<%
	 			 //comment
	 			%>
	 			<%-- <%comment%> --%>
	 		
	 		6) action tag(custom-tag)
	 		7) EL(expression Language)
	 		8) JSTL(java standard tag library) 
	 	
	</pre>
</body>
</html>