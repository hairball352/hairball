package com.sh.hairball.webchat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.webchat.model.WebChat;
import com.sh.hairball.webchat.model.WebChatService;

/**
 * Servlet implementation class ChatHistoryServlet
 */
@WebServlet("/saveChatHistory")
public class ChatHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson(); // Gson 객체를 생성
	private final WebChatService webChatService = new WebChatService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장할 본문을 읽는다
		BufferedReader reader = request.getReader();
		String line;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		// 로그인한 사용자의 Member 객체를 가져온다
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		System.out.println("loginMember : " + loginMember);

		// 로그인한 사용자의 회원 ID를 가져온다
		int memberId  = loginMember.getId();
		System.out.println("memberId : " + memberId);

		// 본문을 JSON 문자열로 파싱
		String[] chatHistoryAll = gson.fromJson(builder.toString(), String[].class);

		// 각 채팅 메시지를 데이터베이스에 저장
		for (String message : chatHistoryAll) {
			WebChat webchat = new WebChat();
			webchat.setMemberId(memberId); // 세션에서 가져온 회원 ID를 설정
			webchat.setContent(message); // 채팅 메시지 설정
			webchat.setRegDate(new java.sql.Date(System.currentTimeMillis())); // 현재 시간 설정
			int result = webChatService.insertWebChat(webchat);

		}
	}

}
