<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	File[] images = (File[])request.getAttribute("images");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		let imageArea = $("#imageArea");
		const SRCPTRN = "${pageContext.request.contextPath }/image.do?image=%v";
		$("[name='image']").on("change", function(event){
			console.log(event);
			console.log(this);
			console.log($(this));
// 			this.form.submit();
// 	 		$(this.form).trigger("submit");
// "<img src='"+value+"'/>"
			let image = $(this).val();
			imageArea.empty();
			if(image){
				let src = SRCPTRN.replace("%v", image);
				let imgTag = $("<img>").attr("src", src)
				imageArea.html(imgTag);			
			}
			return true;
		});
		
		$("form:first").on("submit", function(event){
			console.log(event);
			let value = $(this.image).val();
			console.log(value);
			let valid = true;
			if(!value){
				console.log("파라미터 누락");
				valid = false;
				$(this.image).focus();
			}
	 		return valid;
		});
	});

</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/image.do">
	<select name="image" required>
		<option value>이미지 선택</option>
		<%
			for(File image : images){
				%>
				<option><%=image.getName() %></option>
				<%
			}
		%>
	</select>
</form>
<div id="imageArea"></div>
</body>
</html>
















