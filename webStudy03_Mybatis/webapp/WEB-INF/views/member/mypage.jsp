<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO member = (MemberVO) request.getAttribute("member");
%>
	<table>
		<tr>
			<th>회원아이디</th>
			<td><%=member.getMemId()%></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=member.getMemPass()%></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><%=member.getMemName()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMemRegno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMemRegno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMemBir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMemZip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMemAdd1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=member.getMemAdd2()%></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=member.getMemHometel()%></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><%=member.getMemComtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=member.getMemHp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMemMail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMemJob()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMemLike()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=member.getMemMemorial() %></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=member.getMemMemorialday() %></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMemMileage() %></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMemDelete() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="회원정보수정" 
					onclick="location.href='<%=request.getContextPath() %>/member/memberUpdate.do';"
				/>
				<input type="button" value="회원탈퇴" 
					onclick="deleteHandler();"
				/>
			</td>
		</tr>
		<tr>
			<th>구매상품목록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품코드</th>
							<th>상품명</th>
							<th>상품분류</th>
							<th>거래처</th>
							<th>거래처주소</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<%
							Set<ProdVO> prodList = member.getProdList();
							if(prodList.isEmpty()){
								%>
								<tr>
									<td colspan="7">구매상품 없음.</td>
								</tr>
								<%
							}else{
								for(ProdVO prod : prodList){
									%>
									<tr>
										<td><%=prod.getProdId() %></td>
										<td><%=prod.getProdName() %></td>
										<td><%=prod.getLprodNm() %></td>
										<td><%=prod.getBuyer().getBuyerName() %></td>
										<td><%=prod.getBuyer().getBuyerAdd1() %></td>
										<td><%=prod.getProdCost() %></td>
										<td><%=prod.getProdPrice() %></td>
										<td><%=prod.getProdMileage() %></td>
									</tr>
									<%
								}
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<form name="deleteForm" action="<%=request.getContextPath() %>/member/memberDelete.do" method="post">
		<input type="hidden" name="memPass" />
	</form>
	<script type="text/javascript">
	<%
		String message = (String) session.getAttribute("message");
		if(StringUtils.isNotBlank(message)){
			%>
			alert("<%=message %>");
			<%
			// flash attribute
			session.removeAttribute("message");
		}
	%>
		function deleteHandler(){
			let password = prompt("비밀번호");
			if(password){
				let form = document.deleteForm;
				form.memPass.value = password;
				form.submit();
			}
		}
	</script>
</body>
</html>











