package com.sh.hairball.webchat.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserSocketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String loginMemberWebChat = req.getParameter(getServletName());
        req.getRequestDispatcher("/WEB-INF/views/webChat/user.jsp").forward(req, resp);

    }
}