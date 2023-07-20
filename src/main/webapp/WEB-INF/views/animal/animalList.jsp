<%@page import="com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%
List<EnrollBoardDto> animalBoardList= (List<EnrollBoardDto>) request.getAttribute("EnrollBaordList");
%>
<section>
<div class="animal-section" >
    	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%= request.getContextPath() %>/animal/procedure">보호동물</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%= request.getContextPath() %>/animal/procedure">입양절차</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/list">보호동물</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
		<div class="introduce01-detail-section animalList" style="width:950px">
		<div class="checked-title2">보호동물목록</div>
		<hr class="section-hr" />
		<div>
		<% if(animalBoardList.size()==0){ %>
		<h1 id="canNotFindAnimal">등록된 동물이 없습니다.</h1>
		<%} %>
    	<%for(int i = 0 ; i<animalBoardList.size(); i++){
    	if(i%3==0){
    		%>
    		<ul>
    		<%
    	}%>
  
    	<a href="<%= request.getContextPath() %>/animal/animalDetail?no=<%= animalBoardList.get(i).getAnimalId()%>">
	    	<li class="animal-card">
		    	<img src="<%= request.getContextPath() %>/upload/animal/<%= animalBoardList.get(i).getRenamedFileName() %>" style="width:150px; height:150px;"/>
		    	<p><span>고유번호</span> : <%= animalBoardList.get(i).getPbl_id() %></p>
		    	<p><span>성별</span> : <%= animalBoardList.get(i).getSex() %></p>
		    	<p><span>나이</span> : <%= animalBoardList.get(i).getAge() %></p>
		    	<span class="state"><%= stateArr[Integer.parseInt(animalBoardList.get(i).getState())] %></span>

		    	
	    	</li>
    	</a>
    	<%if(i%3==2){%>
    		</ul>
    		<%}%>
    <%} %>
    </div>
    <div id='pagebar3'>
		<%= request.getAttribute("AnimalListPageBar") %>
	</div>
</div>
</div>
</section>
<script>
window.addEventListener('load', () => {
	const state = document.querySelector(".state");
	console.log(state);
	if(state && state.innerHTML == '입양 확정') {
		state.style.backgroundColor = '#5fab95';
	}
});
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>