<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>

<div id="resultArea">

</div>
<input type="button" value="text/plain" class="ctrlBtn" data-data-type="txt"/>
<input type="button" value="application/json" class="ctrlBtn" data-data-type="json"/>

<script type="text/javascript">
	${".ctrlBtn"}.on("click", function(){
		let dataType = $(this).data("dataType");
		$('#resultArea').empty();
		$.ajax({
			url: "${cPath}/dummy/ajax",
			dataType : dataType,
			success : function(resp) {
				$('#resultArea').text(JOSN.stringify(resp));
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	});
	
	
</script>
</body>
</html>