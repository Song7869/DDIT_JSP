<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<form id="factorialForm" method="post">
	<input type="number" name="operand" min="1" max="10" />
	<input type="submit" value="!" />
	<span id="resultArea"><%=request.getAttribute("result") %></span>
</form>
<script type="text/javascript">
	$("#factorialForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		console.log(data);
		$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : "html" , // accept(request) | content-type(response) text/html, text/plain, application/xml, application/json, text/javascript
			success : function(resp) {
				$("#resultArea").html(resp.result);
				
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
		return false;
	});
</script>
</body>
</html>













