package com.sh.hairball.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.oauth.model.service.OAuth2ServiceNaver;

@WebServlet("/oauth/naver")
public class NaverLoginServlet extends HttpServlet {
    private final OAuth2ServiceNaver oauth2ServiceNaver = new OAuth2ServiceNaver();
    Member member = null;
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code"); // 인증 과정에 대한 내부 구분값으로 'code'로 전달받음
        										// 네이버 API를 사용할 수 있는지 인증에 대한 코드임
        String state = req.getParameter("state");
        System.out.println("naverLoginServlet code = " + code);
        try {
            member = oauth2ServiceNaver.naverLogin(code, state); // focus
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = req.getSession();

        if (member != null) {
            session.setAttribute("loginMember", member);
        }

        resp.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
