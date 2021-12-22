<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>

	<form method="post" enctype="multipart/form-data">
		<table>
		<tr>
			<th>상품코드</th>
			<td><input type="text" name="prodId" 
				value="${prod['prodId'] }" /><span class="error">${errors['prodId']}</span></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prodName" 
				value="${prod.prodName }" /><span class="error">${errors.prodName }</span></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>
				<select name="prodLgu">
					<option value>상품분류</option>
				</select>
				<span class="error">${errors.prodLgu }</span></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prodBuyer">
				
				</select>
			<span class="error">${errors.prodBuyer }</span></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prodCost" 
				value="${prod.prodCost}" /><span class="error">${errors.prodCost }</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prodPrice" 
				value="${prod.prodPrice}" /><span class="error">${errors.prodPrice }</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prodSale" 
				value="${prod.prodSale}" /><span class="error">${errors.prodSale }</span></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><input type="text" name="prodOutline" 
				value="${prod.prodOutline}" /><span class="error">${errors.prodOutline }</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input type="clob" name="prodDetail"
				value="${prod.prodDetail}" /><span class="error">${errors.prodDetail }</span></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" name="prodImage" accept="image/*" /><span class="error">${errors.prodImage }, ${errors.prodImg }</span></td>
		</tr>
		<tr>
			<th>재고</th>
			<td><input type="number" name="prodTotalstock" 
				value="${prod.prodTotalstock}" /><span class="error">${errors.prodTotalstock }</span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input type="date" name="prodInsdate"
				value="${prod.prodInsdate}" /><span class="error">${errors.prodInsdate }</span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input type="number" name="prodProperstock" 
				value="${prod.prodProperstock}" /><span class="error">${errors.prodProperstock }</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input type="text" name="prodSize"
				value="${prod.prodSize}" /><span class="error">${errors.prodSize }</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prodColor"
				value="${prod.prodColor}" /><span class="error">${errors.prodColor }</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input type="text" name="prodDelivery"
				value="${prod.prodDelivery}" /><span class="error">${errors.prodDelivery }</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prodUnit"
				value="${prod.prodUnit}" /><span class="error">${errors.prodUnit }</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prodQtyin"
				value="${prod.prodQtyin}" /><span class="error">${errors.prodQtyin }</span></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input type="number" name="prodQtysale"
				value="${prod.prodQtysale}" /><span class="error">${errors.prodQtysale }</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prodMileage"
				value="${prod.prodMileage}" /><span class="error">${errors.prodMileage }</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" />
				<input type="reset" value="취소" />
			</td>
		</tr>
		</table>
	</form>
	<script type="text/javascript">
		let prodBuyer = $('[name="prodBuyer"]');
		let prodLgu = $("[name='prodLgu']").on("change", function(){
			let lprodGu = $(this).val();
// 			if(!lprodGu) return false;
			$.ajax({
				url : "<%=request.getContextPath() %>/others/getBuyerList.do",
				data : {
					lprodGu:lprodGu
				},
				dataType : "json",
				success : function(resp) {
					let options = [];
					options.push(
						$("<option>").text("거래처선택")
									.attr("value","")
					);
					$.each(resp, function(idx, buyer){
						options.push(
							$("<option>").text(buyer.buyerName)
										.attr("value",buyer.buyerId)
						);
					});
					prodBuyer.html(options);
					prodBuyer.val("${prod.prodBuyer}");
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		});
		$.ajax({
			url : "<%=request.getContextPath() %>/others/getLprodList.do",
			dataType : "json",
			success : function(resp) {
				let options = [];
				$.each(resp, function(idx, lprod){
					let option = $("<option>")
									.text(lprod.lprodNm)
									.attr("value", lprod.lprodGu);
					options.push(option);
				});
				prodLgu.append(options);
				prodLgu.val("${prod.prodLgu}");
				prodLgu.trigger("change");
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	</script>
	
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>