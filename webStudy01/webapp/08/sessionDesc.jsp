
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/session.Desc.jsp</title>
</head>
<body>
	<h4>session(HttpSession)</h4>
	<pre>
		: 한 세션에 대한 모든 정보를 가진 객체
		세션이란? 한 클라이언트(하나의 브라우저)가 어플리케이션을 사용하기 시작한 순간부터 사용 종료까지의 기간.
		
		세션 생명주기
		- 생성 : 최초의 요청이 발생 했을 때 (session ID 부여됨.)
			session id : <%= session.getId() %>
			session creation : <%=new Date(session.getCreationTime()) %> <!-- 밀리 세컨 -->
			session timeout : <%=session.getMaxInactiveInterval() %> <!-- 세컨 -->
			session last acced time : <%=new Date(session.getLastAccessedTime()) %>
		- 유지 : (tracking mode, ex) Cookie 형태) : 
				클라이언트와 세션 사이에서 (C/S) 세션 아이디 공유 방법
				
			1. cookie : 브라우저의 쿠키 저장소 이용
				1) JSESSIONID, 쿠키를 구분하기 위한 이름 (변경 가능)

			2. URL : url뒤에 세션의 정보를 담아서 보내는 경우 (; 뒤에 붙음//세션파라미터)
				1) 세션 파라미터(matrix variable)로 세션 유지
					<a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">URL 트래킹모드 확인</a>
			
			3. SSL : 암호화 모듈에 관한 정보를 주고 받는 트래픽 모듈 (https) // (http -> https : 인증서가 필요함)
			
			
			
		- 소멸 (만료 정책) : 
			1. logout(session invalidate) -> 명시적(순간 만료) <%-- <% session.invalidate(); %> --%>
				1) 세션 스코프가 청소됨
				2) invalidate
				3) 세션 만료
			
			2. tracking mode 에 따라 공유되던 session ID (cookie) 를 분실 한 경우 -> timeout
			## 모든 세션은 공통적으로 timeout 이 설정 되어있다. timeout 이내에 새로운 요청이 없으면 session 소멸된다.##
			
			3. 브라우저의 종료 (종료와 동시에 cookie가 삭제된다. -> 2번 상황 발생) // 브라우저 버전에 따라 다름
			
	</pre>
</body>
</html>














