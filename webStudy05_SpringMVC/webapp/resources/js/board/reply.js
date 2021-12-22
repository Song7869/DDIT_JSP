/**
 * 
 */

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
									$("<td>").text(reply.rnum),	
									$("<td>").text(reply.repContent),	
									$("<td>").text(reply.repWriter),	
									$("<td>").text(reply.repMail),	
									$("<td>").text(reply.repDate),	
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