<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/scopeDesc.jsp</title>
</head>
<body>

	<h4>웹 어플리케이션의 영역(Scope)와 속성(Attribute)</h4>
	<pre>
		scope(저장공간)
		각 공간에 메모리 공간을 제공하고, 객체를 마치 전역변수마냥 사용 할 수 있게 해준다. (라이프 사이클에 맞춰서)
		각 scope 내부에 map을 하나씩 가지고 있다. 
		--> setAttribute(name, value)의 형태(Map), getAttribute(name)을 통해 꺼냄
		--> removeAttribute(name)을 통해 삭제한다.
		--> getAttributeNames()을 통해 키값만 싹 가져올 수 있다.
		
		: 4가지의 기본객체가 각자 관리하고 있는 map 형태의 데이터 공유공간. 관리 기본객체와 라이프사이클이 동일하다.
		
		
		1. page scope : 한페이지 안에서만 유혀성을 가지고 있다.
			pageContext 가 관리, pageContext Map %lt;String, Object%gt; 
			
		2. request scope : 중간에 응답데이터가 나가면 소멸된다.
			requestContext Map %lt;String, Object%gt;
		
		3. session scope : 세션이 유지되는 동안 같이 유지된다. // 데이터가 계속 쌓인다.-> 서버에 부하 -> 데이터의 한계점을 지정 할 수 있다.
			session Map %lt;String, Object%gt;
		
		4. application scope : // 데이터가 계속 쌓인다.-> 서버에 부하 -> 데이터의 한계점을 지정 할 수 있다. 
			-> 이건 싱글톤이야
		serveltContext Map %lt;String, Object%gt;
		
		<a href="<%=request.getContextPath()%>/07/destination.jsp">destination 이동</a>
	</pre>
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
		//pageContext.include("/07/destination.jsp");
		response.sendRedirect(request.getContextPath()+"/07/destination.jsp");
	%>
	
</body>
</html>



























