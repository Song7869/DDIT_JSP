/**
 * 
 */
$(document).ajaxError(function(event, xhr, settings, error){
	console.log(error);
});
function commonSuccess(resp){
	if(resp.result == "OK"){
		repInsertForm.resetForm();
		replyModal.modal("hide");
		replyChildModal.modal("hide");
		searchForm.submit();
	}else if(resp.message){
		alert(resp.message);
	}
}
let repInsertForm = $("#repInsertForm").ajaxForm({
	dataType:"json",
	success:function(resp){
		if(resp.result == "OK"){
			repInsertForm.resetForm();
			searchForm.submit();
		}else{
			alert(resp.message);
		}
	}
});

// 대댓글
function childReply(event){
	let reply = $(this).parents("tr:first").data("reply");
	let repParent = reply.repNo;
	replyChildForm.get(0).repParent.value = repParent;
	replyChildModal.modal("show");
}
// 수정
function updateReply(event){
	let reply = $(this).parents("tr:first").data("reply");
	for(let prop in reply){
		$(replyUpdateForm).find("[name='"+prop+"']").val(reply[prop]);
	}
	$(replyUpdateForm).find('[name$="pass"]').val("");
	replyModal.modal("show");
}
// 삭제
function deleteReply(event){
	if(!confirm("정말 지울텐가?")) return false;
	let reply = $(this).parents("tr:first").data("reply");
	$(replyDeleteForm).find("[name='repNo']").val(reply.repNo);
	replyDeleteModal.modal("show");
}

let listTable = $("#replyTable").on("click", ".replyBtn", childReply)
 								.on("click", ".updateBtn", updateReply)
								.on("click", ".delBtn", deleteReply)
								.find("#listBody");

let replyModal = $("#replyModal").on("hidden.bs.modal", function(){
	$(this).find("form").get(0).reset();
}).on("shown.bs.modal", function(){
	$(this).find(":text:first").focus().select();
});
let replyChildModal = $("#replyChildModal").on("hidden.bs.modal", function(){
	$(this).find("form").get(0).reset();
}).on("shown.bs.modal", function(){
	$(this).find(":text:first").focus().select();
});
let replyDeleteModal = $("#replyDeleteModal").on("hidden.bs.modal", function(){
	$(this).find("form").get(0).reset();
}).on("shown.bs.modal", function(){
	$(this).find(":password").focus();
});

let options ={
	dataType : "json",
	success :commonSuccess,
	beforeSubmit:function(){
		replyDeleteModal.modal("hide");
	}
}

let replyUpdateForm = replyModal.find("form").ajaxForm(options);
let replyChildForm = replyChildModal.find("form").ajaxForm(options);
let replyDeleteForm = replyDeleteModal.find("form")
						.ajaxForm(options);

let listBody = $("#listBody");
let pagingArea = $("#pagingArea");
let searchForm = $("#searchForm").paging()
				.ajaxForm({
					dataType:"json",
					success:function(resp){
				// 			console.log(resp);
						listBody.empty();
						pagingArea.empty();
						searchForm.find("[name='page']").val("");
						let replyList = resp.dataList;
						let pagingHTML = resp.pagingHTML;
						let trTags = [];
						if(replyList){
							$.each(replyList, function(idx, reply){
								let trTag = $("<tr>").append(
									$("<td>").html(reply.displayContent)
									.addClass("text-left"),
									$("<td>").text(reply.repWriter+"("+reply.repMail+")")
											.addClass("text-center"),
									$("<td>").text(reply.repDate)
											.addClass("text-center"),	
									$("<td>").append(
										$("<input>").attr({
											type:"button",
											value:"덧글"
										}).addClass("btn btn-primary mr-2 replyBtn"),		
										$("<input>").attr({
											type:"button",
											value:"수정"
										}).addClass("btn btn-info mr-2 updateBtn"),		
										$("<input>").attr({
											type:"button",
											value:"삭제"
										}).addClass("btn btn-danger mr-2 delBtn")	
									).addClass("text-center")		
								).data("reply", reply)
								trTags.push(trTag);
							});
							pagingArea.html(pagingHTML);
						}else{
							let trTag = $("<tr>").html(
											$("<td>").attr({
												colspan:"6"
											}).text("덧글이 없음.")		
										);
							trTags.push(trTag);
							
						}
						
						listBody.append(trTags);
					},
					error:function(xhr, jQuery, error){
						console.log(jQuery);
						console.log(error);
					}
				}).submit();