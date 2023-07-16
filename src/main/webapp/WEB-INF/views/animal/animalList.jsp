<%@page import="com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%
List<EnrollBoardDto> animalBoardList= (ArrayList) request.getAttribute("EnrollBaordList");
%>
<main>
<div class="animal-list-container" >
    <div class="side-menu" style="margin-top: 100px;">
        <h1 class="side-menu-title" style="text-align: center; border-bottom: solid 1px gray; margin-left: 100px; width: 150px; font-size: 30px;">보호동물</h1>
        <br>
        <ul class="side-menu-list">
            <li style="text-align: center; border-bottom: solid 1px gray ; margin-left: 100px; width: 150px; font-size: 30px;">입양절차</li>
            <li style="text-align: center; border-bottom: solid 1px gray ; margin-left: 100px; width: 150px; font-size: 30px;">보호동물</li>
        </ul>
    </div>
    <%for(int i = 0 ; i<animalBoardList.size(); i++){
    	if(i%3==0){
    		%>
    		<ul>
    		<%
    	}%>
    	
    	<li>
    	<a href="<%=request.getContextPath() %>/animal/animalDetail"><img src="<%= request.getContextPath() %>/upload/animal/<%= animalBoardList.get(i).getRenamedFileName() %>" style="width:100px"/></a>
    	<p>고유번호 : <%= animalBoardList.get(i).getPbl_id() %></p>
    	<p>성별 : <%= animalBoardList.get(i).getSex() %></p>
    	<p>나이 : <%= animalBoardList.get(i).getAge() %></p>
    	</li>
    	
    	<%if(i%3==2){%>
    		</ul>
    		
    		<%}%>
    <%} %>
</div>
</main>
<script>
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>