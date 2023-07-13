package com.sh.hairball.qnaboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.qnaboard.model.AnswerVo;
import com.sh.hairball.qnaboard.service.QuestionService;


@WebServlet("/question/answerDelete")
public class AnswerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final QuestionService questionService = new QuestionService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력값 처리
		int questionId =  Integer.parseInt(request.getParameter("questionId"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 2.업무로직
		AnswerVo answer = new AnswerVo();
		answer.setQuestionId(questionId);
		answer.setId(id);
		
		int result = questionService.deleteAnswer(id);
		
		// 3. 응답처리
		request.getSession().setAttribute("msg", "댓글을 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/qnaBoard/questionDetail?id=" + questionId);
	}

}
