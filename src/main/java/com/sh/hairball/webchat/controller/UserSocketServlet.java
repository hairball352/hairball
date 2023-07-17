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
    	
    	String loginMemberName  = req.getParameter("Name");
    	
		List<Member> member = memberService.findAll();

        req.getRequestDispatcher("/WEB-INF/views/webChat/user.jsp").forward(req, resp);

    }
}