package com.sh.hairball.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.MemberRole;

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String password = AnimalUtil.getEncryptedPassword(request.getParameter("password"), memberId);

		String saveId = request.getParameter("saveId");
		System.out.println("memberId = " + memberId);
		System.out.println("saveId = " + saveId);

		Member member = memberService.findById(memberId);
		

		HttpSession session = request.getSession(); // request.getSession(true)와 동일.

		if(member != null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); // 쿠키를 사용할 url
			if (saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효기간 7일
			} else {
				// 기존의 쿠키 삭제
				cookie.setMaxAge(0); // 클라이언트 있던 쿠기의 만료기간을 0으로 변경함과 동시에 삭제
			}
			response.addCookie(cookie); // 응답 헤더 Set-Cookie : saveId=honggd

		} else {
			// 로그인 실패
            session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}

		System.out.println("memberServlet@member = " + member);
		// member.setMemberRole(MemberRole.A); 권한을 전부 관리자로 변경해서 
		// 3. 응답처리
		response.sendRedirect(request.getContextPath() + "/"); // redirect를 통한 url변경

	}
}
