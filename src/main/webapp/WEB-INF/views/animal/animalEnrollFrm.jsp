<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>

<h1>동물 등록 폼</h1>
관리자만 가능


<form name="memberEnrollFrm" action="" method="POST">
<table>
    <tr>
        <th>등록고유번호</th>
        <td>
            <input type="text" name="" id="">
        </td>
    </tr>
    <tr>
        <th>동물타입</th>
        <td>
            <input type="text" name="" id="">
        </td>
    </tr>
    <tr>
        <th>동물사진</th>
        <td>
            <input type="file" name="" id="">
        </td>
    </tr>
    <tr>
        <th>품종</th>
        <td>
            <input type="text" name="" id="">
        </td>
    </tr>
    <tr>
        <th>나이</th>
        <td>
            <input type="text" name="" id="">
        </td>
    </tr>
    <tr>
        <th>몸무게</th>
        <td>
            <input type="number">
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
            <input type="radio" name="gender" id="gender0" value="M">
			<label for="gender0">M</label>
			<input type="radio" name="gender" id="gender1" value="F">
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
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>