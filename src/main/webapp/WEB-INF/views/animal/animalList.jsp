<%@page import="com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%
List<EnrollBoardDto> animalBoardList= (List<EnrollBoardDto>) request.getAttribute("EnrollBaordList");
%>
<div class="animal-section" >
    <div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="/hairball/animal/animalAdoptionList">입양신청</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="/hairball/animal/animalAdoptionList">입양신청</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
		<div class="introduce01-detail-section animalList" style="width:950px">
		<div class="checked-title2">보호동물목록</div>
		<hr class="section-hr" />
		<div>
		<% if(animalBoardList.size()==0){ %>
		<h1 id="canNotFindAnimal">등록 유기동물을 찾을 수 없습니다 !</h1>
		<%} %>
    <%for(int i = 0 ; i<animalBoardList.size(); i++){
    	if(i%3==0){
    		%>
    		<ul>
    		<%
    	}%>
    	<a href="<%= request.getContextPath() %>/animal/animalDetail?no=<%= animalBoardList.get(i).getAnimalId()%>">
	    	<li>
		    	<img src="<%= request.getContextPath() %>/upload/animal/<%= animalBoardList.get(i).getRenamedFileName() %>" style="width:100px"/>
		    	<p>고유번호 : <%= animalBoardList.get(i).getPbl_id() %></p>
		    	<p>성별 : <%= animalBoardList.get(i).getSex() %></p>
		    	<p>나이 : <%= animalBoardList.get(i).getAge() %></p>
	    	</li>
    	</a>
    	<%if(i%3==2){%>
    		</ul>
    		
    		<%}%>
    <%} %>
    </div>
    <div id='pagebar'>
		<%= request.getAttribute("AnimalListPageBar") %>
	</div>
</div>

</div>

<script>
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>