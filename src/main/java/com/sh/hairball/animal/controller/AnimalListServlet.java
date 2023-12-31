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
import com.sh.hairball.common.util.AnimalUtil;

@WebServlet("/animal/list")
public class AnimalListServlet extends HttpServlet {
	
	private final AnimalService animalService = new AnimalService();
	private final int LIMIT = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	int pageNo = 1;
    	if(req.getParameter("pageNo") != null) {
    		pageNo = Integer.parseInt(req.getParameter("pageNo"));
    	}
    	int start = (pageNo -1) * LIMIT +1;
    	int end = pageNo * LIMIT;
    	List<EnrollBoard> enrollBoardsList = animalService.findList(start , end);
    	int totalContent = animalService.getTotalContent(); // 전체 게시글 수
    	
    	String url = req.getRequestURI();
    	String pagebar = AnimalUtil.getPagebar(pageNo, LIMIT, totalContent, url);
    	
    	req.setAttribute("EnrollBaordList", enrollBoardsList);
		req.setAttribute("pagebar", pagebar);
    	
        req.getRequestDispatcher("/WEB-INF/views/board/animalList.jsp")
                .forward(req, resp);
    }
    
    
}
