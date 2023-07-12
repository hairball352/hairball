package com.sh.hairball.admin.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.MemberRole;

import java.io.IOException;

@WebServlet("/admim/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    protected  void doPos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String memberId = request.getParameter("memberId");
        String _memberRole = request.getParameter("memberRole");
        MemberRole memberRole = MemberRole.valueOf(_memberRole);

        int result = memberService.updateMemberRole(memberId, memberRole);


        response.sendRedirect(request.getContextPath() + "/admin/memberList");
    }
}
