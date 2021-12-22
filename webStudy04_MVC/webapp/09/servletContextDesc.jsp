<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/servletContextDesc.jsp</title>
</head>
<body>
<h4>application(ServletContext)</h4>
<pre>
	: 서블릿 객체가 운영되는 어플리케이션(context)과  container(WAS) 에 대한 정보를 가진 객체.
	: 하나의 컨텍스트당 하나씩 운영됨.
	
	1. 서버의 정보를 확인
		<%=application.getServerInfo() %>
		<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	2. logging
		<%	// logging 조건 : message, layout, appender, logger, event level 
			application.log("서버 통계 목적으로 남기는 로그");
		%>
	3(*****). 웹리소스 확보
		<%
			String srcURL = "/resources/images/cat1.jpg";
			String srcFileSystemPath = application.getRealPath(srcURL);
			File srcFile = new File(srcFileSystemPath);
			String destFolderURL = "/09";
			String destFolderFileSystemPath = application.getRealPath(destFolderURL);
// 			File destFolder = new File(destFolderFileSystemPath);
// 			File destFile = new File(destFolde-r, srcFile.getName());
			Path destPath = Paths.get(destFolderFileSystemPath, srcFile.getName());
			String destFileURL = destFolderURL + "/" + srcFile.getName();
			try(
// 				InputStream is = application.getResourceAsStream(srcURL);
				InputStream is = new FileInputStream(srcFile);	
// 				OutputStream os = new FileOutputStream(destFile);	
			){
				Files.copy(is, destPath);
			}
		%>
		<%=srcURL %>
		<%=srcFileSystemPath %>
	4. 컨텍스트 파라미터 확보 
		<%=application.getInitParameter("contentFolder") %>
</pre>
<img src="${pageContext.request.contextPath }<%=destFileURL %>" />
</body>
</html>















