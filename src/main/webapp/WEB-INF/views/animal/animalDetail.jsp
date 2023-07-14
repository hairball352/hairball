<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
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
                            <td colspan="6" id="STATE_NM"></td>
                            </th>
                            <th colspan="4">공고번호
                            <td colspan="6" id="PBLANC_IDNTFY_NO"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">공고마감일
                            <td colspan="6" id="PBLANC_END_DE"></td>
                            </th>
                            <th colspan="4">발견장소
                            <td colspan="6" id="DISCVRY_PLC_INFO"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">견종
                            <td colspan="6" id="SPECIES_NM"></td>
                            </th>
                            <th colspan="4">성별
                            <td colspan="6" id="SEX_NM"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">나이
                            <td colspan="6" id="AGE_INFO"></td>
                            </th>
                            <th colspan="4">몸무게
                            <td colspan="6" id="BDWGH_INFO"></td>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4">특이사항
                            <td colspan="16" id="PARTCLR_MATR"></td>
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
    const animalDatas = sessionStorage.getItem("animalInfo"); // string
    const animalInfo = JSON.parse(animalDatas);
    console.log(animalDatas);
    console.log(animalInfo);

    const {AGE_INFO, BDWGH_INFO, PBLANC_IDNTFY_NO, DISCVRY_PLC_INFO, PBLANC_END_DE,SEX_NM,SFETR_INFO, STATE_NM, IMAGE_COURS, PARTCLR_MATR} = animalInfo;

    const img = document.querySelector("#table-img");
    img.src = IMAGE_COURS;

    document.querySelector("#AGE_INFO").innerHTML = AGE_INFO;
    document.querySelector("#BDWGH_INFO").innerHTML =  BDWGH_INFO;
    document.querySelector("#DISCVRY_PLC_INFO").innerHTML = DISCVRY_PLC_INFO;
    document.querySelector("#PBLANC_IDNTFY_NO").innerHTML = PBLANC_IDNTFY_NO;
    document.querySelector("#PBLANC_END_DE").innerHTML = PBLANC_END_DE;
    document.querySelector("#SEX_NM").innerHTML = SEX_NM;
    document.querySelector("#SFETR_INFO").innerHTML = SFETR_INFO;
    document.querySelector("#STATE_NM").innerHTML = STATE_NM;
    document.querySelector("#PARTCLR_MATR").innerHTML = PARTCLR_MATR;


</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>

