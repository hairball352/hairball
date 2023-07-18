package com.sh.hairball.qnaboard.notification;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.sh.hairball.member.model.vo.Member;

public class QuestionWebSocketConfigurator extends Configurator{
	/**
	 * 사용자연결을 위한 WebSocket용 Session객체 생성시 메소드를 호출
	 * - HttpSession에 등록된 loginMember 객체의 memberId를 가져와서
	 * - ServerEndpointConfig객체의 properties 맵 객체에 저장.
	 * - Endpoint클래스의 @OnOpen 메소드에서 사용할 수 있다.
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		System.out.println("QuestionWebSocketConfigurator#modifyHandshake 실행");
		HttpSession httpSession = (HttpSession)request.getHttpSession(); 
		// HandshakeRequest를 통해 HttpSession 객체를 가져옴
		
		// memberId 관리
		Member loginMember = (Member) httpSession.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		// HttpSession에서 "loginMember"라는 속성으로 저장된 로그인 사용자 정보를 가져옴
		
		Map<String, Object> configProperties = sec.getUserProperties();
		configProperties.put("memberId", memberId);
		// 맵 객체에 "memberId"라는 키로 memberId를 저장.
		
		// QuestionWebSocketConfigurator 클래스는 WebSocket 연결 시 
		// HttpSession에서 로그인 사용자의 memberId를 가져와서
		// WebSocket세션 객체의 properties에 저장하는 역할
		
		
	}
}
