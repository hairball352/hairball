<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>

<section id=enroll-container class="enroll-container">
	<div class="enroll-h2">	
   </div>
  <form
          name="checkIdDuplicateFrm"
          action="<%= request.getContextPath() %>/member/checkIdDuplicate">
    <input type="hidden" name="memberId"/>
  </form>
  <form
  	class="enrollFrm" 
  	name="memberEnrollFrm" action="" method="POST">
    <table id="enroll-table">
     	<img src="/hairball/images/procedure/9.gif" alt="login">
      <tr>
        <th>아이디<sup>*</sup></th>
        <td id="login-td">
          <input type="text" placeholder="아이디" name="memberId" id="_memberId" value="" required>
          <input class="enroll-btn4" type="button" value="중복검사" onclick="checkIdDuplicate();"/>
          <input type="hidden" id="idValid" value="0"/>
          <%-- id검사여부 확인용: 0-유효하지않음, 1-유효한 아이디 --%>
        </td>
      </tr>
      <tr>
        <th>패스워드<sup>*</sup></th>
        <td>
          <input type="password" placeholder="비밀번호" name="password" id="_password" value="" required><br>
        </td>
      </tr>
      <tr>
        <th>패스워드확인<sup>*</sup></th>
        <td>
          <input type="password" placeholder="비밀번호 확인" id="passwordConfirmation" value="" required><br>
        </td>
      </tr>
      <tr>
        <th>이름<sup>*</sup></th>
        <td>
          <input type="text" placeholder="이름" name="name" id="name" value="" required><br>
        </td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>
          <input type="email" placeholder="비밀번호 분실 시 확인용 이메일" name="email" id="email" value=""><br>
        </td>
      </tr>
      <tr>
        <th>휴대폰<sup>*</sup></th>
        <td>
          <input type="tel" placeholder="휴대전화번호" name="phone" id="phone" maxlength="11" value="" required><br>
        </td>
      </tr>
      <tr>
        <th>주소<sup>*</sup></th>
        <td>
          <input type="address" placeholder="주소" name="address" id="address" maxlength="11" value="" required><br>
        </td>
      </tr>
    </table>
    <div class="enroll-btn">
    <input class="enroll-btn3" type="reset" value="취소">
    <input class="enroll-btn2" type="submit" value="가입" >
    </div>
  </form>
</section>
<script>

  document.querySelector("#_memberId").onchange = () => {
    document.querySelector("#idValid").value = "0";
  };

  /**
   * 아이디 중복검사 함수
   * - 팝업창으로 폼을 제출
   */
  const checkIdDuplicate = () => {
    const title = "checkIdDuplicatePopup";
    const popup = open("", title, "width=500px, height=300px");

    const frm = document.checkIdDuplicateFrm;
    frm.target = title; // 폼의 제출대상으로 팝업창으로 연결
    frm.memberId.value = document.querySelector("#_memberId").value;
    frm.submit();
  }

  // 비밀번호 일치여부
  document.querySelector("#passwordConfirmation").onblur = (e) => {
    const pw1 = document.querySelector("#_password");
    const pw2 = e.target;

    if(pw1.value !== pw2.value) {
      alert("비밀번호가 일치하지 않습니다.");
      pw1.select();
    }
  };

  // 폼 유효성검사
  
	const displayErrorMessage = (field, message) => {
	    const parent = field.parentNode;
	    const errorElement = parent.querySelector(".error-message");
	
	    if (!errorElement) {
	        const errorDiv = document.createElement("div");
	        errorDiv.classList.add("error-message");
	        errorDiv.textContent = message;
	        parent.appendChild(errorDiv);
	    } else {
	        errorElement.textContent = message;
	    }
	};
	
	const removeErrorMessage = (field) => {
	    const parent = field.parentNode;
	    const errorElement = parent.querySelector(".error-message");
	
	    if (errorElement) {
	        parent.removeChild(errorElement);
	    }
	};
	
	// 아이디 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#_memberId").onblur = () => {
	    const memberId = document.querySelector("#_memberId");
	    const idValid = document.querySelector("#idValid");
	
	    if (!/^\w{4,}$/.test(memberId.value)) {
	        // 입력이 올바르지 않은 경우
	        memberId.classList.add("error");
	        idValid.value = "0";
	        displayErrorMessage(memberId, "아이디는 영문자/숫자 4글자 이상이어야 합니다.");
	    } else {
	        // 입력이 올바른 경우
	        memberId.classList.remove("error");
	        memberId.classList.add("success");
	        idValid.value = "1";
	        removeErrorMessage(memberId);
	    }
	};
	
	// 비밀번호 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#_password").onblur = () => {
	    const password = document.querySelector("#_password");
	
	    if (!/^[\w!@#$%]{4,}$/.test(password.value)) {
	        // 입력이 올바르지 않은 경우
	        password.classList.add("error");
	        displayErrorMessage(password, "비밀번호는 영문자/숫자/특수문자 4글자 이상이어야 합니다.");
	    } else {
	        // 입력이 올바른 경우
	        password.classList.remove("error");
	        password.classList.add("success");
	        removeErrorMessage(password);
	    }
	};
	
	// 비밀번호 확인 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#passwordConfirmation").onblur = (e) => {
	    const pw1 = document.querySelector("#_password");
	    const pw2 = e.target;
	
	    if (pw1.value !== pw2.value) {
	        // 입력이 올바르지 않은 경우
	        pw2.classList.add("error");
	        displayErrorMessage(pw2, "비밀번호가 일치하지 않습니다.");
	    } else {
	        // 입력이 올바른 경우
	        pw2.classList.remove("error");
	        pw2.classList.add("success");
	        removeErrorMessage(pw2);
	    }
	};
	
	// 이름 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#name").onblur = () => {
	    const name = document.querySelector("#name");
	
	    if (!/^[가-힣]{2,}$/.test(name.value)) {
	        // 입력이 올바르지 않은 경우
	        name.classList.add("error");
	        displayErrorMessage(name, "이름은 한글 2글자 이상이어야 합니다.");
	    } else {
	        // 입력이 올바른 경우
	        name.classList.remove("error");
	        name.classList.add("success");
	        removeErrorMessage(name);
	    }
	};
	
	// 휴대폰 번호 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#phone").onblur = () => {
	    const phone = document.querySelector("#phone");
	
	    if (!/^010\d{8}$/.test(phone.value)) {
	        // 입력이 올바르지 않은 경우
	        phone.classList.add("error");
	        displayErrorMessage(phone, "전화번호는 010으로 시작하고 숫자 8자리여야 합니다.");
	    } else {
	        // 입력이 올바른 경우
	        phone.classList.remove("error");
	        phone.classList.add("success");
	        removeErrorMessage(phone);
	    }
	};
	
	// 이메일 입력란 유효성 검사 및 멘트 표시
	document.querySelector("#email").onblur = () => {
	    const email = document.querySelector("#email");
	
	    if (!/^.+@.+\..+$/.test(email.value)) {
	        // 입력이 올바르지 않은 경우
	        email.classList.add("error");
	        displayErrorMessage(email, "유효한 이메일 주소를 입력해주세요.");
	    } else {
	        // 입력이 올바른 경우
	        email.classList.remove("error");
	        email.classList.add("success");
	        removeErrorMessage(email);
	    }
	};
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
