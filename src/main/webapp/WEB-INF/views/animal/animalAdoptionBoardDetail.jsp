<%@page import="com.sh.hairball.board.adoptboard.model.vo.AdopBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>

<%
	AdopBoard adopBoard = (AdopBoard)request.getAttribute("adopBoard");
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
                            <th colspan="4">나의 입양 번호
                            <td colspan="6" id=""><%=adopBoard.getId() %>></td>
                            </th>
                            <th colspan="4">ㅇㄴㅁㅇㄴㅁ
                            <td colspan="6" id=""></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">ㅇㅁㄴㅇㅁㅇ
                            <td colspan="6" id="PBLANC_END_DE"></td>
                            </th>
                            <th colspan="4">발견ㅁㄴㅇㄴㅁㅇ장소
                            <td colspan="6" id="DISCVRY_PLC_INFO"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">ㅁㅇㄴㅇㅁ
                            <td colspan="6" id="SPECIES_NM"></td>
                            </th>
                            <th colspan="4">ㅁㄴㅇㅇㄴ
                            <td colspan="6" id="SEX_NM"></td>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>