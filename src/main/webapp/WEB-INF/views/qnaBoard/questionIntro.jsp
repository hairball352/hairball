<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/header2.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<style>
*{
	margin: 0;
	padding: 0;
}

.faq-container{
	width: 80%;
	box-shadow: 0 0 20 px #e5e5e5;
	padding: 0 40 px;
}
input[id*="answer"]{
	display: none;
}
input[id*="answer"] + label {
	font-size: 1.5em;
	display: flex;
	line-height: 4em;
	justify-content: space-between;
	position: relative;
	border-bottom: 1px solid #d5d5d5;
	cursor: pointer;
}
input[id*="answer"] + label i{
	position: absolute;
	top: 50%;
	right: 0;
	transform: translateY(-50%);
	transition: all .3s;
}
input[id*="answer"] + label + div{
	overflow: hidden;
	height: 0;
	display: flex;
	align-items: center;
	transition: all .3s;
}
input[id*="answer"] + label div p{
	position: inline-block;
	padding: 20px 0;
}
input[id*="answer"]:checked + label + div{
	height: 150px;
	border-bottom: 2px solid #d5d5d5;
}
input[id*="answer"]:checked + label i{
	fransform: rotate(180deg);
}
</style>

<div class="faq-container">
	<h2>자주하는 질문</h2>
	<div class="answer-list">
		<input type="radio" name="accordion" id="answer1"/>
		<label for="answer1">
		자주하는질문1번입니까?
			<i class ="fas fa-angle-down"></i>
		</label>
		<div>
			<p>자주하는질문1에 대한 답변입니다.</p>
		</div>
		
		<input type="radio" name="accordion" id="answer2"/>
		<label for="answer2">
		자주하는질문2번입니까?
			<i class ="fas fa-angle-down"></i>
		</label>
		<div>
			<p>자주하는질문2에 대한 답변입니다.</p>
		</div>
		
		<input type="radio" name="accordion" id="answer3"/>
		<label for="answer3">
		자주하는질문3번입니까?
			<i class ="fas fa-angle-down"></i>
		</label>
		<div>
			<p>자주하는질문3에 대한 답변입니다.</p>
		</div>
		
		<input type="radio" name="accordion" id="answer4"/>
		<label for="answer4">
		자주하는질문4번입니까?
			<i class ="fas fa-angle-down"></i>
		</label>
		<div>
			<p>자주하는질문4에 대한 답변입니다.</p>
		</div>
		
		<input type="radio" name="accordion" id="answer5"/>
		<label for="answer5">
		자주하는질문5번입니까?
			<i class ="fas fa-angle-down"></i>
		</label>
		<div>
			<p>자주하는질문5에 대한 답변입니다.</p>
		</div>
	</div>
</div>	
<h2>
	<a href="<%= request.getContextPath() %>/qnaBoard/questionList">QnA</a>
</h2>
 

<%@ include file="/WEB-INF/views/templates/footer.jsp" %>