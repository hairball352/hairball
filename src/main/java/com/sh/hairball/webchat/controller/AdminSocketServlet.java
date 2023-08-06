package com.sh.hairball.webchat.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

import java.io.IOException;

@WebServlet("/admin")
public class AdminSocketServlet extends HttpServlet {
	private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		// 로그인한 사용자의 Member 객체를 가져온다
		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		// 로그인한 사용자의 회원 아이디를 가져온다
		String loginMemberId  = loginMember.getMemberId();

		// "loginMemberName" JSP로 전달
		req.setAttribute("loginMemberId", loginMemberId);
        req.getRequestDispatcher("/WEB-INF/views/webChat/admin.jsp").forward(req, resp);
    }
}
