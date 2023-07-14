package com.sh.hairball.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Terms
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

import java.io.IOException;
import java.util.Set;

@WebServlet("/member/terms")
    public class Terms extends HttpServlet {
        private static final long serialVersionUID = 1L;

        private final MemberService memberService = new MemberService();

        /**
         * GET /member/memberEnroll
         * - 회원가입 폼페이지 응답
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	
            request.getRequestDispatcher("/WEB-INF/views/member/terms.jsp")
                    .forward(request, response);
        }

}
