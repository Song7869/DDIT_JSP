<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<jsp:include page="/includee/preScript.jsp" />
	<style type="text/css">
		.send {
			background-color: yellow;
		}
	</style>
</head>
<body>
	<button id="openBtn">웹소켓 연결</button>
	<button id="closeBtn">웹소켓 종료</button>
	<input type="text" id="messageTag" />
	<button id="sendBtn">전송</button>
	<div id="messageArea"></div>
	
	<script type="text/javascript">
	let ws = null;
		$("#openBtn").on("click", function(){
			ws = new WebSocket("wss://192.168.32.45${cPath}/websocket/echo");
			ws.onopen=function(event){ /* 연결 수립이 되면 이벤트 수행  */
				console.log(event);
			}
			ws.onclose=function(event){
				console.log(event);
			}
			ws.onerror=function(event){
				console.log(event);
			}
			ws.onmessage=function(event){/* json 메시지 데이터가 들어옴 언마샬링 해야함*/
				let jsonMsg = event.data;
				let obj = JSON.parse(jsonMsg);
				let receive = obj.sender + ":" + obj.message;
				messageArea.append($("<p>").addClass("receive").text(receive));
			}
		});
		$("#closeBtn").on("click",function(){
			if(ws==null){
				return;
			}
			ws.close();
		});
		let messageTag = $("#messageTag");
		let messageArea = $("#messageArea");
		
		$("#sendBtn").on("click", function(){
			let message = messageTag.val();
			if(!message) return;
			ws.send(message);
			messageArea.append($("<p>").addClass("send").text(message));
			messageTag.val("");
		});
	</script>
</body>
</html>