package com.sh.hairball.webchat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.webchat.model.WebChat;
import com.sh.hairball.webchat.model.WebChatService;

@WebServlet("/loadChatHistory")
public class LoadChatHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final WebChatService webChatService = new WebChatService();
    private final MemberService memberService = new MemberService();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		// 로그인한 사용자의 Member 객체를 가져온다
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		System.out.println("loginMember : " + loginMember);
        
        
        if (loginMember == null) {
            // memberId에 해당하는 Member가 없는 경우 에러 처리
        } else {
            int id = loginMember.getId(); // Member 객체의 id를 가져오기
            List<WebChat> chatHistory = webChatService.getChatHistory(id); // id를 이용하여 채팅 기록을 가져오기
            System.out.println("servlet chatHistory : " + chatHistory);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("loginMember", loginMember);
            
            Gson gson = new Gson();
            String chatHistoryJson = gson.toJson(chatHistory);
            System.out.println("chatHistoryJson : " + chatHistoryJson);

            response.getWriter().write(chatHistoryJson);
        }
    }
}