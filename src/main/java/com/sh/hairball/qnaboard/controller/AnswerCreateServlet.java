package com.sh.hairball.qnaboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.qnaboard.model.AnswerVo;
import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.notification.service.NotificationService;
import com.sh.hairball.qnaboard.service.QuestionService;

@WebServlet("/qnaBoard/answerCreate")
public class AnswerCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final QuestionService questionService = new QuestionService();
	private final NotificationService notificationService = new NotificationService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int questionId =  Integer.parseInt(request.getParameter("questionId"));
		String memberId = request.getParameter("memberId");
		String content = request.getParameter("content");
		
		AnswerVo answer = new AnswerVo(0, memberId, content, null, questionId);
		System.out.println("answer = " + answer);
		
		// 2. 업무로직
		// 댓글 등록
		int result = questionService.insertAnswer(answer);
		
		// 댓글 등록 실시간 알림
		QuestionVo question = questionService.findById(questionId);
//		result = notificationService.notifyNewAnswer(question);
		
		// 3. 응답처리 - redirect
		response.sendRedirect(request.getContextPath() + "/qnaBoard/questionDetail?id=" + questionId);
		
	}

}
