<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/bufferDesc.jsp</title>
</head>
<body>
	<h4>출력 버퍼</h4>
	<pre>
		모든 jsp는 기본적으로 페이지를 만들 때 8kb의 버퍼 공간을 만들어둔다. (buffer="8kb")
		(메타데이터 : 상태코드 , 헤더)
		
		
		<%
			for(int i = 1; i <= 500; i++){
				out.print(i+"번째");
				if(out.getRemaining()<100){
					out.flush();
				}
				if(i == 490){
					//throw new NullPointerException("강제 발생 예외입니다.");
					request.getRequestDispatcher("/07/destination.jsp").forward(request, response);
					
				}
			}
		%>
	</pre>
</body>
</html>



























