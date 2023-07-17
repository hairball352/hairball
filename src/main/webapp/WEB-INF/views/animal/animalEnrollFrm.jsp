<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>

<div class="animal-section" >
<div class="introduce01-bar">
                <div class="side-menu-title"><a href="<%= request.getContextPath() %>/animal/enroll">관리자페이지</a></div>
                <hr class="side-hr" />
               	<div class="side-menu"><a href="<%= request.getContextPath() %>/animal/enroll">동물등록</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/memberList">회원목록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/webChatList">채팅기록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/AdminChat">현재채팅상담</a></div>
                <hr class="side-hr" />
            </div>


<div class="introduce01-detail-section animalList" style="width:950px">
		<div class="checked-title2">상세동물보기</div>
		<hr class="section-hr" />
		
	<form name="animallEnrollFrm" action="<%=request.getContextPath() %>/animal/enroll" method="POST"
			enctype="multipart/form-data">
	<table id="enroll" style="width:950px;">
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
	<input type="submit" value="등록" style="margin-left:550px; width: 200px; height:35px;" >
	</form>
</div>
</div>
<script>
document.animallEnrollFrm.onsubmit = (e) => {
	
	const frm = e.target;
	const reg_no = e.target.reg_no;//
	const type = e.target.type;//
	const species = e.target.species;
	const age = e.target.age;
	const weight = e.target.weight;
	const discovery_place = e.target.discovery_place
	const sex = e.target.sex0.checked ? "M" : e.target.sex1.checked ? "F" : null ;
	const IsNeutured = e.target.IsNeutured0.checked ? "Y" : e.target.IsNeutured1.checked ? "N" : null ;
	const note = e.target.note.value;
	
	if(file==null){
		alert("사진첨부는 필수입니다.")
		return false;
	}
	
	if (reg_no.value.length < 1) {
	      alert("등록번호를 입력해주세요.")
	      return false;
	    }
	if (!/^\d{4}$/.test(age.value)) {
		  // 4자리 연도가 아닌 경우
		  alert("4자리 연도를 입력해주세요.");
		  return false;
		}
	if (!(type.value == "고양이" || type.value == "개")) {
		console.log(type)
	      alert("개 또는 고양이만 등록가능합니다.")
	      return false;
	    }
	if(!/^\d+(\.\d+)?$/.test(weight.value)){
		alert("몸무게 입력이 정확하지 않습니다.\n 소숫점 표현식 00.000 으로입력해주세요.")
	}
	if(species == null){
		alert("품종을 입력해주세요.")
		return false;
	}
	if(sex === null) {
		alert("성별을 선택해주세요.")
		return false;
		}
	if(IsNeutured === null) {
		alert("중성화 여부를 선택해주세요.")
		return false;
	}
	
}
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>