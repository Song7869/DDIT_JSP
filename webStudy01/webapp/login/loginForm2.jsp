<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String mem_id = String.valueOf(request.getAttribute("mem_id"));
    	if(mem_id.equals("null")){
    		mem_id="";
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/login/loginProcess2.do" method="post">
		<ul>
			<li>
				아이디 : <input type="text" name="mem_id" value="<%=mem_id %>"/>
			</li>
			<li>
				비밀번호 : <input type="text" name="mem_pass" />
				<input type="submit" value ="로그인" /> 
			</li>
		</ul>
	</form>
</body>
</html>