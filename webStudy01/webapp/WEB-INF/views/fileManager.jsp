<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	File[] images = (File[])request.getAttribute("images");
	File[] images2 = (File[])request.getAttribute("images2");
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
	
	const SRCPTRN = "<%=request.getContextPath() %>/image.do?image=%v";
	
	$("[name='image']").on("change", function(event){
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
		let value = $(this.image).val();
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
<!-- 4번 : 이것을 모델2 로 구현(web-inf 안에 있으니까)
1번
A : /resources/images/* -->
<form action="<%=request.getContextPath() %>/image.do">
	<select size="10" id="srcSelect" required="required" name="image">
		<option value>이미지선택</option>
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
	
	<div>
<!-- 3번 : 이 옵션을 Enum 으로 해보기 -->
		<input type="radio" name="command" value="작업안함" /> 작업안함
		<input type="radio" name="command" value="COPY" /> COPY
		<input type="radio" name="command" value="MOVE" /> MOVE
		<input type="radio" name="command" value="DELETE" /> DELETE
		<input type="submit" name="OK" value="확인!" />
	</div>
	
	
<!-- 2번
B : /09/* 자원의 목록을 제공 -->
	<select size="10" id="destSelect">
		<%
			for(File image2 : images2){
				%>
				<option><%=image2.getName() %></option>
				<%
			}
		%>		
	</select>
	
</body>
</html>