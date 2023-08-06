<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/aside.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/question.css" />
<section class="animal-section">
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
				<a href="<%=request.getContextPath()%>/qnaBoard/questionList">Q&A</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">FAQ</div>	
		<hr class="section-hr" />

		<div id="Accordion_wrap">
			<button type="button" class="collapsible" onclick="collapse(this);"><span>Q.</span> 입양 후 파양이 가능한가요?</button>
			<div class="content">
				<p>입양 후 파양은 절대 불가합니다. 신중하게 생각하시고 신청해주시기 바랍니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);"><span>Q.</span>  임시보호를 하고 싶은데 임시보호도 가능한가요?</button>
			<div class="content">
				<p>불가능합니다. 저희 센터는 끝까지 책임질 수 있는 보호자를 찾고 있습니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);"><span>Q.</span>  유기동물을 구조했는데 어떻게 해야 하나요?</button>
			<div class="content">
				<p>유기견을 구조하였을 경우에는 센터로 문의하여 주시거나, 한국동물구조관리협회(031-867-9119)로 전화주시기 바랍니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);"><span>Q.</span>  센터에 있는 아이들을 만져 볼 수도 있나요?</button>
			<div class="content">
				<p>안전상의 문제와 전염성 질병 예방을 위해 밖에서만 보실 수 있습니다.</p>
			</div>
			<button type="button" class="collapsible" onclick="collapse(this);"><span>Q.</span>  동물등록과 중성화는 꼭 해야하나요?</button>
			<div class="content">
				<p>등물등록은 반려동물을 잃어버린 경우 신속하게 주인을 찾아주고, <br/>동물소유자의 책임 의식을 높여 동물 유기를 방지하기 위하여 시행된 제도입니다. <br/>미등록 적발시 1차 20만원, 2차 40만원, 3차 60만원의 과태료가 부과됩니다.
  				중성화는 예기치 못하게 잃어버렸을 경우, <br/>교배로 인해 추가적으로 유기견이 발생하는 것을 방지하기 위하여 필수적으로 시행해야 합니다.</p>
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
		console.dir(content)
		if (content.style.maxHeight != 0) { // 버튼 다음 요소가 펼쳐져 있으면
			content.style.maxHeight = null; // 접기
		} else {
			content.style.maxHeight = content.scrollHeight + "px"; // 접혀있는 경우 펼치기
		}
	}
</script>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>