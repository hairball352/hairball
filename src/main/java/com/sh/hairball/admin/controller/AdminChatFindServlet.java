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
 * Servlet implementation class AdminChatFindServlet
 */
@WebServlet("/admin/webChatFinder")
public class AdminChatFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String searchKeyword = request.getParameter("searchKeyword");
        System.out.println("serchType = " + searchType);
        System.out.println("serchKeyword = " + searchKeyword);


        List<Member> members = memberService.searchMember(searchType, searchKeyword);
        
        request.setAttribute("members", members);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/webChatList.jsp")
                .forward(request,response);
    }
}
