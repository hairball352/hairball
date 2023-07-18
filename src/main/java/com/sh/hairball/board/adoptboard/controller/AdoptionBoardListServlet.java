package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.board.adoptboard.model.service.AdoptionService;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity;
import com.sh.hairball.common.util.AnimalUtil;


@WebServlet("/animal/animalAdoptionList")
public class AdoptionBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdoptionService adoptionService = new AdoptionService();
    private final int LIMIT = 10;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch (NumberFormatException e) {
			
		}
		
		int start = (cpage - 1) * LIMIT + 1 ;
		int end = cpage * LIMIT;
		 
		List<AdopBoard> adopBoards = adoptionService.findAll(start, end);
		int totalContent = adoptionService.getTotalContent(); // 전체 게시글 수
		
		String url = request.getRequestURI();
		String pagebar = AnimalUtil.getPagebar(cpage, LIMIT, totalContent, url);
		
		request.setAttribute("adoptionBoardList", adopBoards);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/animal/animalAdoptionBoardList.jsp")
			.forward(request, response);
	}



}