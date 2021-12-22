<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
	<label><input type="radio" name="dataKind" checked/>파라미터 형태</label>
	<label><input type="radio" name="dataKind" data-content-type="application/json;charset=UTF-8" />JSON 형태</label>
</div>
<form id="calForm" method="post">
<input type="number" name="leftOp" required/>
<select name="operator" required>
	<option value>연산자</option>
	<%
		for( OperatorType tmp : OperatorType.values()){
			%>
			<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
			<%
		}
	%>
</select>
<input type="number" name="rightOp" required />
<button type="submit">=</button>
</form>
<span id="resultArea"><%=Objects.toString(request.getAttribute("expression"), "") %></span>
<!-- 2 * 3 = 6 -->
<script type="text/javascript">
	
	let resultArea = $("#resultArea");
	$("#calForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		// leftOp=3&rightOp=5&operator=PLUS
// 		let data = $(this).serialize(); 
		let data = {};
		let children = $(this).find(":input[name]");
		console.log(children);
		$.each(children, function(index, child){
			let name = this.name;
			let value = $(this).val();
			data[name] = value; 
		});
		console.log(data);
		
		let options = {
			url : url,
			method : method,
			dataType : "json", // Accept(request), Content-Type(response)
			success : function(resp) {
				resultArea.html(resp.expression);
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		};
		
		let contentType = $("[name='dataKind']:checked").data("contentType");
		if(contentType){
			data = JSON.stringify(data);
			options.contentType = contentType;
			options.method = "post";
		}
		options.data = data;
		$.ajax(options);
		return false;
	});
</script>
</body>
</html>
















