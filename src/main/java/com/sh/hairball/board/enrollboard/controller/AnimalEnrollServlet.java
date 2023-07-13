//package com.sh.hairball.board.enrollboard.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.FileRenamePolicy;
//import com.sh.hairball.animal.model.service.AnimalService;
//import com.sh.hairball.animal.model.vo.Animal;
//import com.sh.hairball.animal.model.vo.AnimalType;
//import com.sh.hairball.animal.model.vo.Sex;
//import com.sh.hairball.board.enrollboard.model.service.EnrollBoardService;
//import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;
//
//
//@WebServlet("/animal/enroll")
//public class AnimalEnrollServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private final EnrollBoardService enrollBoardService = new EnrollBoardService();
//	private final AnimalService animalService = new AnimalService();
//	
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		req.getRequestDispatcher("/WEB-INF/views/animal/animalEnrollFrm.jsp").forward(req, resp);
//	}
//
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		ServletContext application = getServletContext();
//		String saveDirectory = application.getRealPath("/upload/board");
//		System.out.println("saveDirectory = " + saveDirectory);
//		int maxPostSize = 1024 * 1024 * 10; 
//		String encoding = "utf-8";
//		
//		FileRenamePolicy policy = new MyPolicy();
//		
//		MultipartRequest multiReq = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);
//		
//		
//		String reg_no = multiReq.getParameter("reg_no");
//		String type = multiReq.getParameter("type");
//		String age = multiReq.getParameter("age");
//		String discovery_place = multiReq.getParameter("discovery_place");
//		String sex = multiReq.getParameter("sex");
//		String species = multiReq.getParameter("species");
//		String IsNeutured = multiReq.getParameter("IsNeutured");
//		String note = multiReq.getParameter("note");
//		String weight = multiReq.getParameter("weight");
//		
//		Animal animal = new Animal();
//		animal.setAge(Integer.parseInt(age));
//		animal.setAnimalType(type.equals("고양이") ? AnimalType.C : AnimalType.D);
//		animal.setDiscoveryPlace(discovery_place);
//		animal.setNeutered(IsNeutured.equals("Y") ? 1 : 0);
//		animal.setPblId(reg_no);
//		animal.setState(note);
//		animal.setSex(sex.equals("M") ? Sex.M : Sex.F);
//		animal.setSpecies(species);
//		animal.setWeight(Float.parseFloat(weight));
//		System.out.println(animal.toString());
//		
//		
//	}
//}
