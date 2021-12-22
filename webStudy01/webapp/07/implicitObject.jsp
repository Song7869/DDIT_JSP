<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>기본 객체(내장객체)</h4>
	<pre>
		: jsp 에 해당하는 서블릿 소스가 파싱 될 때 컨테이너에 의해 기본 제공되는 객체.
		
		1. request(HttpServletRequest)
		2. response(HttpServletResponse)
		
		3. <a href="bufferDesc.jsp">out(JspWriter)</a> :
			- 출력 스트림으로 버퍼에 응답 데이터를 기록 할때 사용된다.
			- 버퍼의 제어에도 사용된다.
			
		4. <a href="../08/session.Desc.jsp">session(HttpSession) </a>:
			- Cookie
			1) URL
			2) Cookie

		5. <a href="../09/servletContextDesc.jsp">application(ServletContext)</a>
			
		
		6. config(ServletConfig)
		7. page(Object - Jsp 인스턴스, this)
		
		8. exception(Throwable) <!--현재 웹어플리케이션에서 발생한 마지막 에러의 정보를 가지고 있다.-->
			에러 처리 용도의 페이지에서 활성화(isErrorPage = "true")
			
		9.!! <a href="../09/pageContextDesc.jsp">pageContext : 가장 먼저 생성되는 기본 객체. 나머지 기본객체의 참조를 소유함.</a>
		 
	</pre>
</body>
</html>