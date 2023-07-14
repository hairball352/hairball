package com.sh.hairball.oauth.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        int result = ouath2Service.kakaoLogin(req.getParameter("code"));
    }

}
