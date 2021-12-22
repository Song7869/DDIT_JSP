<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String message ="";
    	if(request.getAttribute("member")!=null){
    		MemberVO mvo = (MemberVO)request.getAttribute("member");
    	}

    
    	if(request.getAttribute("Message")!=null){    		
    		message = (String)request.getAttribute("Message");
    	}


    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		var message = <%=message%>;
		if(!message.equals("")){
		alert(<%=message%>);
		}
		alert(<%=message%>);
	})
	
</script>
</head>
<body>
	<h4> 회원 가입 페이지 </h4>
	<form method="post">
		아이디* : <input type="text" value="" name="MEM_ID"><br>
		비밀번호* : <input type="text" value="" name="MEM_PASS"><br>
		이름* : <input type="text"  value=""  name="MEM_NAME"><br>
		주민번호 앞자리* : <input type="text"  value=""  name="MEM_REGNO1"><br>
		주민번호 뒷자리* : <input type="text"   value=""  name="MEM_REGNO2"><br>
		생일 : <input type="date"  value=""   name="MEM_BIR"><br>
		우편번호* : <input type="text"   value=""  name="MEM_ZIP"><br>
		주소* : <input type="text"   value=""   name="MEM_ADD1"><br>
		상세주소* : <input type="text"    value=""   name="MEM_ADD2"><br>
		집 전화번호* : <input type="text"   value=""  name="MEM_HOMETEL"><br>
		직장 전화번호* : <input type="text"   value=""   name="MEM_COMTEL"><br>
		휴대전화 : <input type="text" value=""    name="MEM_HP"><br>
		메일* : <input type="text"  value=""   name="MEM_MAIL"><br>
		직업 : <input type="text"    value=""  name="MEM_JOB"><br>
		취미 : <input type="text"   value=""    name="MEM_LIKE"><br>
		기념일 : <input type="text"  value=""   name="MEM_MEMORIAL"><br>
		기념일 : <input type="date"   value=""   name="MEM_MEMORIALDAY"><br>
		<div hidden="">마일리지 : <input type="number" value="0" name="MEM_MILEAGE"></div><br>
		<div hidden="">탈퇴여부 : <input type="text" value="N" name="MEM_DELETE"></div><br>
		<input type="submit" value="가입신청"/>
	</form>
</body>
</html>