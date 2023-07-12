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

        String code = req.getParameter("code");
        String state = req.getParameter("state");
//        String provider = req.getParameter("service_provider");
        System.out.println("naverLoginServlet code = " + code);
        try {
            member = oauth2ServiceNaver.naverLogin(code, state);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = req.getSession();

        if (member != null) {
            session.setAttribute("loginMember", member);
        }

        System.out.println("loginMember = " + member);

//        resp.sendRedirect(req.getContextPath() + "/views/Oauth/naverCallback.jsp");
        req.getRequestDispatcher("/WEB-INF/views/oauth/naverCallback.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
