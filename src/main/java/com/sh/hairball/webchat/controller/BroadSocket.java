package com.sh.hairball.webchat.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//일반 유저에서 서버간의 웹 소켓 url
@ServerEndpoint("/broadsocket")
public class BroadSocket {
	// searchUser 함수의 filter 표현식을 위한 인터페이스
	private interface SearchExpression {
		// 람다식을 위한 함수
		boolean expression(User user);
	}
	// 서버와 유저간의 접속을 key로 구분하기 위한 인라인 클래스
	// 각 사용자는 고유한 세션과 키를 가진다. 그걸로 1:1 채팅을 구분할 수 있음
	
	// Client의 키와 세션을 매핑하는 내부 클래스
	private class User {
		Session session; // WebSocket 세션
		String key; // 유니크 키
	}
	// 유저와 서버간의 접속 리스트
	// 동시성을 보장하기 위해 Collections.synchronizedList를 사용
	private static List<User> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	
	// Session으로 접속 리스트에서 User 클래스를 탐색
	// 접속 리스트에 session을 가지는 유저가 있으면 해당 유저를 반환한다
	private static User getUser(Session session) {
		return searchUser(x -> x.session == session);
	}
	// Key로 접속 리스트에서 User 클래스를 탐색
	// 접속 리스트에 session을 가지는 유저가 있으면 해당 유저를 반환한다
	private static User getUser(String key) {
		return searchUser(x -> x.key.equals(key));
	}
	// 사용자 정의 함수 인터페이스를 이용하여 접속 리스트를 탐색한다
	private static User searchUser(SearchExpression func) {
		Optional<User> op = sessionUsers.stream().filter(x -> func.expression(x)).findFirst();
		// Optional<User>가 값이 있으면 그 값을 반환하고, 없으면 null을 반환한다
		if (op.isPresent()) {
			// 결과를 리턴
			return op.get();
		}
		// 없으면 null 처리
		return null;
	}
	// browser에서 웹 소켓으로 접속하면 호출되는 함수
	@OnOpen
	public void handleOpen(Session userSession) {
		// 새로운 사용자 세션에 대한 User 인스턴스를 생성
		User user = new User();
		// 새로운 고유 키를 생성. Unique키를 발급 ('-'는 제거한다.)
		user.key = UUID.randomUUID().toString().replace("-", "");
		// WebSocket의 세션을 설정
		user.session = userSession;
		// 생성된 User 인스턴스를 접속 리스트에 추가
		sessionUsers.add(user);
		// 운영자 Client에 유저가 접속한 것을 알린다.
		Admin.visit(user.key);
	}
	// browser에서 웹 소켓을 통해 메시지가 오면 호출되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// Session으로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(userSession);
		// 접속 리스트에 User가 있으면
		if (user != null) {
			// 운영자 Client에 유저 key와 메시지를 보낸다.
			Admin.sendMessage(user.key, message);
		}
	}
	// 운영자 client가 유저에게 메시지를 보내는 함수
	public static void sendMessage(String key, String message) {
		// key로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(key);
		// 접속 리스트에 User가 있으면
		if (user != null) {
			try {
				// 유저 Session으로 socket을 취득한 후 메시지를 전송한다.
				user.session.getBasicRemote().sendText(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// WebSocket이 종료가 되면, 종료 버튼이 없기 때문에 유저 브라우저가 닫히면 발생한다.
	@OnClose
	public void handleClose(Session userSession) {
		// Session으로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(userSession);
		// 접속 리스트에 User가 있으면
		if (user != null) {
			// 운영자 Client에 유저 key로 접속 종료를 알린다.
			Admin.bye(user.key);
			// 위 유저 접속 리스트에서 유저를 삭제한다.
			sessionUsers.remove(user);
		}
	}
	// 현재 접속한 모든 사용자의 키를 배열로 반환하는 메소드
	// 유저간의 접속 리스트의 키를 취득하려고 할때.
	public static String[] getUserKeys() {
		// 반환할 String 배열을 선언한다.
		String[] ret = new String[sessionUsers.size()];
		// 유저 리스트를 반복문에 돌린다.
		for (int i = 0; i < ret.length; i++) {
			// 유저의 키만 반환 변수에 넣는다.
			ret[i] = sessionUsers.get(i).key;
		}
		// 값 반환
		return ret;
	}
}