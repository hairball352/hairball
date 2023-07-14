<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
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

		<div id="Accordion_wrap">
			<h2>자주묻는 질문</h2>
			<button type="button" class="collapsible" onclick="collapse(this);">자주묻는 질문1</button>
			<div class="content">
				<p>내용 1 입니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);">자주묻는 질문2</button>
			<div class="content">
				<p>내용 2 입니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);">자주묻는 질문3</button>
			<div class="content">
				<p>내용 3 입니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);">자주묻는 질문4</button>
			<div class="content">
				<p>내용 3 입니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);">자주묻는 질문5</button>
			<div class="content">
				<p>내용 3 입니다.</p>
			</div>
		</div>
	</div>
</section>

<script>
	function collapse(element) {
		var before = document.getElementsByClassName("active")[0] // 기존에 활성화된 버튼
		if (before && document.getElementsByClassName("active")[0] != element) { // 자신 이외에 이미 활성화된 버튼이 있으면
			before.nextElementSibling.style.maxHeight = null; // 기존에 펼쳐진 내용 접고
			before.classList.remove("active"); // 버튼 비활성화
		}
		element.classList.toggle("active"); // 활성화 여부 toggle

		var content = element.nextElementSibling;
		if (content.style.maxHeight != 0) { // 버튼 다음 요소가 펼쳐져 있으면
			content.style.maxHeight = null; // 접기
		} else {
			content.style.maxHeight = content.scrollHeight + "px"; // 접혀있는 경우 펼치기
		}
	}
</script>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>