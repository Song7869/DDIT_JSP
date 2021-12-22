<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">

	#header{
		height : 100px;
		width : 100%;
		background-color: lightblue;
	}
	
	#left{
		width : 20%;
		height : 500px;
		background-color: aqua;
		float: left;
	}
	
	#content{
		width : 79%;
		height : 500px;
		float: right;
	}
	
	#footer{
		width : 100%;
		height: 100px;
		background-color: lightgreen;
		float: left;
	}




</style>


</head>
<body>
	<div id="header">
		<%-- <iframe src="<%=request.getContextPath() %>/includee/headerMenu.jsp"></iframe> --%>
		<%
			pageContext.include("/includee/headerMenu.jsp");
		%>
	</div>
	
	
	<div id="left">
		<%-- <iframe src="<%=request.getContextPath() %>/includee/leftMenu.jsp"></iframe> --%>
		<jsp:include page="/includee/leftMenu.jsp"></jsp:include>
		<!-- jsp:include -> (액션태그)태그의 모양이지만, 화면에 노출되지 않는다 > 서버사이드 태그이며, pageContext 와 같은 역할 -->
	</div>
	
	
	<div id="conent">
		
	</div>
	
	
	<div id="footer">
		<%-- <iframe src="<%=request.getContextPath() %>/includee/footer.jsp"></iframe> --%>
		<jsp:include page="/includee/footer.jsp"></jsp:include>
	</div>
	
	
</body>
</html>