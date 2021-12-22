<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
	String message =     request.getParameter("message");
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
	}
%>

</head>
<body>
	<h4>웰컴 페이지</h4>
	<pre>
	1. 제일 처음 접속 -> 세션이 없음
	2. 로그인 후 접속 -> 세션이 있음
	- 로그아웃은 post방식
	</pre>
	<%
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		if(authMember != null){
			%>
			<a><%=authMember.getMem_id() %></a>
			<a href="<%=request.getContextPath()%>/login/logout.do"  onclick="return clickHandler(event);">로그아웃</a>
			<form name="logoutForm" method="post">
				
			</form>
			<%
		}
		else{
			%>
			<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>
			<%
		}
	%>
	
<script type="text/javascript">
	function clickHandler(event){
		event.preventDefault();
		document.logoutForm.action = event.target.href;
		document.logoutForm.submit();
		return false;
	}
</script>	
	
</body>
</html>