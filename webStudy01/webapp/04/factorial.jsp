<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
</head>
<body>
<form method="post">
	<input type="number" name="operand" min="1" max="10" />
	<input type="submit" value="!" />
	<%=request.getAttribute("result") %>
</form>
</body>
</html>













