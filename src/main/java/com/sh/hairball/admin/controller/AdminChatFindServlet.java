package com.sh.hairball.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

/**
 * Servlet implementation class AdminChatFindServlet
 */
@WebServlet("/admin/webChatFinder")
public class AdminChatFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();
	private final int LIMIT = 10; // 한페이지당 게시물수

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String searchKeyword = request.getParameter("searchKeyword");

		// 1. 사용자입력값 처리
		int cpage = 1; // 기본값처리
		try {
			cpage = Integer.parseInt(request.getParameter("cpage")); 			
		} catch (NumberFormatException e) {
			// 예외처리외에 아무것도 하지 않음.
		}
		int start = (cpage - 1) * LIMIT + 1;
		int end = cpage * LIMIT;
		
        List<Member> memberList = memberService.searchMember(searchType, searchKeyword);
        
        // 페이지바영역 처리
 		int totalContent = memberService.getTotalContent();
 		String url = request.getRequestURI(); 
 		String pagebar = AnimalUtil.getPagebar(cpage, LIMIT, totalContent, url);
        
 		request.setAttribute("memberList", memberList);
     	request.setAttribute("pagebar", pagebar);
     	
        request.getRequestDispatcher("/WEB-INF/views/admin/webChatList.jsp")
                .forward(request,response);
    }
}
