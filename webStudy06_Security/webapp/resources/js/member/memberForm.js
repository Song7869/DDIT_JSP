/**
 * 
 */
let memberForm = $("#memberForm").on("submit", function(){
	let idValid = $(this).data("idValid");
	let pass = true;
	if(!idValid){
		alert("아이디 중복 여부 확인!");
		pass = false;
	}
	return pass; 
});
$("#checkBtn").on("click", function(){
	let memId = $("[name='memId']").val();
	$.ajax({
		url : $.getContextPath() + "/member/idCheck",
		data : {
			"memId":memId
		},
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.result=='PKDUPLICATED'){
				alert(resp.message);
				$("[name='memId']").focus();
			}else{
				console.log(resp.message);
				memberForm.data("idValid", true);
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
});