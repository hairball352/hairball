package com.sh.hairball.oauth.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.oauth.model.service.OAuth2Service;

/**
 * Servlet implementation class KakaoLoginServlet
 */
@WebServlet("/oauth/kakao")
public class KakaoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final OAuth2Service ouath2Service = new OAuth2Service();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = ouath2Service.kakaoLogin(req.getParameter("code"));
        String saveId = req.getParameter("saveId"); 
        		
        HttpSession session = req.getSession(); // request.getSession(true)와 동일.

		if (member != null) {
			session.setAttribute("loginMember", member);

			Cookie cookie = new Cookie("saveId", member.getMemberId());
			cookie.setPath(req.getContextPath()); // 쿠키를 사용할 url
			if (saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효기간 7일
			} else {
				// 기존의 쿠키 삭제
				cookie.setMaxAge(0); // 클라이언트 있던 쿠기의 만료기간을 0으로 변경함과 동시에 삭제
			}
			resp.addCookie(cookie); // 응답 헤더 Set-Cookie : saveId=honggd
		} else {
			// 로그인 실패
//                session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}

		System.out.println("memberServlet@member = " + member);

		// 3. 응답처리
		resp.sendRedirect(req.getContextPath() + "/"); // redirect를 통한 url변경

    }

}
