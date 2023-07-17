//package com.sh.hairball.admin.controller;
//
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.sh.hairball.common.util.AnimalUtil;
//import com.sh.hairball.member.model.service.MemberService;
//import com.sh.hairball.member.model.vo.Member;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/admin/memberList")
//public class AdminMemberListServlet extends HttpServlet{
//    private static final long serialVersionUID = 1L;
//    private final MemberService memberService = new MemberService();
//	private final int LIMIT = 10; // 한페이지당 게시물수
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 1. 사용자입력값 처리
//		int cpage = 1; // 기본값처리
//		try {
//			cpage = Integer.parseInt(request.getParameter("cpage")); 			
//		} catch (NumberFormatException e) {
//			// 예외처리외에 아무것도 하지 않음.
//		}
//		int start = (cpage - 1) * LIMIT + 1;
//		int end = cpage * LIMIT;
//		
//		// 2. 업무로직
//		List<Member> memberList = memberService.findPage(start, end);
//        List<Member> members = memberService.findAll();
//        
//        // 페이지바영역 처리
// 		int totalContent = memberService.getTotalContent();
// 		String url = request.getRequestURI(); 
// 		String pagebar = AnimalUtil.getPagebar(cpage, LIMIT, totalContent, url);
//
//     	request.setAttribute("memberList", memberList);
//     	request.setAttribute("pagebar", pagebar);
//        request.setAttribute("members", members);
//
//        request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
//                .forward(request,response);
//    }
//}
