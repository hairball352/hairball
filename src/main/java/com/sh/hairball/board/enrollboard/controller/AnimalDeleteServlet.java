package com.sh.hairball.board.enrollboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.board.enrollboard.model.service.EnrollBoardService;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

/**
 * Servlet implementation class AnimalDeleteServlet
 */
@WebServlet("/animal/delete")
public class AnimalDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EnrollBoardService enrollBoardService = new EnrollBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enrollBoardId = Integer.parseInt(request.getParameter("enrollBoardId"));
		System.out.println(enrollBoardId);
		
		int result = enrollBoardService.deleteBoard(enrollBoardId);
		
		request.getRequestDispatcher("/WEB-INF/views/animal/animalEnrollFrm.jsp").forward(request, response);
		
	}

}
