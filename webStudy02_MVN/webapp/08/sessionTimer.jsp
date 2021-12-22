<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%!SimpleDateFormat ff = new SimpleDateFormat("mm:ss");%>
<head>
<meta charset="UTF-8">
<title>08/sessionTimer.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/sessionTimer.js"></script>
</head>



<body>
	<div>
		세션 생성시간 :<%=new Date(session.getCreationTime())%>
		<br> 세션 만료시간 :<%=session.getMaxInactiveInterval()%>
		<br> 세션 마지막 접근시간 : <span id="lastTime"><%=new Date(session.getLastAccessedTime())%></span>
		<br> 서버시간 : <span id="serverTimer"></span> <br>
		=======================================================================================
	</div>

	<h4>
		세션 만료 시간 : <span id="sessionTimer"></span>
	</h4>
	<div id="msgArea">
		세션을 연장하시겠습니까? <input type="button" id="yesbtn" value="예"
			class="controlBtn"> <input type="button" id="nobtn"
			value="아니요" class="controlBtn">
	</div>

		 1. 세션타이머 디스카운트(업데이트)
	<br> 2. 1분 남은 시점에 메시지를 통해 연장여부를 확인
	<br> 3. 아니오> 타이머 종료조건(-시간이 되지 않도록) -> 로그아웃처리(페이지 새로고침)
	<br> 4. 예> 세션 연장에 관한 처리 -> 서버세션 연장요청(비동기, body없이) -> 타이머 리셋

</body>
<script type="text/javascript">
	const TIMEOUT = <%=session.getMaxInactiveInterval()%>;
	let msgArea = $("#msgArea");
	let sessionTimer = $('#sessionTimer').sessionTimer({
		timeout : TIMEOUT,
		msgArea : msgArea
	});


</script>
</html>














