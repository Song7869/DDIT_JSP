<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="preScript"/>


</head>
<body>
<div id="header">
	<tiles:insertAttribute name="header"/>
</div>

<div id="left">
	<tiles:insertAttribute name="left"/>
</div>

<div id="content">
	<tiles:insertAttribute name="content"/>
</div>

<div id="footer">
	<tiles:insertAttribute name="footer"/>
</div>

<tiles:insertAttribute name="postScript" />
</body>
</html>













