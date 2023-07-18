package com.sh.hairball.animal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.animal.model.service.AnimalService;
import com.sh.hairball.animal.model.vo.Animal;


@WebServlet("/animal/autocomplete")
public class AnimalAutocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AnimalService animalService = new AnimalService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String term = request.getParameter("term");
		System.out.println("term = " + term);
		
		
		List<Animal> results = animalService.ListByPblId(term); // 공고 번호로 찾기
		System.out.println("resulst" + results);
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(results != null && !results.isEmpty()) {
			for(int i=0; i < results.size(); i++) {
				Animal animal = results.get(i);
				out.append(animal.getPblId());
				
				if(i != results.size() -1) 
					out.append(",");
			}
		}
		
	}



}
