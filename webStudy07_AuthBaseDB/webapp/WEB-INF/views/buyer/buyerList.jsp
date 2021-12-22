<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  
<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>행번호</th>
			<th>거래처명</th>
			<th>분류명</th>
			<th>소재지</th>
			<th>당담자</th>
			<th>연락처</th>
			<th>이메일</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="buyerList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty buyerList }">
				<c:forEach items="${buyerList }" var="buyer">
					<tr>
						<c:url value="/buyer/buyerView.do" var="viewURL">
							<c:param name="what" value="${buyer.buyerId }"/>
						</c:url>
						<td>${buyer.rnum }</td>
						<td><a class='byAjax' href="${viewURL }">${buyer.buyerName }</a></td>
						<td>${buyer.lprodNm }</td>
						<td>${buyer.buyerAdd1 }</td>
						<td>${buyer.buyerCharger }</td>
						<td>${buyer.buyerComtel }</td>
						<td>${buyer.buyerMail }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="col">거래처 목록이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" value="${param.searchType }"/>
					<input type="hidden" name="searchWord" value="${param.searchWord }"/>
				</form>
				<div id="searchUI" class="row justify-content-center mb-3">
					<div class="col-3">
						<select name="searchType" class="form-control mr-1">
							<option value>전체</option>
							<option value="name" ${"name" eq param.searchType?"selected":""  }>이름</option>
							<option value="address" ${"address" eq param.searchType?"selected":""  }>지역</option>
							<option value="lgu" ${"lgu" eq param.searchType?"selected":""  }>분류</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="searchWord"  class="form-control mr-3"  value="${param.searchWord }"/>
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<security:authorize url="/buyer/buyerInsert.do">
							<input type="button" value="신규등록"  id="createBtn" class="btn btn-primary"/>
						</security:authorize>
					</div>
				</div>	
				<div id="pagingArea"  class="d-flex justify-content-center">${pagingVO.pagingHTML }</div>
			</td>
		</tr>
	</tfoot>
</table>
<script src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">
<security:authorize url="/buyer/buyerInsert.do">
	$("#createBtn").on("click", function(){
		location.href="<c:url value='/buyer/buyerInsert.do' />";
	});
</security:authorize>
	
	$("[name='searchType']").val("${pagingVO.searchVO.searchType}");
	$("[name='searchWord']").val("${pagingVO.searchVO.searchWord}");
	
	$("#searchForm").paging();
</script>
