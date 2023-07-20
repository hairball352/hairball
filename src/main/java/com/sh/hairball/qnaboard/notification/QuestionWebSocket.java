package com.sh.hairball.qnaboard.notification;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/QuestionWebSocket", configurator = QuestionWebSocketConfigurator.class) 
public class QuestionWebSocket { // QuestionWebSocket클래스는 ClientMap 
	
	// WebSocket 세션을 관리할 맵(멀티스레드에 사용하므로 동기화처리 필수)
	// (키:벨류) memberId : String = websocketSession : Session
    // HelloWebSocketConfigurator* 웹 소켓 
	// HTTP 세션 세팅하기 위한 클래스 Configurator
	public static Map<String, Session> clientMap = 
			Collections.synchronizedMap(new HashMap<>());
	
	// WebSocket 연결이 열릴 때 호출되는 메소드
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("open");
		Map<String, Object> configProperties = config.getUserProperties(); // 사용자 속성(properties) 맵을 가져옴
		String memberId = (String) configProperties.get("memberId"); // "memberId"라는 키에 해당하는 값을 가져옴
		
		// 1. clientMap에 저장
		clientMap.put(memberId, session);
		
		// 2. WebSocket Session객체 properties 맵 객체에 memberId 저장(@OnClose에서 사용)
		Map<String, Object> sessionProperties = session.getUserProperties();
		sessionProperties.put("memberId", memberId);
	}
	
	@OnMessage
	public void OnMessage(String message, Session session) {
		System.out.println("message : " + message);
	}
	
	@OnError
	public void OnError(Throwable e) {
		System.out.println("error");
		e.printStackTrace(); 
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("close");
		
	}

	
}
