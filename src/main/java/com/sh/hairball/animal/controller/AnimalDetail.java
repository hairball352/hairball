package com.sh.hairball.animal.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/animal/animalDetail")
public class AnimalDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int animalId = Integer.parseInt(req.getParameter("animalId"));
    	System.out.println("animal ID @ animalDetail Controller"+animalId);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
//        BufferedReader reader = req.getReader();
//        StringBuilder jsonBody = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            jsonBody.append(line);
//        }
//
//        System.out.println("jsonBody" + jsonBody);
//
//
//        // MAP으로 저장함
//        Map<String, String> animal = null;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(jsonBody.toString());
//
//            animal = new HashMap<>();
//
//            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
//
//            while (fieldsIterator.hasNext()) {
//                Map.Entry<String, JsonNode> entry = fieldsIterator.next();
//                String key = entry.getKey();
//                JsonNode valueNode = entry.getValue();
//                String value = (valueNode.isNull()) ? null : valueNode.asText();
//
//                animal.put(key, value);
//            }
//
//            for (Map.Entry<String, String> entry : animal.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        req.setAttribute("animal", jsonBody);
//        req.setAttribute("animalMapo", animal);
        
    	req.getRequestDispatcher("/WEB-INF/views/animal/animalDetail.jsp").forward(req,resp);
    }
}
