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
				<input type="button" value="신고" id="repBtn" data-url="/board/report.do"/>
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
			<input type="button" value="추천" id="recBtn"  data-url="/board/recommend.do"/>
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
	
	<form id="replyform">		
		<table>
		<tr>
		<th>작성자</th>
		<td><input type="text" name="repWriter" value="작성자" id="repWriter"></td>
		</tr>
		<tr>
		<th>내용</th>
		<td><textarea name="repContent" value="" id="repContent"></textarea></td>
		</tr>
		</table>
		<input type="button" value="작성완료" id="inputBtn">
		<input type="reset" value="취소">
	</form>
	
	
	<table>		
		<thead>
		<h4>덧글 리스트</h4>
			<th>덧글번호</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
		</thead>
		
		<tbody id="listBody">
		
		</tbody>
		
		<div id="pagingArea">
			${pagingVO.pagingHTML }
		</div>
	</table>
	
	<form id="deleteForm" action="${cPath }/board/boardDelete.do" method="post">
		<input type="hidden" name="boNo" value="${board.boNo }">
		<input type="hidden" name="boPass">
	</form>
	<script type="text/javascript">
		let repContent = $("#repContent").val();
		let repWriter = $("#repWriter").val();
	
		$('#inputBtn').on('click', function(){
			$.ajax({
				url : "${cPath }/reply/replyInsert.do",
				data : {
					bo_no : ${board.boNo},
					repWriter : $("#repWriter").val()
					repContent : $(repContent)
				},
				method : "post",
				dataType : "json",
				success : function(resp) {

				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
			
			
		})
		
	
	
	
		let deleteForm = $("#deleteForm");
		let boPassTag = deleteForm.find("[name='boPass']");
		$("#delBtn").on("click", function(){
			let password = prompt("비밀번호");
			if(password){
				boPassTag.val(password);
				deleteForm.submit();
			}
		});
		
		$("#repBtn,#recBtn").on("click", function(){
			let clickBtn = $(this);
			let url = clickBtn.data("url");
			$.ajax({
				url : "${cPath}"+url,
				data : {
					what:${board.boNo}
				},
				dataType : "text",
				success : function(resp) {
					if(resp=="OK"){
						let span = clickBtn.siblings("span:first");
						$(span).text( parseInt($(span).text())+1 );
					}
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		});
		
		let listBody = $("#listBody");

		$(function(){
			$.ajax({
				url : "${cPath }/reply/replyList.do",
				method : "get",
				data : {
					bo_no : ${board.boNo}
				},
				dataType : "json",
				success : function(resp) {
					alert("@@");
					let replyList = resp.replyList;
					let trTags = [];
					
					if(replyList){
						console.log("@@");
						$.each(replyList, function(idx, reply){
							let trTag = $("<tr>").append(
								$("<td>").html(reply.repNo),	
								$("<td>").html(reply.repContent),
								$("<td>").html(reply.repWriter),
								$("<td>").html(reply.repDate)
							);
							trTags.push(trTag);
						});
					}else{
						alert("@@");
						let trTag = $("<tr>").html(
							$("<td>").attr({
								colspan:"6"
							})
						);
						trTags.push(trTag);
					}
					
					listBody.append(trTags);
					pagingArea.html(pagingHTML);
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
				
			})
		});
	</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>



















