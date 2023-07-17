<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>

<section id="login-section">
	<div class="login-container">
		<%
		if (loginMember == null) {
		%>
		
		<!-- 로그인폼 시작 -->
		<form id="loginFrm" name="loginFrm" action="<%=request.getContextPath()%>/member/login" method="post">
			<table id="login-table">
				<tr>
					<img src="/hairball/images/login.gif" alt="login">
					<td><input colspan="2" type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1" value="<%=saveId != null ? saveId : ""%>"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" name="password" id="password" tabindex="2" placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="saveId4">
							<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "checked" : ""%> /> 
							<div class="saveId3">
								<label id="saveId2" for="saveId">아이디저장</label>&nbsp;&nbsp; 
								<br/>
							</div>
						</div>
						<div class="login_btn">
							<input id="btn1" type="submit" tabindex="3" value="로그인">
							<input type="button" id="btn2" value="회원가입" onclick="location.href='<%=request.getContextPath()%>/member/memberEnroll';">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<!-- 로그인폼 끝-->
		<%
		} else {
		%>
		<!-- 로그인사용자정보 시작 -->
		<table id="login">
			<tr>
				<td><%=loginMember.getName()%>님, 안녕하세요. <span
					id="notification"></span></td>
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
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
