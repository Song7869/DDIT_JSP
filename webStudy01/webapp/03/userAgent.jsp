<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <%! public enum Brow {Edg, Whale, Chrome, Safari}; %>
 <%
 Brow Edge = Brow.Edg;
 Brow Whale = Brow.Whale;
 Brow Chrome = Brow.Chrome;
 Brow Safari = Brow.Safari;
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
	<h4>User-Agent Header</h4>
	<%
		
	
	
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerValue ="";
		while(headerNames.hasMoreElements()){
			String headername = headerNames.nextElement();
			if(headername.equals("user-agent")){
				headerValue = request.getHeader(headername);				
			}
			
		}
		
		
	%>
			<%=headerValue%>
	<br>
	<%
	if (!headerValue.contains(Chrome.name()) && !headerValue.contains(Safari.name())){
		%>당신의 브라우져는 <h1>인터넷 익스플로러</h1> 입니다.<%
	}
	else if (!headerValue.contains(Chrome.name()) && headerValue.contains(Safari.name())){
		%>당신의 브라우져는 <h1>사파리</h1> 입니다.<%
	}
	
	
	if (headerValue.contains(Chrome.name()) && !headerValue.contains(Edge.name()) && !headerValue.contains(Whale.name())){
		%>당신의 브라우져는 <h1>크롬</h1> 입니다.<%
	}
	
	else if(headerValue.contains(Chrome.name())==true && headerValue.contains(Edge.name())==true){
		%>당신의 브라우져는 <h1>엣지</h1> 입니다.<%
	}
	
	else if(headerValue.contains(Chrome.name())==true && headerValue.contains(Whale.name())==true){
		%>당신의 브라우져는 <h1>네이버 웨일</h1> 입니다.<%
	}
	



	%>
</body>
</html>