package com.sh.hairball.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.webchat.model.WebChat;
import com.sh.hairball.webchat.model.WebChatService;

/**
 * Servlet implementation class ChatHistoryServlet
 */
@WebServlet("/admin/getChatHistory")
public class ChatHistoryFindServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final WebChatService webChatService = new WebChatService();
    private final MemberService memberService = new MemberService();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId"); // memberId 문자열

        Member member = memberService.findById(memberId); // Member 객체

        
        if (member == null) {
            // memberId에 해당하는 Member가 없는 경우 에러 처리
        } else {
            int id = member.getId(); // Member 객체의 id를 가져오기
            List<WebChat> chatHistory = webChatService.getChatHistory(id); // id를 이용하여 채팅 기록을 가져오기

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String chatHistoryJson = gson.toJson(chatHistory);

            response.getWriter().write(chatHistoryJson);
        }
    }
}
