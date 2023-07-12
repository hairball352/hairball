package com.sh.hairball.member.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.MemberRole;

import java.io.IOException;

    @WebServlet("/member/memberUpdate")
    public class MemberUpdateServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final MemberService memberService = new MemberService();

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 1. 사용자입력값 처리
            String memberId = request.getParameter("memberId");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
//            MemberRole role = request.getParameter("role");


            // Member객체로 변환
            // update member set name = ?, gender = ?, birthday = ?, email = ?, phone = ?, hobby = ? where member_id = ?
            Member member = new Member();
            member.setMemberId(memberId);
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
            member.setAddress(address);
//            member.setMemberRole(role);
            System.out.println(member);

            // 3.업무로직
            int result = memberService.updateMember(member);

            // session의 속성 loginMember도 바로 갱신
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", memberService.findById(memberId));

            // 4. 사용자피드백 및 리다이렉트 처리
            session.setAttribute("msg", "성공적으로 회원정보를 수정했습니다.");

            response.sendRedirect(request.getContextPath() + "/member/memberDetail");
        }

    }
