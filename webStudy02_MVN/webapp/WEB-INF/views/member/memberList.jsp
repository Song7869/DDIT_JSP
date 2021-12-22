<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<th>회원 아이디</th>
			<th>이름</th>
			<th>번호</th>
			<th>메일</th>
			<th>주소(상위주소)</th>
			<th>마일리지</th>
		</thead>
		<tbody>
			<%
				List<MemberVO> memberList = (List)request.getAttribute("memberList");
			if(memberList.isEmpty()){
				%>
				<tr>
					<td colspan="6">회원 없음.</td>
				</tr>
				<%
			}
			else{
				for(MemberVO member : memberList){
					%>
					<tr>
					<td><%=member.getMemId() %></td>
					<td><%=member.getMemName() %></td>
					<td><%=member.getMemHp() %></td>
					<td><%=member.getMemMail() %></td>
					<td><%=member.getMemAdd1() %></td>
					<td><%=member.getMemMileage() %></td>
					</tr>
					<%
			}
				}
			%>
		</tbody>
	</table>
</body>
</html>