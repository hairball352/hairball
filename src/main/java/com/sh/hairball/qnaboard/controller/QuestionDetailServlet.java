package com.sh.hairball.qnaboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.common.util.AnimalUtil;
import com.sh.hairball.qnaboard.model.AnswerVo;
import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.service.QuestionService;

@WebServlet("/question/questionDetail")
public class QuestionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final QuestionService questionService = new QuestionService();
	
	/**
	 * Secure Coding
	 * - XSS 공격 방어처리 (Cross Site Scripting)
	 * - 악성 script태그를 실행해 사용자정보를 탈취하거나 사이트의 위해를 가하는 공격
	 * - <> 부분이 html로 처리되어 생긴 문제이므로 escaping처리를 꼭 해주어야한다.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리 ?no=12
		int id = Integer.parseInt(request.getParameter("id"));
		// 2. 업무로직
		QuestionVo question = questionService.findById(id); // 
		List<AnswerVo> answers = questionService.findAnswerByQuestionId(id);
		System.out.println("question = " + question);
		System.out.println("answers = " + answers);
		
		// secure coding처리
		String unsecureTitle = question.getTitle();
		String secureTitle = AnimalUtil.escapeHtml(unsecureTitle);
		question.setTitle(secureTitle);
		
		// 3. 응답처리 jsp
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		request.getRequestDispatcher("/WEB-INF/views/qnaBoard/questionDetail.jsp")
			.forward(request, response);
	}

}






