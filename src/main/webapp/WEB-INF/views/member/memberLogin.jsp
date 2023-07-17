<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%

%>
<section id="login-section">
   <div class="login-container">
      <%
      if (loginMember == null) {
      %>
      <div class="login-div">      
         <form id="loginFrm" name="loginFrm"
            action="<%=request.getContextPath()%>/member/login" method="post">
            <table id="login-table">
               <tr>
                  <img src="/hairball/images/login.gif" alt="login">
                  <td><input colspan="2" type="text" name="memberId"
                     id="memberId" placeholder="아이디" tabindex="1"
                     value="<%=saveId != null ? saveId : ""%>"></td>
               </tr>
               <tr>
                  <td colspan="2"><input type="password" name="password"
                     id="password" tabindex="2" placeholder="비밀번호"></td>
               </tr>
               <tr>
                  <td colspan="2">
                     <div class="saveId4">
                        <input type="checkbox" name="saveId" id="saveId"
                           <%=saveId != null ? "checked" : ""%> />
                        <div class="saveId3">
                           <label id="saveId2" for="saveId">아이디저장</label>&nbsp;&nbsp; <br />
                        </div>
                     </div>
                  </td>
               </tr>
            </table>
         </form>
      </div>
      <div class="login-btn">
         <div>
            <button onclick="loginBtn();">로그인</button>
         </div>
         <a
            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a7b86ff96d50db1785b75938758aeb44&redirect_uri=http://localhost:8080/hairball/oauth/kakao">
            <img src="/hairball/images/kakao_login/kakao_login_large_narrow.png" />
         </a> <a
            href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=9kBGa_4PSPHg5IPpNrhO&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fhairball%2Foauth%2Fnaver&state=587038165575376253184817806031526768739">
            <img src="/hairball/images/naver_login/btnG_완성형.png" />
         </a>
         <div>
            <button onclick="enrollBtn();">회원가입</button>
         </div>
      </div>
      <!-- 로그인폼 끝-->
      <%
      } else {
      %>
      <!-- 로그인사용자정보 시작 -->
      <table id="login">
         <tr>
            <td><%=loginMember.getName()%>님, 안녕하세요. <span id="notification"></span></td>
         </tr>
         <tr>
            <td><input type="button" value="내정보보기"
               onclick="location.href = '<%=request.getContextPath()%>/member/memberDetail';">
               <input type="button" value="로그아웃"
               onclick="location.href='<%=request.getContextPath()%>/member/logout';">
            </td>
         </tr>
      </table>
      <!-- 로그인사용자정보 끝 -->
      <%
      }
      %>
   </div>
</section>
<script>
const loginBtn = () => {
   document.getElementById("loginFrm").submit();
};

const enrollBtn = () => {
   location.href='<%=request.getContextPath()%>/member/memberEnroll';
};
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>