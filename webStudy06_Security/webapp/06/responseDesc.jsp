<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDesc.jsp</title>
</head>
<body>
<h4>response(HttpServletResponse)</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 응답과 관련된 모든 정보를 캡슐화한 객체.
	
	Http 따른 response 패키징 방식
	1. Response Line : response status code(응답 상태 코드)
	   : 명령 처리 결과를 표현하는 세자리 숫자
	   	Http - stateless, connectless
	   1) 100~ : ing...
	   2) 200~ : OK
	   3) 300~ : 처리가 완료되려면, 클라이언트로부터 추가 액션이 필요함.
	   		304(Not Modified), 302/307(Moved)
	   4) 400~ : client side fail
	   		404(Not found), 400(Bad Request),
	   		401<%=HttpServletResponse.SC_UNAUTHORIZED %>, 403<%=HttpServletResponse.SC_FORBIDDEN %>
	   		405<%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>
	   		415<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %>
	   5) 500~ : server side fail, 500(Internal server error)
	   상태 코드 변경시 : response.sendError[sendRedirect]
	    
	2. Response Header : content meta data, setHeader(name, value)
		1) cotent mime setting : Content-Type
			response.setHeader("Content-Type", value)
			response.setContentType(value)
			page 지시자의 contentType 속성
		2) cache control
			<a href="cacheControl.jsp">cacheControl.jsp</a>
		3) auto request 를 통한 페이지 갱신
			<a href="autoRequest.jsp">autoRequest.jsp</a>
		4) flow control
			<a href="flowControl.jsp">flowControl.jsp</a>
	
	3. Response Body(Content Body, Message Body) 
		: request method 가 head 인 경우, body 가 구성되지 않음.
		: redirect 이동 방식의 경우, 중간에 body가 없는 응답이 전송.
</pre>
<img src="${pageContext.request.contextPath }/resources/images/cat1.jpg" />
</body>
</html>









