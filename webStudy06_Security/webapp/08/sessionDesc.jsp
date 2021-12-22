<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionDesc.jsp</title>
</head>
<body>
<h4>session(HttpSession)</h4>
<pre>
	: 한 세션에 대한 모든 정보를 가진 객체.
	세션이란? 한 클라이언트(하나의 브라우저)가 어플리케이션을 사용하기 시작한 순간부터 사용 종료까지의 기간.
	세션 생명주기
	생성 : 최초의 요청이 발생했을 때(session ID 부여됨.).
		session id : <%=session.getId() %>
		session creation : <%=new Date(session.getCreationTime()) %>
		session timeout : <%=session.getMaxInactiveInterval() %>s
		session last accessed time : <%=new Date(session.getLastAccessedTime()) %>
	유지 (tracking mode, ex) Cookie 형태) : C/S 사이에 세션 아이디 공유 방법
		1. COOKIE : JSESSIONID(변경 가능)
		2. URL : 세션 파라미터(matrix variable)로 세션 유지
			<a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">URL 트래킹모드 확인</a>
		3. SSL
	소멸(만료 정책)
		1. logout(session invalidate) <%-- session.invalidate();  --%>
		2. tracking mode 에 따라 공유되던 session ID 를 분실.
		3. 브라우저 종료
		** timeout 정책에 따라 만료 시간 결정 - timeout 이내에 새로운 요청이 없으면 소멸.
</pre>
</body>
</html>











