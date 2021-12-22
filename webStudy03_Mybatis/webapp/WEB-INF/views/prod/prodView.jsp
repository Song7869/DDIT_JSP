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
	<jsp:useBean id="prod" type="kr.or.ddit.vo.ProdVO" scope="request" />
	<table>
		<tr>
			<th>상품코드</th>
			<td><%=prod.getProdId()%></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><%=prod.getProdName()%></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td><%=prod.getLprodNm()%></td>
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
							<td><%=prod.getBuyer().getBuyerName() %></td>
							<td><%=prod.getBuyer().getBuyerCharger() %></td>
							<td><%=prod.getBuyer().getBuyerAdd1() %></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><%=prod.getProdCost()%></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><%=prod.getProdPrice()%></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><%=prod.getProdSale()%></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><%=prod.getProdOutline()%></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><%=prod.getProdDetail()%></td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td><%=prod.getProdImg()%></td>
		</tr>
		<tr>
			<th>재고</th>
			<td><%=prod.getProdTotalstock()%></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><%=prod.getProdInsdate()%></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><%=prod.getProdProperstock()%></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><%=prod.getProdSize()%></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><%=prod.getProdColor()%></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><%=prod.getProdDelivery()%></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><%=prod.getProdUnit()%></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><%=prod.getProdQtyin()%></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><%=prod.getProdQtysale()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=prod.getProdMileage()%></td>
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
						<%
							List<MemberVO> memberList = prod.getMemberList();
							if(memberList.isEmpty()){
								%>
								<tr>
									<td colspan="3">구매자가 없음.</td>
								</tr>
								<%
							}else{
								for(MemberVO member : memberList){
									%>
									<tr>
										<td><%=member.getMemName() %></td>
										<td><%=member.getMemAdd1() %></td>
										<td><%=member.getMemMail() %></td>
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
</body>
</html>










