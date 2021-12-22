<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<form method="post">
	<table>
	<th>비밀번호를 입력하세요</th>
	<td><input type="text" value="" name="inputPass"/></td>
	<td><input type="submit" value="전송!" /></td>
	</table>
</form>
</body>
</html>