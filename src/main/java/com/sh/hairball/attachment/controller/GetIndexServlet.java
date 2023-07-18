package com.sh.hairball.attachment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.hairball.attachment.model.service.AttachmentService;
import com.sh.hairball.attachment.model.vo.Attachment;

/**
 * Servlet implementation class GetIndexServlet
 */
@WebServlet("/getindex")
public class GetIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		System.out.println("왔냐?");
		List<Attachment> attachments = attachmentService.findAll();
		System.out.println("photos = " + attachments);
		
		// 3. 응답처리 (json)
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(attachments, response.getWriter());
	}

}
