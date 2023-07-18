package com.sh.hairball.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

/**
 * Servlet implementation class SearchMember
 */
@WebServlet("/member/findMemberId")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 뷰에서 넘어온 사용자 입력값을 처리한다 (조회하고 싶은 폰 번호가 넘어왔겠지)
		
		String phone = request.getParameter("phone");
		System.out.println(phone);
		// 받아온 입력값을 갖고 DB에 조회한다
		// 서비스 - DAO - DB 이 순서대로 조회하는거 쓰기
		// memberService 로 접근해서 하기 (findByPhone : 디비에 member테이블에 member_id)
		Member member = memberService.findByPhone(phone);
		
		request.setAttribute("selectedMember", member);
		// 디비에서 가져온 member 객체의 값을 읽어오면 될거 아니야
		  request.getRequestDispatcher("/WEB-INF/views/member/search.jsp")
          .forward(request, response);
		// 이 멤버 객체를 뷰로 보내야돼 setAttrubute
		
		// jsp에서 member.getMemberId();
	}

}
