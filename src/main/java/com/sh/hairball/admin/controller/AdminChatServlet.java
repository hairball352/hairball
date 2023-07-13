package com.sh.hairball.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

/**
 * Servlet implementation class AdminChatServlet
 */
@WebServlet("/admin/webChatList")
public class AdminChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        List<Member> members = memberService.findAll();
        request.setAttribute("members", members);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/webChatList.jsp")
        .forward(request,response);
	}

}
