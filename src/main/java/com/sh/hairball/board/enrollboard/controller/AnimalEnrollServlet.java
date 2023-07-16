package com.sh.hairball.board.enrollboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalType;
import com.sh.hairball.animal.model.vo.Sex;
import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.enrollboard.model.service.EnrollBoardService;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;
import com.sh.hairball.common.MyPolicy;


@WebServlet("/animal/enroll")
public class AnimalEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EnrollBoardService enrollBoardService = new EnrollBoardService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/animal/animalEnrollFrm.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/animal");
		System.out.println("saveDirectory = " + saveDirectory);
		int maxPostSize = 1024 * 1024 * 10; 
		String encoding = "utf-8";
		
		FileRenamePolicy policy = new MyPolicy();
		
		MultipartRequest multiReq = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);
		
		String reg_no = multiReq.getParameter("reg_no");
		String type = multiReq.getParameter("type");
		String age = multiReq.getParameter("age");
		String discovery_place = multiReq.getParameter("discovery_place");
		String sex = multiReq.getParameter("sex");
		String species = multiReq.getParameter("species");
		String IsNeutured = multiReq.getParameter("IsNeutured");
		String note = multiReq.getParameter("note");
		String weight = multiReq.getParameter("weight");
		
		EnrollBoard enrollBoard = new EnrollBoard();
		
		Enumeration<String> filenames = multiReq.getFileNames();
		
		
		while(filenames.hasMoreElements()) {
			String name = filenames.nextElement();
			File upFile = multiReq.getFile(name);
			if(upFile != null) {
				Attachment attachment = new Attachment();
				attachment.setOriginal_filename(multiReq.getOriginalFileName(name));
				attachment.setRenamed_filename(multiReq.getFilesystemName(name)); // renamedFilename
				enrollBoard.addAttachment(attachment);
			}
		}
		Animal animal = new Animal();
		animal.setAge(Integer.parseInt(age));
		animal.setAnimalType(type.equals("고양이") ? AnimalType.C : AnimalType.D);
		animal.setDiscoveryPlace(discovery_place);
		animal.setNeutered(IsNeutured.equals("Y") ? 1 : 0);
		animal.setPblId(reg_no);
		animal.setState(note);
		animal.setSex(sex.equals("M") ? Sex.M : Sex.F);
		animal.setSpecies(species);
		animal.setWeight(Float.parseFloat(weight));
		
		enrollBoard.setAnimal(animal);
		
		System.out.println(enrollBoard);
		
		int result = enrollBoardService.insertEnrollBoard(enrollBoard);
		
		
		resp.sendRedirect(req.getContextPath() + "/animal/animalDetail?no=" + enrollBoard.getId());
		
	}
}

