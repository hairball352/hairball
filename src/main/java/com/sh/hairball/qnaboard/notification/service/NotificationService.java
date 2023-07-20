package com.sh.hairball.qnaboard.notification.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.notification.QuestionWebSocket;
import com.sh.hairball.qnaboard.notification.dao.NotificationDao;
import com.sh.hairball.qnaboard.notification.model.MessageType;

/**
 * 
 * 알림 요청이 있을때마다
 * - 1. db notification테이블 저장 (생략)
 * - 2. HelloWebSocket.clientMap에서 해당 사용자를 찾아서 실시간 알림처리
 *
 * honggd 게시글 작성 - sinsa 해당 게시글에 댓글 작성 - honggd 게시글 댓글 알림
 */
public class NotificationService { // NotificationService클래스는 알림처리 미 실시간알림메세지 전송기능을 한다. 
	
	/**
	 * 1. db저장
	 * 2. 실시간 알림
	 */
	
	public int notifyNewBoardComment(QuestionVo question) { // 메소드는 새로운 게시글 댓글이 달릴 때 알림을 처리하는 메소드
		// 1. 저장
//		NotificationDao notificationDao = new NotificationDao();
//		int result = notificationDao.insertNotification(conn, notification);
		// 2. 실시간 알림
		// WebSocket Session 가져오기
		Session wsSession = QuestionWebSocket.clientMap.get(question.getMemberId()); // QuestionWebSocket.clientMap에서 해당 사용자의 WebSocket 세션을 가져옴.
		System.out.println("wsSession : "+ wsSession);
		if(wsSession != null) { // 실시간 접속 여부
			Basic basic = wsSession.getBasicRemote(); // Basic 객체를 사용하여 실시간 알림 메시지를 보냄
			System.out.println("basic : " + basic);

			try {
				Map<String, Object> payload = new HashMap<>(); // payload라는 맵 객체에 필요한 정보를 담아 JSON 형태로 변환
				payload.put("messageType", MessageType.NEW_ANSWER);
				payload.put("receiver", question.getMemberId());
				payload.put("createdAt", System.currentTimeMillis());
				payload.put("message", question.getTitle() + "(" + question.getId() + "번) 질문에 답변이 달렸습니다.");
				basic.sendText(new Gson().toJson(payload));
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
}