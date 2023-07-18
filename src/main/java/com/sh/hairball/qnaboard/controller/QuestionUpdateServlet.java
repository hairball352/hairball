package com.sh.hairball.qnaboard.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.service.QuestionService;


/**
 * Servlet implementation class BoardCreateServlet
 */
@WebServlet("/qnaBoard/questionUpdate")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final QuestionService questionService = new QuestionService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int id = Integer.parseInt(request.getParameter("id"));
		// 2. 업무로직
		QuestionVo question = questionService.findById(id);
		request.setAttribute("question", question);
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/qnaBoard/questionUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자 입력값 처리
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String memberId = request.getParameter("memberId");
		String content = request.getParameter("content");
		
		QuestionVo question = new QuestionVo();
		question.setId(id);
		question.setTitle(title);
		question.setMemberId(memberId);
		question.setContent(content);


		// 2. 업무로직
		int result = questionService.updateQuestion(question);


		// 3. 응답처리 (목록페이지로 redirect) - POST방식 DML처리후 url변경을 위해 redirect처리
		response.sendRedirect(request.getContextPath() + "/qnaBoard/questionDetail?id=" + question.getId());

	}

}
