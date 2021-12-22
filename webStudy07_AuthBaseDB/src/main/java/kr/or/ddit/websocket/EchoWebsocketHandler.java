package kr.or.ddit.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.a;

@Slf4j
public class EchoWebsocketHandler extends TextWebSocketHandler{
	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("연결 수립 : {}", session.getId());
		sessionList.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("연결 종료 : {}", session.getId());
		sessionList.remove(session);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.error("연결 에러 발생", exception);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> attributes = session.getAttributes();
		Authentication authentication = (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		String sender = null;
		if(principal instanceof MemberVOWrapper) {
			MemberVO authMember = ((MemberVOWrapper) principal).getAuthMember();
			sender = authMember.getMemName();
		}else {
			sender = "익명";
		}
		String receiveMsg = message.getPayload();
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("sender", sender);
		messageMap.put("message", receiveMsg);
		String jsonMsg = new ObjectMapper().writeValueAsString(messageMap);
		for(WebSocketSession tmp : sessionList) {
			tmp.sendMessage(new TextMessage(jsonMsg));		
		}
	}
}

















