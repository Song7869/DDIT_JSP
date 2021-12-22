<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/pageContextDesc.jsp</title>
</head>
<body>
	<h4>pageContext(PageContext)</h4>
	하나의 페이지를 놓고 봤을 때 그녀석을 둘러싸고있는 것
	-> 현재페이지의 모든것을 가져올 수 있다.
	
	<pre>
		: 나머지 객체의 참조 소유자.
		<%=request == pageContext.getRequest() %> 주소만 확인할거라서 (==)를 사용
		--> true 둘의 주소가 같다.
		
		<%=response == pageContext.getResponse() %>
		--> 봐 이것도 true
		
		==> 오늘까지 배운 모든 기본객체들은 pageContext 를 이용해서 다 할 수 있어.
		(심지어 다른 언어에 자바의 기본객체가 없어도, pageContext 는 있기 때문에, 모두 사용 가능하다.)
		
		
		
		활용
		1. 속성 데이터 관리 <% pageContext.setAttribute("sample", "value", PageContext.SESSION_SCOPE); %> (스코프 숫자를 몰라도 저렇게 하면 꺼낼 수 있어)
		-> getAttribute 역시 지원한다.
		
		2. flow control (포워드, 인클루드)
		<%
			String destination = "/07/destination.jsp";
		
			//방법1)
			//request.getRequestDispatcher(destination).forward(request, response);
			
			//방법2)	
			//pageContext.forward(destination);
			
			// 둘 다 같은 결과가 나오지만 방법2가 더 간단해.
			
			
			//방법1)
			//request.getRequestDispatcher(destination).include(request, response);
			// 방법1로 인클루드 하면 인클루드의 출력위치를 예측 할 수 없다.
			
			//방법2)	
			pageContext.include(destination);
			// 방법2로 인클루드 하면 인클루드의 출력 위치가, 인클루드를 호출하는 부분에 출력되게 된다.
			// 1-2-3-5-6-4 (응답순서가 일정하게 유지)

		%> 
		이 라인이 어디에 출력되나?
		
		
		
		
----------------------------------------------------------------------------
		3. 에러데이터 확보 : <%= exception == pageContext.getException()  %>
		
		
		
		
		
	</pre>
	
	
</body>
</html>























