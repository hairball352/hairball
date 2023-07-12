package com.sh.hairball.board.enrollboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.board.enrollboard.model.service.EnrollBoardService;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;


@WebServlet("/Animal/Enroll")
public class AnimalEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EnrollBoardService enrollBoardService = new EnrollBoardService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<EnrollBoard> list = enrollBoardService.findAll();
		
		req.getRequestDispatcher("/WEB-INF/views/animal/animalList.jsp").forward(req, resp);
	}
	
}
