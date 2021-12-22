<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.css"> 
<script src="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.js"></script>
<form:form commandName="buyer" method="post" action="${cPath }${currentAction }" enctype="multipart/form-data">
	<form:hidden path="buyerId" />
<table class="table">
	<tr class="row">
		<th class="col-3">거래처명</th>
		<td class="col-9">
			<form:input path="buyerName" cssClass="form-control"/>
			<form:errors path="buyerName" element="label" cssClass="error" for="buyerName" id="buyerName-error" />
			
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">거래처분류코드</th>
		<td class="col-9">
			<form:select path="buyerLgu" cssClass="form-control" required="required">
				<c:forEach items="${lprodList }" var="lprod">
					<form:option value="${lprod.lprod_gu }" label="${lprod.lprod_nm }" />
				</c:forEach>
			</form:select>
			<form:errors path="buyerLgu" element="label" cssClass="error" for="buyerLgu" id="buyerLgu-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">은행명</th>
		<td class="col-9">
			<form:input path="buyerBank" cssClass="form-control" />
			<form:errors path="buyerBank" element="label" cssClass="error" for="buyerBank" id="buyerBank-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌번호</th>
		<td class="col-9">
			<form:input path="buyerBankno" cssClass="form-control"/>
			<form:errors path="buyerBankno" element="label" cssClass="error" for="buyerBankno" id="buyerBankno-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌주</th>
		<td class="col-9">
			<form:input path="buyerBankname" cssClass="form-control"/>
			<form:errors path="buyerBankname" element="label" cssClass="error" for="buyerBankname" id="buyerBankname-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">우편번호</th>
		<td class="col-9">
			<div class="input-group">
				<form:input path="buyerZip" cssClass="form-control" maxLength="7" pattern="[0-9]{3}-[0-9]{3}" readonly=""
						data-msg-="우편번호 필수" data-msg-pattern="형식확인"/>
				<form:errors path="buyerZip" element="label" cssClass="error" for="buyerZip" id="buyerZip-error" />
				<form:button id="zipBtn" cssClass="btn btn-primary" data-bs-toggle="modal" data-bs-target="#zipModal">우편번호 검색</form:button>
			</div>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">주소1</th>
		<td class="col-9">
			<div class="form-group">
				<form:input path="buyerAdd1" cssClass="col form-control" maxLength="80" readonly=""  data-msg="주소 필수" />
				<form:errors path="buyerAdd1" element="label" cssClass="error" for="buyerAdd1" id="buyerAdd1-error" />
			</div>		
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">주소2</th>
		<td class="col-9">
			<div class="form-group">
				<form:input path="buyerAdd2" cssClass="col form-control" maxLength="80" readonly=""  data-msg="주소 필수" />
				<form:errors path="buyerAdd2" element="label" cssClass="error" for="buyerAdd2" id="buyerAdd2-error" />
			</div>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">회사전번</th>
		<td class="col-9">
			<form:input path="buyerComtel" cssClass="form-control"/>
			<form:errors path="buyerComtel" element="label" cssClass="error" for="buyerComtel" id="buyerComtel-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">팩스번호</th>
		<td class="col-9">
			<form:input path="buyerFax" cssClass="form-control"/>
			<form:errors path="buyerFax" element="label" cssClass="error" for="buyerFax" id="buyerFax-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">이메일</th>
		<td class="col-9">
			<form:input path="buyerMail" cssClass="form-control" />
			<form:errors path="buyerMail" element="label" cssClass="error" for="buyerMail" id="buyerMail-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">담당자명</th>
		<td class="col-9">
			<form:input path="buyerCharger" cssClass="form-control"/>
			<form:errors path="buyerCharger" element="label" cssClass="error" for="buyerCharger" id="buyerCharger-error" />
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">내선번호</th>
		<td class="col-9">
			<form:input path="buyerTelext" cssClass="form-control"/>
			<form:errors path="buyerTelext" element="label" cssClass="error" for="buyerTelext" id="buyerTelext-error" />
		</td>
	</tr>
	<tr class="row">
		<td class="col text-center">
			<input type="submit" value="저장" class="btn btn-primary"/>
			<input type="reset" value="취소" class="btn btn-warning"/>
			<a class="btn btn-secondary" href='<c:url value="/buyer/buyerList.do"/>'>목록으로</a>
		</td>
	</tr>
</table>
</form:form>
<script src="${pageContext.request.contextPath }/resources/js/searchZip.js"></script>
<script type="text/javascript">
	$("[name='buyerLgu']").val("${buyer.buyerLgu }");
	$("#zipBtn").searchZip({
		zipCodeTag : "[name='buyerZip']",
		add1Tag : "[name='buyerAdd1']",
		add2Tag : "[name='buyerAdd2']",
		searchURL : "${pageContext.request.contextPath }/zip/searchZip.do"
	});
	$.searchZip({
		zipCodeTag:$("[name='buyerZip']").get(0),
		add1Tag:$("[name='buyerAdd1']").get(0),
		add2Tag:$("[name='buyerAdd2']").get(0)
	});
</script>
