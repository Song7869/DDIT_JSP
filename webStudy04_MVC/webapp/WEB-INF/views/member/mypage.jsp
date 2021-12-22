<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table>
		<tr>
			<th>회원아이디</th>
			<td>${member.memId }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.memPass }</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member.memName }</td>
		</tr>
		<tr>
			<th>프로필이미지</th>
			<td>
				<img src="data:image/*;base64,${member.base64Image }" />
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.memRegno1 }</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.memRegno2 }</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.memBir }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAdd1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAdd2 }</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member.memHometel }</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member.memComtel }</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member.memHp }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail }</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob }</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike }</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.memMemorial }</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.memMemorialday }</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage }</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete }</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="회원정보수정" class="linkBtn"
					data-gopage="${pageContext.request.contextPath }/member/memberUpdate.do"
				/>
				<input type="button" value="회원탈퇴" onclick="deleteHandler();" />
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
						<c:set var="prodList" value="${member.prodList }" />
						<c:choose>
							<c:when test="${empty prodList }">
								<tr>
									<td colspan="7">구매상품 없음.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${prodList }" var="prod">
									<tr>
										<td>${prod.prodId }</td>
										<td>${prod.prodName }</td>
										<td>${prod.lprodNm }</td>
										<td>${prod.buyer.buyerName }</td>
										<td>${prod.buyer.buyerAdd1 }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodMileage }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<form name="deleteForm" action="${pageContext.request.contextPath }/member/memberDelete.do" method="post">
		<input type="hidden" name="memPass" />
	</form>
	<script type="text/javascript">
		function deleteHandler(){
			let password = prompt("비밀번호");
			if(password){
				let form = document.deleteForm;
				form.memPass.value = password;
				form.submit();
			}
		}
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>











