<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<table class="table table-bordered">
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
				<input type="button" value="답글쓰기" class="linkBtn btn btn-primary"
					data-gopage="${insertURL}" 
				/>
				<input type="button" value="글삭제" id="delBtn" class="btn btn-danger"/>
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.boNo }" />
				</c:url>
				<input type="button" value="글수정" class="linkBtn btn btn-secondary"
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
<form method="post" class="form-inline" id="repInsertForm"
	action="${pageContext.request.contextPath }/reply/replyInsert.do">
	<input type="hidden" name="repNo" />
	<input type="hidden" name="boNo" value="${board.boNo }"/>
	<table class="col-md-10">
		<tr>
			<td>
				<input type="text" class="form-control mb-2" name="repWriter" placeholder="작성자" maxlength="15"/>
			</td>
			<td>
				<input type="password" class="form-control mb-2" name="repPass" placeholder="비밀번호"/>
			</td>
			<td>
				<input type="email" class="form-control mb-2" name="repMail" placeholder="이메일" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="repContent"></textarea>
				</div>
			</td>
			<td colspan="3">
				<input type="submit" value="전송" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form>

<h4>댓글리스트 (비동기)</h4>
<table id="replyTable" class="table table-bordered">
	<thead class="table-dark">
		<tr>	
			<th class="text-center">내용</th>
			<th class="text-center">작성자</th>
			<th class="text-center">작성일</th>
			<th class="text-center">&nbsp;</th>
		</tr>
	</thead>
	<tbody id="listBody" class="overflow-auto">
	
	</tbody>
</table>
<div id="pagingArea"></div>
<form id="searchForm" action="${cPath }/reply/replyList.do" method="get">
	<input type="hidden" name="boNo" value="${board.boNo }" />
	<input type="hidden" name="page"  />
</form>
<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${pageContext.request.contextPath }/reply/replyUpdate.do" method="post">
      	<input type="hidden" name="repNo" required/>
      	<input type="hidden" name="boNo"  required value="${board.boNo }"/>
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<input type="text" required name="repWriter" class="form-control" placeholder="작성자" />
	      			</td>
	      			<td>
	      				<input type="password" required name="repPass" class="form-control" placeholder="비밀번호"/>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="repContent"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade" id="replyChildModal" tabindex="-1" aria-labelledby="replyChildModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyChildModalLabel">대댓글</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${cPath }/reply/replyInsert.do" method="post">
      	<input type="hidden" name="repParent" required/>
      	<input type="hidden" name="boNo"  required value="${board.boNo }"/>
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<input type="text" required name="repWriter" class="form-control" placeholder="작성자" />
	      			</td>
	      			<td>
	      				<input type="password" required name="repPass" class="form-control" placeholder="비밀번호"/>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
	      				<input type="text" class="form-control mb-2" name="repMail" placeholder="이메일" />
					</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="repContent"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">등록</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade" id="replyDeleteModal" tabindex="-1" aria-labelledby="replyDeleteModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${pageContext.request.contextPath }/reply/replyDelete.do" method="post">
      	<input type="hidden" name="repNo" required/>
      	<input type="hidden" name="boNo"  required value="${board.boNo }"/>
	      <div class="modal-body">
   				<input type="password" required name="repPass" class="form-control" placeholder="비밀번호"/>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">삭제</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/board/reply.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/board/boardView.js"></script>



















