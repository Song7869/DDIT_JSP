<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String message = (String) request.getAttribute("message");
	if (StringUtils.isNotBlank(message)) {
%>
<script type="text/javascript">
			alert("<%=message%>");
</script>
<%
	}
%>
</head>
<body>

	<h4>회원 정보 수정</h4>
	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
	<jsp:useBean id="errors" scope="request" class="java.util.LinkedHashMap"></jsp:useBean>
	<!-- jsp:useBean 은 스코프에서 id 값을 찾는다. 있으면 가져오고, 없으면 null을 처리해서 내보낸다.(null포인터가 뜨지 않게된다.) -->

	<form method="post">
		<table>
			<tr>
				<th>회원 아이디</th>
				<td><input required name="memId"
					value="<%=Objects.toString(member.getMemId(), "")%>" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memPass" required
					value="<%=member.getMemPass()%>" /><span class="error"><%=errors.get("memPass")%></span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" required
					value="<%=member.getMemName()%>" /><span class="error"><%=errors.get("memName")%></span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="memRegno1" required
					value="<%=member.getMemRegno1()%>" /><span class="error"><%=errors.get("memRegno1")%></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2" required
					value="<%=member.getMemRegno2()%>" /><span class="error"><%=errors.get("memRegno2")%></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir"
					value="<%=member.getMemBir()%>" /><span class="error"><%=errors.get("memBir")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" required
					value="<%=member.getMemZip()%>" /><span class="error"><%=errors.get("memZip")%></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1" required
					value="<%=member.getMemAdd1()%>" /><span class="error"><%=errors.get("memAdd1")%></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2" required
					value="<%=member.getMemAdd2()%>" /><span class="error"><%=errors.get("memAdd2")%></span></td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td><input type="text" name="memHometel" required
					value="<%=member.getMemHometel()%>" /><span class="error"><%=errors.get("memHometel")%></span></td>
			</tr>
			<tr>
				<th>회사번호</th>
				<td><input type="text" name="memComtel" required
					value="<%=member.getMemComtel()%>" /><span class="error"><%=errors.get("memComtel")%></span></td>
			</tr>
			<tr>
				<th>휴대전화</th>
				<td><input type="text" name="memHp"
					value="<%=member.getMemHp()%>" /><span class="error"><%=errors.get("memHp")%></span></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="text" name="memMail" required
					value="<%=member.getMemMail()%>" /><span class="error"><%=errors.get("memMail")%></span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob"
					value="<%=member.getMemJob()%>" /><span class="error"><%=errors.get("memJob")%></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike"
					value="<%=member.getMemLike()%>" /><span class="error"><%=errors.get("memLike")%></span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="memMemorial"
					value="<%=member.getMemMemorial()%>" /><span class="error"><%=errors.get("memMemorial")%></span></td>
			</tr>
			<tr>
				<th>기념날짜</th>
				<td><input type="date" name="memMemorialday"
					value="<%=member.getMemMemorialday()%>" /><span class="error"><%=errors.get("memMemorialday")%></span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>3000</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" />
					<input type="reset" value="취소" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>