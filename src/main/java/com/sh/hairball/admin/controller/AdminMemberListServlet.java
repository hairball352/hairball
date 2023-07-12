package com.sh.hairball.admin.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/memberList")
public class AdminMemberListServlet {
    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberService.findAll();
        System.out.println("members = " + members);
        request.setAttribute("members", members);

        request.getRequestDispatcher("WEB-INF/views/admin/memberList.jsp")
                .forward(request,response);
    }
}
