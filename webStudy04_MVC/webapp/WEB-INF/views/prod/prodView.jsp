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
			<th>상품코드</th>
			<td>${prod.prodId }</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prodName }</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.lprodNm }</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>거래처명</th>
							<th>담당자</th>
							<th>소재지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${prod.buyer.buyerName }</td>
							<td>${prod.buyer.buyerCharger }</td>
							<td>${prod.buyer.buyerAdd1 }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prodCost }</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice }</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale }</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td>${prod.prodOutline }</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prodDetail }</td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td><img src="${pageContext.request.contextPath }/resources/prodImages/${prod.prodImg }" /></td>
		</tr>
		<tr>
			<th>재고</th>
			<td>${prod.prodTotalstock }</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate }</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock }</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize }</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor }</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery }</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit }</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin }</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>${prod.prodQtysale }</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/prod/prodUpdate.do" var="updateURL">
					<c:param name="what" value="${prod.prodId }" />
				</c:url>
				<input type="button" class="linkBtn" data-gopage="${updateURL }" value="상품 수정" />
			</td>
		</tr>
		<tr>
			<th>구매자정보</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>구매자명</th>
							<th>소재지</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="memberList" value="${prod.memberList }" />
						<c:choose>
							<c:when test="${empty memberList }">
								<tr>
									<td colspan="3">구매자가 없음.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${memberList }" var="member">
									<tr>
										<td>${member.memName }</td>
										<td>${member.memAdd1 }</td>
										<td>${member.memMail }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>










