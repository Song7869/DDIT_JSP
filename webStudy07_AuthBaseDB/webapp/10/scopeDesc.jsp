<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/scopeDesc.jsp</title>
</head>
<body>
<h4>웹어플리케이션의 영역(Scope)과 속성(Attribute)</h4>
<pre>
setAttribute(name, value), getAttribute(name), removeAttribute(name), getAttributeNames()
	scope? 4가지의 기본 객체가 각자 관리하고 있는 map 형태의 데이터 공유 공간, 관리 기본 객체와 생명주기가 동일함.
1. page Scope : pageContext Map&lt;String,Object&gt;
2. request Scope : request Map&lt;String,Object&gt;
3. session Scope : session Map&lt;String,Object&gt;
4. application Scope : ServletContext Map&lt;String,Object&gt;
<a href="${pageContext.request.contextPath }/07/destination.jsp">destination이동</a>
</pre>
<%
	pageContext.setAttribute("pageAttr", "페이지 속성");
	request.setAttribute("requestAttr", "요청 속성");
	session.setAttribute("sessionAttr", "세션 속성");
	application.setAttribute("applicationAttr", "어플리케이션 속성");
// 	pageContext.include("/07/destination.jsp");
	response.sendRedirect(request.getContextPath() + "/07/destination.jsp");
%>
</body>
</html>















