package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.animal.model.service.AnimalService;
import com.sh.hairball.board.adoptboard.model.service.AdoptionService;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;


@WebServlet("/animal/animalAdoptionBoardDetail")
public class AnimalAdoptionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdoptionService adoptionService = new AdoptionService();
    private final AnimalService animalService = new AnimalService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		AdopBoard adopBoard = adoptionService.findById(no);
		
		request.setAttribute("adopBoard", adopBoard);
		
		request.getRequestDispatcher("/WEB-INF/views/animal/animalAdoptionBoardDetail.jsp")
		.forward(request, response);
		
	}


}
