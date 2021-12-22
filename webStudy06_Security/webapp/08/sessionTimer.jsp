<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionTimer.jsp</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/sessionTimer.js"></script>
</head>
<body>
<h4>세션 만료 시간 : <span id="sessionTimer"></span></h4>
<div id="msgArea">
	세션 연장할텨?
	<input type="button" id="yesBtn" value="예" class="controlBtn"/>
	<input type="button" id="noBtn" value="아니오" class="controlBtn"/>
</div>
<!-- 1. 세션 타이머 디스카운트 -->
<!-- 2. 1분 남은 시점에 메시지를 통해 연장 여부 확인 -->
<!-- 3. 세션 타이머 종료 조건 처리 -->
<!-- 4. 세션 연장 처리 -->
<script>
	const TIMEOUT = <%=session.getMaxInactiveInterval() %>;
	let msgArea = $("#msgArea");
	let sessionTimer = $("#sessionTimer").sessionTimer({
		timeout:TIMEOUT,
		msgArea:msgArea
	});
</script>
</body>
</html>ㅈ








