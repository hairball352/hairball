<%@page import="java.util.Arrays"%>
<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%

  String memberId = loginMember.getMemberId();
  String name = loginMember.getName();
  String email = loginMember.getEmail();
  String phone = loginMember.getPhone();
  String address = loginMember.getAddress();
  MemberRole memberRole = loginMember.getMemberRole();

  // null값 노출 방지용
  email = email != null ? email : "";


%>
<section id=enroll-container1>
	<div>
	<img id="detail-img" src="/hairball/images/login.gif" alt="login">
	</div>
  <h2>프로필 수정</h2>
  <form
          name="memberUpdateFrm"
          action="<%= request.getContextPath() %>/member/memberUpdate"
          method="post">
    <table>
      <tr>
        <th contenteditable="false">아이디<sup>*</sup></th>
        <td>
          <input type="text" name="memberId" id="memberId" value="<%= memberId %>" readonly>
        </td>
      </tr>
      <tr>
        <th contenteditable="false" >이름<sup>*</sup></th>
        <td>
          <input type="text" name="name" id="name" value="<%= name %>"  readonly><br>
        </td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>
          <input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= email %>"><br>
        </td>
      </tr>
      <tr>
        <th>휴대폰<sup>*</sup></th>
        <td>
          <input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%= phone %>" required><br>
        </td>
      </tr>
      <tr>
        <th>주소<sup>*</sup></th>
        <td>
          <input type="address" placeholder="" name="address" id="address" maxlength="11" value="<%= address %>" required><br>
        </td>
      </tr>
    </table>
    
    <input class="detail-btn" type="submit" value="적용"/>
    <input class="detail-btn" type="button" onclick="returnToMain ();" value="취소"/>
    <input class="detail-btn" type="button" onclick="deleteMember();" value="탈퇴"/>
  </form>
</section>
<form name="memberDelFrm" action="<%= request.getContextPath() %>/member/memberDelete" method="post"></form>
<script>
  //폼 유효성검사
  document.memberUpdateFrm.onsubmit = (e) => {
    const frm = e.target;
    const name = e.target.name;
    const phone = e.target.phone;

    // 이름 검사 - 한글2글자 이상
    if (!/^[가-힣]{2,}$/.test(name.value)) {
      alert("이름은 한글2글자 이상이어야 합니다.");
      return false;
    }
    // 전화번호 검사 - 01012345678 010으로 시작하고 숫자8자리 여부 확인
    if (!/^010\d{8}$/.test(phone.value)) {
      alert("전화번호는 010으로 시작하고 숫자8자리여야 합니다.");
      return false;
    }

  };
  
  const returnToMain  = () => {
	  
  };
	  
  
  const deleteMember = () => {
    if(confirm("정말 탈퇴하시겠습니까?"))
      document.memberDelFrm.submit();
  }
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
