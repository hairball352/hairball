<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>

<main>
<div class="introduce01-bar">
                <div class="side-menu-title"><a href="<%= request.getContextPath() %>/introduce/introduce1.jsp">관리자 페이지</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/introduce/introduce1.jsp">동물 등록</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/introduce/introduce2.jsp">회원 목록 조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/introduce/introduce2.jsp">채팅 목록 조회</a></div>
                <hr class="side-hr" />
            </div>
        </div>


<div class="Enroll-Container">
  <div id="checked-title2">타이틀</div>
            <hr class="section-hr" />
	<form name="memberEnrollFrm" action="" method="POST">
	<table>
	    <tr>
	        <th>등록고유번호</th>
	        <td>
	            <input type="text" name="reg_no" id="reg_no">
	        </td>
	    </tr>
	    <tr>
	        <th>동물타입</th>
	        <td>
	            <input type="text" name="type" id="type">
	        </td>
	    </tr>
	    <tr>
	        <th>동물사진</th>
	        <td>
	            <input type="file" name="file" id="file">
	        </td>
	    </tr>
	    <tr>
	        <th>품종</th>
	        <td>
	            <input type="text" name="species" id="species">
	        </td>
	    </tr>
	    <tr>
	        <th>나이(YYYY)</th>
	        <td>
	            <input type="text" name="age" id="age">
	        </td>
	    </tr>
	    <tr>
	        <th>몸무게</th>
	        <td>
	            <input type="text" name="weight" id="weight">
	        </td>
	    </tr>
	    <tr>
	        <th>발견장소</th>
	        <td>
	            <input type="text" name="discovery_place" id="discovery_place">
	        </td>
	    </tr>
	    <tr>
	        <th>성별</th>
	        <td>
	            <input type="radio" name="sex" id="sex0" value="M">
				<label for="gender0">M</label>
				<input type="radio" name="sex" id="sex1" value="F">
				<label for="gender1">F</label>
	        </td>
	    </tr>
	    <tr>
	        <th>중성화</th>
	        <td>
	            <input type="radio" name="IsNeutured" id="IsNeutured0" value="Y">
				<label for="gender0">Y</label>
				<input type="radio" name="IsNeutured" id="IsNeutured1" value="N">
				<label for="gender1">N</label>
	        </td>
	    </tr>
	    <tr>
	        <th>특이사항</th>
	        <td>
	            <textarea name="note" id="note" cols="30" rows="10"></textarea>
	        </td>
	    </tr>
	</table>
			<input type="submit" value="등록" >
	</form>
</div>
</main>
<script>
document.memberEnrollFrm.onsubmit = (e) => {
	
	const frm = e.target;
	const reg_no = e.target.reg_no;
	const species = e.target.species;
	const age = e.target.age;
	const sex = e.target.sex0 ? "M" : "F" ;
	const IsNeutured = e.target.IsNeutured0 ? "Y" : "N";
	const note = e.target.note.value;
	
	}
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>