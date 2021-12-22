<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDesc.jsp</title>
</head>
<body>

	<h4>response (HttpServletResponse)</h4>
	<pre>
	 : 서버에서 클라이언트로 전송되는 응답과 관련된 모든 정보를 캡슐화 한 객체.
	 
	 Http 따른 response 패키징 방식
	 1. Response Line
	 	- response status code (응답상태 코드)
	 		: 명령처리 결과를 표현하는 세자리 숫자
	 			Http - statless, connectless
	 			
	 		1) 100 ~ : ing... (아직 처리가 끝나지 않음/ 연결이 끊어지지 않음) websocket의 경우 (connect Full)
	 		
	 		2) 200 ~ : OK
	 		
	 		3) 300 ~ : 처리가 완료되려면, 클라이언트로부터 추가 액션이 필요함.
	 			- 304(Not Modified) : 최신 상태가 아닌 경우
	 			- 302/307(Moved) : redirect, 위치가 변경된 경우
	 			
	 		4) 400 ~ : client side fail 인 경우
	 			- 400<%=HttpServletResponse.SC_BAD_REQUEST %>(Bad Request) / server-side validation
	 			- 401<%=HttpServletResponse.SC_UNAUTHORIZED %> : 허가받지 못한 접근
	 			- 403<%=HttpServletResponse.SC_FORBIDDEN %> : 금지된 접근
	 			- 404<%=HttpServletResponse.SC_NOT_FOUND %>(Not found)
	 			- 405<%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %> : post, get 이 불일치
	 			- 415<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %> : 요청/응답 타입(mime) 불일치
	 		
	 		5) 500 ~ : server side fail 인 경우
	 			- 500(Internal server error)
	 	
			상태코드 변경시 :response.sendError[sendRedirect]
	 	
	 2. Response Header
	 	- content meta data, setHeader(name, value)
	 	1) content mime setting : Content-Type
	 			- response.setHeader("Content-Type", "text/plain;charset=UTF-8");
	 			- response.setContentType(value)
	 			- page 지시자의 contentType 속성
	 			
	 	2) auto request 를 통한 페이지 갱신
	 		<a href="autoRequest.jsp"></a>
	 	3) cache control
	 		<a href="cacheContol.jsp"></a>
	 	4) flow control
	 		<a href="flowControl.jsp">flowControl.jsp</a>
	 	
	 3. Response Body (Content Body, Message Body)
	 	- request method 가 head 인 경우, body 가 구성되지 않음.
	 	- redirect 의 이동방식인 경우, 중간에 body가 없는 응답이 전송된다.
	 	
	 
	</pre>
	<img src ="<%=request.getContextPath() %>/resources/images/cat2.png" />
</body>
</html>