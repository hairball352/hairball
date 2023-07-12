package com.sh.hairball.qnaboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.service.QuestionService;

@WebServlet("/qnaBoard/questionList")
public class QuestionListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final QuestionService questionService = new QuestionService();
    private final int LIMIT = 10; // 한페이지당 게시물수

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 사용자입력값 처리
        int cpage = 1;
        try {
            cpage = Integer.parseInt(request.getParameter("cpage"));

        } catch (NumberFormatException e) {
        }

        int start = (cpage - 1) * LIMIT + 1;
        int end = cpage * LIMIT;

        // 2. 업무로직
        List<QuestionVo> questions = questionService.findAll(start, end);

        // xss공격대비처리
        for(QuestionVo question : questions) {
            question.setTitle(AnimalUtil.escapeHtml(question.getTitle()));
        }

        // 페이지바영역 처리
        int totalContent = questionService.getTotalContent();
        String url = request.getRequestURI();
        String pagebar = AnimalUtil.getPagebar(cpage, LIMIT, totalContent, url);

        request.setAttribute("questions", questions);
        request.setAttribute("pagebar", pagebar);

        // 3. 응답처리
        request.getRequestDispatcher("/WEB-INF/views/qnaBoard/questionList.jsp")
                .forward(request, response);


    }


}
