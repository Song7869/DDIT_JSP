<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestHeaderDesc.jsp</title>
</head>
<body>
<h4> 요청 헤더(meta data) </h4>
<pre>
	: 요청과 클라이언트에 대한 부가적인 수사 정보(meta data)
	<%
		Enumeration<String> headerNames = request.getHeaderNames();
		StringBuffer headers = new StringBuffer();
		String ptrn = "<tr><td>%s</td><td>%s</td></tr>";
		while(headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			headers.append(String.format(ptrn, headerName, headerValue));
		}
	%>
</pre>
<table>
	<thead>
		<tr>
			<th>요청헤더명</th>
			<th>요청헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%=headers %>
	</tbody>
</table>
</body>
</html>











