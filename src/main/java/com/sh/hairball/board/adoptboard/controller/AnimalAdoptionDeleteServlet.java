package com.sh.hairball.board.adoptboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.board.adoptboard.model.service.AdoptionService;

/**
 * 게시글 삭제
 */
@WebServlet("/animal/animalAdoptionDelete")
public class AnimalAdoptionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdoptionService adoptionService = new AdoptionService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no")); // 게시글 번호
		
		System.out.println("adoption boardNo = " + no);
		
		int result = adoptionService.deleteBoard(no);
		session.setAttribute("msg", "신청이 취소되었습니다.");
		response.sendRedirect(request.getContextPath() + "/animal/animalAdoptionList");
		
		
	}

}
