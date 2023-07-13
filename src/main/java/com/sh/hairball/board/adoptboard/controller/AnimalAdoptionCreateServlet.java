package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.board.adoptboard.model.service.AdoptionService;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity;

/**
 * Servlet implementation class AnimalAdoptionCreateServlet
 */
@WebServlet("/animal/animalAdoptionBoardCreate")
public class AnimalAdoptionCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdoptionService adoptionService = new AdoptionService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/animal/animalAdoptionBoardCreate.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int animalId = Integer.parseInt(request.getParameter("animalId"));
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String _visitDate = request.getParameter("visitDate");
		Date visitDate = Date.valueOf(_visitDate);
		
		AdopBoard adopBoard = new AdopBoard();
		
		adopBoard.setAnimalId(animalId);
		adopBoard.setMemberId(memberId);
		adopBoard.setVisitDate(visitDate);
		
		int result = adoptionService.insertBoard(adopBoard);
	
		response.sendRedirect(request.getContextPath() + "/animal/animalAdoptionBoardDetail?no=" + adopBoard.getId());
	}

}
