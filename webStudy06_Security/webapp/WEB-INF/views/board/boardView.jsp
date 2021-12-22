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
			<th>글번호</th>
			<td>${board.boNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boWriter}</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.boIp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.boMail}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boDate}</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td>
				<span>${board.boRep}</span>
				<input type="button" value="신고" id="repBtn" 
						data-url="/board/report.do" data-what="${board.boNo }"/>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boHit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>
			<span>${board.boRec}</span>
			<input type="button" value="추천" id="recBtn"  
					data-url="/board/recommend.do" data-what="${board.boNo }"/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="atch">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${atch.attNo }" />
					</c:url>
					<a href="${downloadURL }">
					${atch.attFilename }
					<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" 
							style="width: 20px; height: 20px;"/>
					</a>		
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boContent}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/boardInsert.do" var="insertURL">
					<c:param name="boParent" value="${board.boNo }" />
				</c:url>
				<input type="button" value="답글쓰기" class="linkBtn"
					data-gopage="${insertURL}"
				/>
				<input type="button" value="글삭제" id="delBtn"/>
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.boNo }" />
				</c:url>
				<input type="button" value="글수정" class="linkBtn"
					data-gopage="${updateURL }"
				/>
			</td>
		</tr>
	</table>
	<form id="deleteForm" action="${cPath }/board/boardDelete.do" method="post">
		<input type="hidden" name="boNo" value="${board.boNo }">
		<input type="hidden" name="boPass">
	</form>
	
<!-- 	덧글 관련 UI -->
<form id="repInsertForm" action="${cPath }/reply/replyInsert.do" method="post">
	<input type="hidden" name="boNo" value="${board.boNo }" />
	<input type="text" name="repWriter" placeholder="작성자" required />
	<input type="text" name="repPass" placeholder="비밀번호" required />
	<input type="text" name="repMail" placeholder="이메일" /><br />
<textarea rows="3" cols="90" name="repContent" placeholder="내용"></textarea>
<input type="submit" value="저장" />
</form>	
	<table>
		<thead>
			<tr>
				<th>일련번호</th>
				<th>덧글 내용</th>
				<th>작성자</th>
				<th>이메일</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="listBody">
		</tbody>
		<tfoot>
		<tr>
			<td id="pagingArea"></td>
		</tr>
		</tfoot>
	</table>
	<form id="searchForm" action="${cPath }/reply/replyList.do">
		<input type="hidden" name="page" />
		<input type="hidden" name="boNo" value="${board.boNo }" />
	</form>
	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/board/reply.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/board/boardView.js"></script>
	
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>



















