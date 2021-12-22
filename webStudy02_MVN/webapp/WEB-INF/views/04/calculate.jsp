<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	$("#test").on("submit", function(event){
		event.priventDefault();
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : "json",
			success : function(resp) {
				alert("들어옴");
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


<body>
	<form id="test" method="post">
	<!-- 비동기, json -->
		<!-- leftOp, rightOp, operator -->
		<input type="number" name="leftOp"/>
		<select name="operator" required>
			<option value>선	택</option>
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">x</option>
			<option value="/">/</option>
		</select>
		<input type="number" name="rightOp"/>
		<input type="submit" value="계산하기"/>
	</form>
		<!-- 이곳에 연산 식 보여주기 -->
		<span id="resultArea"><%=request.getAttribute("lo") %><%=request.getAttribute("op") %><%=request.getAttribute("ro") %>=<%=request.getAttribute("result") %></span>
</body>
</html>