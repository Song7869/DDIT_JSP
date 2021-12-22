<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<%
	String message = (String) session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		session.removeAttribute("message");
		%>
			<script type="text/javascript">
				alert("<%=message %>");
			</script>
		<%
	}
%>
</head>
<body>
<form action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
<ul>
	<li>
		<%
			String failId = (String) session.getAttribute("failId");
			// flash attribute
			session.removeAttribute("failId");
		%>
		아이디 : <input type="text" name="memId" value="<%=Objects.toString(failId, "") %>"/>
	</li>
	<li>
		비밀번호 : <input type="text" name="memPass" />
		<input type="submit" value="로그인"  />
	</li>
</ul>
</form>
</body>
</html>