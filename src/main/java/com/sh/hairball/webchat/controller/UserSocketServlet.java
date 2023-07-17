package com.sh.hairball.webchat.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserSocketServlet extends HttpServlet {
	
	private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		// 로그인한 사용자의 Member 객체를 가져온다
		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
    	
		// 로그인한 사용자의 회원 이름을 가져온다
		String loginMemberName  = loginMember.getName();

		// "loginMemberName" JSP로 전달
		req.setAttribute("loginMemberName", loginMemberName);
        req.getRequestDispatcher("/WEB-INF/views/webChat/user.jsp").forward(req, resp);

    }
}