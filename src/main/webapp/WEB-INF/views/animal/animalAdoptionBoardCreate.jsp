<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>
<%-- 입양 신청 게시글 작성하는 jsp --%>
<section>
	<form name="animalAdoptionFrm" action="<%= request.getContextPath()%>/animal/animalAdoptionCreate" method="post">
	    <div class="animal-container">
	        <div class="animal-sidebar">
	            <aside id="side-bar">
	                <div class="side-menu"><a href="">입양신청</a></div>
	                <hr class="side-hr" />
	                <div class="side-menu"><a href="">입양신청</a></div>
	                <hr class="side-hr" />
	            </aside>
	        </div>
	        <div class="animal-detail-section">
	            <div id="">입양신청</div>
	            <hr class="section-hr" />
	            <div>
	                <p>게시글 작성해주시면 순차적으로 연락드리겠습니다.</p>
	            </div>
	            <div class="animal-detail-div">
	                <div class="table-container">
	                    <div><img src="/FrontEnd/src/img/1687851928029.png" width="200px"></div>
	                    <table>
	                        <tr>
	                            <th>동물 번호</th>
	                            <td><input type="text" name="animalId"></td>
	                        </tr>
	                        <tr>
	                            <th>아이디</th>
	                            <td><input type="text" name="memberId"></td>
	                        </tr>
	                        <tr>
	                            <th>방문날짜</th>
	                            <td><input type="date" name="visitDate"></td>
	                        </tr>
	                    </table>
                    	<button>돌아가기</button>
                    	<button>작성완료</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</form>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>