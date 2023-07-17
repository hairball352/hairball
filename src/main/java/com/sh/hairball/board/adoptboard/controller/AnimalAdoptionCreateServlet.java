package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.animal.model.service.AnimalService;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.board.adoptboard.model.service.AdoptionService;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;

/**
 * Servlet implementation class AnimalAdoptionCreateServlet
 */
@WebServlet("/animal/animalAdoptionBoardCreate")
public class AnimalAdoptionCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdoptionService adoptionService = new AdoptionService();
    private final AnimalService animalService = new AnimalService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("animalId") != null) {
			int animalId = Integer.parseInt(request.getParameter("animalId"));
			Animal animal = animalService.findById(animalId);
			request.setAttribute("animal", animal);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/animal/animalAdoptionBoardCreate.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String animalPblId = request.getParameter("animalPblId");
		
		List<Animal> animal = animalService.findByPblId(animalPblId);
		
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String _visitDate = request.getParameter("visitDate");
		Date visitDate = Date.valueOf(_visitDate);
		
		AdopBoard adopBoard = new AdopBoard();
		
		adopBoard.setAnimalId(animal.get(0).getId());
		adopBoard.setMemberId(memberId);
		adopBoard.setVisitDate(visitDate);
		
		int result = adoptionService.insertBoard(adopBoard);
	
		response.sendRedirect(request.getContextPath() + "/animal/animalAdoptionBoardDetail?no=" + adopBoard.getId());
	}

}
