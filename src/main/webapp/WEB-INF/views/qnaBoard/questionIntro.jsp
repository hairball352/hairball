<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>
<%@ include file="/WEB-INF/views/templates/aside.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/question.css" />
<section class="question-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a>참여소통</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/qnaBoard/questionIntro">자주하는질문</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/qnaBoard/questionList">QnA</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">FAQ</div>
		<hr class="section-hr" />
		<div class="faq-container">
			<div class="answer-list">
				<input type="radio" name="accordion" id="answer1" /> <label
					for="answer1"> 자주하는질문1번입니까? <i class="fas fa-angle-down"></i>
				</label>
				<div>
					<p>자주하는질문1에 대한 답변입니다.</p>
				</div>

				<input type="radio" name="accordion" id="answer2" /> <label
					for="answer2"> 자주하는질문2번입니까? <i class="fas fa-angle-down"></i>
				</label>
				<div>
					<p>자주하는질문2에 대한 답변입니다.</p>
				</div>

				<input type="radio" name="accordion" id="answer3" /> <label
					for="answer3"> 자주하는질문3번입니까? <i class="fas fa-angle-down"></i>
				</label>
				<div>
					<p>자주하는질문3에 대한 답변입니다.</p>
				</div>

				<input type="radio" name="accordion" id="answer4" /> <label
					for="answer4"> 자주하는질문4번입니까? <i class="fas fa-angle-down"></i>
				</label>
				<div>
					<p>자주하는질문4에 대한 답변입니다.</p>
				</div>

				<input type="radio" name="accordion" id="answer5" /> <label
					for="answer5"> 자주하는질문5번입니까? <i class="fas fa-angle-down"></i>
				</label>
				<div>
					<p>자주하는질문5에 대한 답변입니다.</p>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>