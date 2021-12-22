<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/flowControl.jsp</title>
</head>
<body>
<h4>웹어플리케이션에서 흐름 제어(페이지 이동, A->B)</h4>
<pre>
	1. Request Dispatch : 원본 요청에 대한 정보를 가지고 분기. 서버사이드 위임구조.
		1) forward : 최종 응답은 이동 이후에 B에서 전송.
		2) include : A가 B에서 만들어지는 결과를 내포하는 구조(서버사이드 페이지 모듈화).
		<%
			String destination = "/07/destination.jsp";
// 			RequestDispatcher rd = request.getRequestDispatcher(destination);
// 			rd.forward(request, response);
// 			rd.include(request, response);
			pageContext.include(destination);
		%>
	2. Redirect : 원본 요청에 대한 정보가 페이지 이동전에 stateless 방식으로 삭제된 후에 이동하는 구조.
			원본 요청 발생(requestA) -> responseA 가 전송(Body 가 없고, 302/Location 만으로 구성)
			-> Location 방향으로 새로운 요청(requestB) 발생 -> responseB 가 최종 전송
		<%--
			String location = request.getContextPath() + "/07/destination.jsp";
			response.sendRedirect(location);
		--%>	
</pre>
</body>
</html>











