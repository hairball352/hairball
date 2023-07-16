<%@page import="com.sh.hairball.attachment.model.vo.Attachment"%>
<%@page import="com.sh.hairball.animal.model.vo.Animal"%>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<% 
	Animal animal = (Animal)request.getAttribute("animal");
	Attachment img = (Attachment) request.getAttribute("attachment");
%>
<section class="animal-section">
    <div class="animal-container">
        <div class="animal-sidebar">
            <aside id="side-bar">
                <div class="side-menu"><a href="">보호동물</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="">입양절차</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="">보호동물</a></div>
            </aside>
        </div>
        <div class="animal-detail-section">
            <div id="checked-title">보호동물</div>
            <hr class="section-hr" />
            <div class="animal-detail-div">
                <div>
                    <img id="table-img" src="../src/img/소개/mong.jpg">
                </div>
                <div class="table-container">
                    <table>
                        <thead></thead>
                        <tbody>
                        <tr>
                            <th colspan="4">입양상태
                            <td colspan="6" id="state"></td>
                            </th>
                            <th colspan="4">동물등록번호
                            <td colspan="6" id="pblId"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">견종
                            <td colspan="6" id="species"></td>
                            </th>
                            <th colspan="4">성별
                            <td colspan="6" id="sex"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">나이(연생)
                            <td colspan="6" id="age"></td>
                            </th>
                            <th colspan="4">몸무게
                            <td colspan="6" id="weight"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">발견장소
                            <td colspan="16" id="discoveryPlace"></td>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<script>


</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>

