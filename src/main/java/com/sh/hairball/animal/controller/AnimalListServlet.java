package com.sh.hairball.animal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.hairball.animal.model.service.AnimalService;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto;
import com.sh.hairball.common.util.AnimalUtil;

@WebServlet("/animal/list")
public class AnimalListServlet extends HttpServlet {
	
	private final AnimalService animalService = new AnimalService();
	private final int LIMIT = 9;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	int cpage = 1;
    	if(req.getParameter("cpage") != null) {
    		cpage = Integer.parseInt(req.getParameter("cpage"));
    		System.out.println(cpage);
    	}
    	int start = (cpage -1) * LIMIT +1;
    	int end = cpage * LIMIT;
    	List<EnrollBoardDto> enrollBoardsList = animalService.findList(start , end);
    	int totalContent = animalService.getTotalContent(); // 전체 게시글 수
    	
    	String url = req.getRequestURI();
    	String pagebar = AnimalUtil.getPagebar(cpage, LIMIT, totalContent, url);
    	
    	req.setAttribute("EnrollBaordList", enrollBoardsList);
		req.setAttribute("AnimalListPageBar", pagebar);
    	
        req.getRequestDispatcher("/WEB-INF/views/animal/animalList.jsp")
                .forward(req, resp);
    }
    
    
}
