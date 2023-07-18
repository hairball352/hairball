package com.sh.hairball.animal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.animal.model.service.AnimalService;

import oracle.security.o3logon.a;

/**
 * Servlet implementation class AnimalUpdateServlet
 */
@WebServlet("/animal/update")
public class AnimalUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnimalService animalService = new AnimalService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int animalId = Integer.parseInt(request.getParameter("animalId"));
		int state = Integer.parseInt(request.getParameter("state"));
		
		animalService.updateState(animalId , state);
	}

}
