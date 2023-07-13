package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.board.adoptboard.model.service.AdoptionService;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity;


@WebServlet("/animal/animalAdoptionUpdate")
public class AnimalAdoptionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdoptionService adoptionService = new AdoptionService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 게시글 불러오기 
		int no = Integer.parseInt(request.getParameter("no"));
		
		AdopBoardEntity adopBoard = adoptionService.findById(no);
		request.setAttribute("adopBoard", adopBoard);
		
		request.getRequestDispatcher("/WEB-INF/views/animal/animalAdoptionBoardList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
