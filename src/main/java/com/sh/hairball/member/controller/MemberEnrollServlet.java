package com.sh.hairball.member.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.member.model.service.MemberService;

import java.io.IOException;

@WebServlet("/member/memberEnroll")
    public class MemberEnrollServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        private final MemberService memberService = new MemberService();

        /**
         * GET /member/memberEnroll
         * - 회원가입 폼페이지 응답
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
                    .forward(request, response);
        }

        /**
         * POST /member/memberEnroll
         * - DB에 회원정보 저장 insert into member values (?, ?, default, ...)
         * - 회원권한, 포인트, 등록일등 sql 기본값처리
         *
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            // 1. 사용자입력값 처리
            String memberId = request.getParameter("memberId");
            String password = AnimalUtil.getEncryptedPassword(request.getParameter("password"), memberId);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

//            MemberVo newMember = new MemberVo(memberId, password, name, email, phone, address);
//            System.out.println(newMember);

            // 2. 업무로직 - db저장 요청
//            int result = memberService.insertMember(newMember);
//            System.out.println("result = " + result);

            // 결과 메세지 속성 등록 : 성공적으로 회원등록 했습니다.
            HttpSession session = request.getSession();
            session.setAttribute("msg", "성공적으로 회원등록 했습니다.");

            // 3. 인덱스페이지 리다이렉트
            response.sendRedirect(request.getContextPath() + "/");


        }
}
