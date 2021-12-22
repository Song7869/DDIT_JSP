<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>application(ServletContext)</h4>
		<pre>
			: 서블릿 객체가 운영되는 어플리케이션(context)과 container(WAS) 에 대한 정보를 가진 객체.
			: 하나의 컨텍스트당 하나씩 운영됨.
			
		1. 서버의 정보를 확인
			<%=application.getServerInfo() %>
			<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
			
		2. logging
			<%	// logging 조건 : message(무엇을 기록 할 것인가), layout(기록의 형식/어떻게), appender(어디에다가), logger(전달자), event level 
				application.log("서버 통계 목적으로 남기는 로그");//로그는 클라이언트를 위한 정보가 아니다.
			%>
		
		
		**3. 웹리소스 확보 **
		
			<%
				String srcURL = "/resources/images/cat1.jpg"; // 논리적 경로, 상대적위치
				String srcFileSystemPath = application.getRealPath(srcURL); // 물리적 경로, 진짜 파일의 전체 경로 (루트경로부터 시작)
				File srcFile = new File(srcFileSystemPath);
				
				String destFolderURL = "/09";
				String destFolderFileSystemPath = application.getRealPath(destFolderURL);
				//File destFolder = new File(destFolderFileSystemPath);
				//File destFile = new File(destFolder, srcFile.getName());
				
				Path destPath = Paths.get(destFolderFileSystemPath, srcFile.getName()); //생성될 위치의 파일(아직 없음)
				
				String destFileURL = destFolderURL + "/" + srcFile.getName();
				
				try(
				//InputStream is =  application.getResourceAsStream(srcURL);
				InputStream is = new FileInputStream(srcFile);
						
						
				//OutputStream os = new FileOutputStream(destFile);						
						
				){
					Files.copy(is, destPath); //신기술, 복사 끝남
				}

			%>

			
		4. 컨텍스트 파라미터 확보 (같은 어플리케이션에서는 어디서든 사용이 가능(싱글톤 확인))
			<%=application.getInitParameter("contentFolder")   %> 
		 
			
		</pre>
<img src="<%=request.getContextPath()%><%=destFileURL%>"/>
</body>
</html>