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
 * Servlet implementation class AdminChatFindServlet
 */
@WebServlet("/admin/getChatHistory")
public class AdminChatFindServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final WebChatService webChatService = new WebChatService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 memberId 가져오기
        String memberId = request.getParameter("memberId");

        // memberId를 기반으로 채팅 기록 조회
        List<WebChat> chatHistory = webChatService.getChatHistory(memberId);

        Gson gson = new Gson();
        String jsonChatHistory = gson.toJson(chatHistory);

        response.setContentType("application/json");
        response.getWriter().write(jsonChatHistory);
    }
}