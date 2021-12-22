<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
	#header{
		height: 100px;
		width: 100%;
		background-color: yellow;
	}
	#left{
		width: 20%;
		height: 500px;
		background-color: aqua;
		float: left;
	}
	#content{
		width: 79%;
		height: 500px;
		float: right;
	}
	#footer{
		width: 100%;
		height: 100px;
		background-color: silver;
		float: left;
	}
</style>
</head>
<body>
<div id="header">
<%-- 	<iframe src="${pageContext.request.contextPath }/includee/headerMenu.jsp"></iframe> --%>
	<%
		pageContext.include("/includee/headerMenu.jsp");
	%>
</div>
<div id="left">
<%-- 	<iframe src="${pageContext.request.contextPath }/includee/leftMenu.jsp"></iframe> --%>
	<jsp:include page="/includee/leftMenu.jsp" />
</div>
<div id="content">

</div>
<div id="footer">
<%-- 	<iframe src="${pageContext.request.contextPath }/includee/footer.jsp"></iframe> --%>
	<jsp:include page="/includee/footer.jsp" />
</div>
</body>
</html>













