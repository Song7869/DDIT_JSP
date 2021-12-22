<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소(상위주소만)</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
					
				</div>
				<div id="searchUI" class="form-inline">
					<select name="searchType" class="form-control mr-3">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
					<input type="text" name="searchWord" class="form-control mr-3" />
					<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
	<input type="text" name="page" />
</form>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">
	let memberModal = $("#exampleModal").on('show.bs.modal', function(event){
		let relTarget = event.relatedTarget;
		let member = $(relTarget).data("member");
		let modalBody = $(this).find(".modal-body");
		console.log(member);
		$.ajax({
			url : "${pageContext.request.contextPath }/member/memberView.do",
			data : {
				who:member.memId
			},
			dataType : "html",
			success : function(resp) {
				modalBody.html(resp);
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	}).on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	
	let listBody = $("#listBody").on("click", "tr", function(){
		let member = $(this).data("member");
		if(!member) return false;
		let memId = member.memId;
// 		location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
// 		memberModal.modal('show');
	});
	
	let pagingArea = $("#pagingArea");
	
	let searchForm = $("#searchForm").paging()
	.ajaxForm({
		dataType:"json",
		success:function(resp){
// 			console.log(resp);
			listBody.empty();
			pagingArea.empty();
			searchForm.find("[name='page']").val("");
			let memberList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			let trTags = [];
			if(memberList){
				$.each(memberList, function(idx, member){
					let trTag = $("<tr>").append(
						$("<td>").text(member.memId),	
						$("<td>").text(member.memName),	
						$("<td>").text(member.memHp),	
						$("<td>").text(member.memAdd1),	
						$("<td>").text(member.memMail),	
						$("<td>").text(member.memMileage)	
					).data("member", member)
				     .attr({
				    	 "data-toggle":"modal",
				    	 "data-target":"#exampleModal"
				     })
					 .css("cursor", "pointer");
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr({
									colspan:"6"
								}).text("조건에 맞는 회원이 없음.")		
							);
				trTags.push(trTag);
				
			}
			
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		},
		error:function(xhr, jQuery, error){
			console.log(jQuery);
			console.log(error);
		}
	}).submit();

</script>

















