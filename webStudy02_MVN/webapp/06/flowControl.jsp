<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/flowControl.jsp</title>
</head>
<body>
	<h4>웹 어플리케이션에서 흐름제어 (페이지이동, A->B)</h4>
	<pre>
	1. Request Dispatch : 원본요청에 대한 정보를 가지고 이동한다.(server side 위임 구조 // B에게 책임을 떠 넘긴다.)
		1) forward (client ---[request]---> A ---[forward]---> B ---[response]---> client)
			: 최종응답은 이동 이후에 B에서 전송
			
		2) include (client ---[request]---> A ---[include]---> B ---[exclude]---> A ---[response]---> client)
			: A가 B에서 만들어지는 결과를 내포하는 구조(서버사이드 페이지 모듈화 // 여러페이지가 모여서 하나를 이룬다).
			
			<%
				String destination = "/07/destination.jsp";
				RequestDispatcher rd =  request.getRequestDispatcher(destination);
				/* rd.forward(request, response); */
				//rd.include(request, response); // A와 B 모두 출력이 된다. (클라이언트 시점에서는 모든게 A가 처리한 것으로 보임)
				pageContext.include(destination);
			
			%>
			
			
			
		
	2. Redirect
		(client ---[requestA]---> A ---[responseA(302:Moved/Location:B : Body가 없다.)]---> client) --> 
		(client ---[requestB]---> B ---[responseB]---> client)
			: 원본요청에 대한 정보가 페이지 이동 전에 stateless 방식으로 삭제된 후에 이동하는 구조.
			
<%-- 				<%
					String location = request.getContextPath() +"/07/destination.jsp";
					response.sendRedirect(location);
				
				
				%> --%>
		
	</pre>
</body>
</html>
























