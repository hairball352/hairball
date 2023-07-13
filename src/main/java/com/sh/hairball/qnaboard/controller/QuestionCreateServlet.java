package com.sh.hairball.qnaboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.service.QuestionService;

@WebServlet("/question/questionCreate")
public class QuestionCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final QuestionService questionService = new QuestionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/qnaBoard/questionCreate.jsp")
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 사용자 입력값 처리
        String title = request.getParameter("title");
        String memberId = request.getParameter("memberId");
        String content = request.getParameter("content");

        // insert into board (no,title,writer,content) values (seq_board_no.nextval, ?, ?, ?)
        QuestionVo question = new QuestionVo();
        question.setTitle(title);
        question.setMemberId(memberId);
        question.setContent(content);



        // 2. 업무로직
        int result= questionService.insertQuestion(question);

        // 3. 응답처리 (목록페이지로 redirect)
		request.getSession().setAttribute("msg", "질문을 등록했습니다..");
        response.sendRedirect(request.getContextPath() + "/qnaBoard/questionDetail?no=" + question.getId());
    }

}
