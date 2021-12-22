<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
	String message = request.getParameter("message");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>웰컴 페이지</h4>
<c:set var="authMember" value="${sessionScope.authMember }" />
<c:choose>
	<c:when test="${not empty authMember }">
		<a href="${pageContext.request.contextPath }/mypage.do">${authMember.memName }[${authMember.memRole }]</a>
		<a href="${pageContext.request.contextPath }/login/logout.do" onclick="return clickHandler(event);">로그아웃</a>
		<form name="logoutForm" method="post">
			<input type="hidden" name="_csrftoken" value="token_value" />
		</form>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/login/loginForm.jsp">로그인</a>
		<a href="${pageContext.request.contextPath }/member/memberInsert.do">회원가입</a>
	</c:otherwise>
</c:choose>

<script>
	function clickHandler(event){
		event.preventDefault();
		document.logoutForm.action = event.target.href;
		document.logoutForm.submit();
		return false;
	}
</script>
</body>
</html>









