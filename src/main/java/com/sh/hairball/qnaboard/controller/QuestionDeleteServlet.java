package com.sh.hairball.qnaboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.qnaboard.service.QuestionService;



/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/question/questionDelete")
public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final QuestionService questionService = new QuestionService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력값 처리
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 2.업무로직

		int result = questionService.deleteQuestion(id);

		
		// 3. 응답처리
		request.getSession().setAttribute("msg", "질문을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/qnaBoard/questionList");
	}

}
