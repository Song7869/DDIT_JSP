<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyQuestVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	List<BuyQuestVO> bqList = (List<BuyQuestVO>)request.getAttribute("BQ");
	for(BuyQuestVO bq : bqList){
		%>
		<tr>
			<td><%=bq.getBuyerId() %></td>
			<td><%=bq.getBuyerName() %></td>
		</tr>
		<%
	}
	%>
</body>
</html>