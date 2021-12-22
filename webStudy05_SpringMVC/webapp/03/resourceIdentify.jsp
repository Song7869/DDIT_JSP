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
<title>03/resourceIdentify.jsp</title>
</head>
<body>
<h4> 자원 (Resource)의 종류 </h4>
<pre>
	file system resource : D:\contents\cat1.jpg
	<%=new File("D:/contents/cat1.jpg").length() %>
	web resource : localhost:80/webStudy01/images/cat1.jpg
	<%
		URL urlRes = new URL("http://localhost:80/webStudy01/resources/images/cat1.jpg");
		InputStream is = urlRes.openStream();
		out.println(is.available());
		
		is = application.getResourceAsStream("/resources/images/cat1.jpg");
		out.println(is.available());
	%>
	class path resource : kr/or/ddit/images/cat1.jpg
	<%
// 		InputStream cpIs = DescriptionServlet.class.getResourceAsStream("../images/cat1.jpg");
		InputStream cpIs = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/images/cat1.jpg");
		out.println(cpIs.available());
	%>
</pre>
<h4>웹 리소스 경로 표기 방식</h4>
<pre>
	:웹 상의 자원을 식별하기 위한 식별 체계(URI)
	URN(naming)
	URC(contents)
	URL(locator)
	protocol://DN(IP):port/context/depth0...n/file
	
	웹리소스의 경로 표기 방식
	client side 
		절대 경로 : http://localhost:80/webStudy01/images/cat1.jpg, ${pageContext.request.contextPath }/images/cat1.jpg
		상대 경로 : 현재 클라이언트가 보유한 주소를 기반으로 자원의 절대 경로를 유추함.
	server side(절대 경로)  : /images/cat1.jpg
</pre>
client side :
<img src="http://localhost:80/webStudy01/resources/images/cat1.jpg" />
client side :
<img src="${pageContext.request.contextPath }/resources/images/cat1.jpg" />
<img src="../images/cat1.jpg" />
<img src="${pageContext.request.contextPath }/image.do?image=cat1.jpg" />
</body>
</html>



















