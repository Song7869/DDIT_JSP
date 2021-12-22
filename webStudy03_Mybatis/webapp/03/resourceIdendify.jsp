<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/03/resourceIdendify.jsp</title>
</head>
<body>

		<h4> 자원의 종류</h4>
	<pre>
		File System Resource : D:\contents/cat1.jpg
			<%=new File("D:/contents/cat1.jpg").length() %>
			서버 단에서 접근
		
		
		Web Resource : localhost:80/webStudy01/images/cat1.jpg
			url 을 가지고 있다.
			클라이언트 단에서 접근 (유일)
			<%
				URL urlRes = new URL("http://localhost:80/webStudy01/resources/images/cat1.jpg");
				InputStream is = urlRes.openStream();
				out.println(is.available());
				
				is = application.getResourceAsStream("/resources/images/cat1.jpg");
				out.println(is.available());
			%>
			
			서버 단에서 접근
		Class Path Resource : kr/or/ddit/images/cat1.jpg
			<%
				//InputStream cpIs = DescriptionServlet.class.getResourceAsStream("../images/cat1.jpg");
				InputStream cpIs = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/images/cat1.jpg");
				// "/" 이 붙으면 절대경로를 의미한다.
				out.println(cpIs.available());
			%>
			
	
	
	</pre>
	
	<h4>웹 리소스(URL) 경로 표기 방식</h4>
	<pre>
	: 웹 상의 자원을 식별하기 위한 식별 체계 (Identifier) = URI
		ex)URL, URI 
		(Unified Resource)
		
		--이하 도태되었음--
		URN(Name/naming) - 이름으로 찾기, 중복가능성 있음, 이름을 모르면 찾지 못함
		URC(Contents) - 특징으로 찾기, 중복가능성 있음
		
		--이하 사용하고있음--
		URL(Location/locator) - 경로로 찾기, 물리적인 경로
		
		URI(URL, URN, URC) - 상징적인 경로까지 포함 (ex. url-pattern)
	</pre>
	
	<h4>URL</h4>
	<pre>
	URL : 
	protocol : //DN(IP):port/context(name)/depth0...depth/file
	
	웹 리소스의 경로 표기 방식
	client side : 
		절대 경로 표기방식 - 	http://localhost:80/webStudy01/images/cat1.jpg,
										   /webStudy01/images/cat1.jpg,
						<%=request.getContextPath() %>/images/cat1.jpg
						
		상대 경로 표기방식 - 현재클라이언트가 보유한 주소를 기반으로 자원의 절대 경로를 유추함
		
		
	server side : 
		절대 경로 표기 방식 - /images/cat1.jpg
		
		상대 경로 표기 방식 - 존재 할 수 없다.
	
	</pre>
	client side :
	<br>
	절대경로
	<img src="http://localhost:80/webStudy01/resources/images/cat1.jpg"/>
	<br>
	상대경로
	<img src="../images/cat1.jpg"/>
	<br>
	<br>
	server side :
	<br>
	<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg"/>
	
	localhost : origin
	
</body>
</html>






















