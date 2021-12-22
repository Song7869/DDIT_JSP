package kr.or.ddit.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoWebsocketHandler extends TextWebSocketHandler{
//에코 핸들러 : 클라이언트가 보내는 요청을 그대로 받아서 처리
	// 사실 누가 보내고, 누가 받는지 중요하지 않음 : 서로 실시간으로 주고받기 때문에
	
	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	// onOpen
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("연결 수립 : {}", session.getId());
		sessionList.add(session);
	}
	
	// 닫히면
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("연결 종료 : {}", session.getId());
		sessionList.remove(session);
	}
	
	//에러 발생하면
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.error("연결 에러 발생 : {}", exception);
	}
	
	//메시지 보내면
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> attributes =  session.getAttributes();
		MemberVO authMember = (MemberVO) attributes.get("authMember");
		String sender = null;
		if(authMember==null) {
			sender = "익명";
		}
		else {
			sender = authMember.getMemName();
		}
		
		String receiveMsg = message.getPayload(); // 클라이언트가 보내는 순수한 메시지 데이터
		
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("sender", sender);
		messageMap.put("message", receiveMsg);
		
		String jsonMsg = new ObjectMapper().writeValueAsString(messageMap); //json 형태 메시지 생성
		
		//샌더에게 다시 보냄
		for(WebSocketSession tmp : sessionList) {			
			tmp.sendMessage(new TextMessage(jsonMsg));
		}
	}
}
