package com.sh.hairball.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
/*
 * util파일입니다 -김대원
 */
public class AnimalUtil {

    public static String getEncryptedPassword(String rawPassword, String salt) {
        String encryptedPassword = null;
        // 1. 암호화
        byte[] output = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] input = rawPassword.getBytes("utf-8");
            byte[] saltBytes = salt.getBytes("utf-8");
            md.update(saltBytes); // salt 추가
            output = md.digest(input); // 평문 비밀번호 전달 digest()가 암호문만듦
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        System.out.println(new String(output));

        // 2. 인코딩
        Encoder encoder = Base64.getEncoder();
        encryptedPassword = encoder.encodeToString(output);
        System.out.println(encryptedPassword);


        return encryptedPassword;
    }

    public static String escapeHtml(String unsecureText) {
        return unsecureText.replace("<", "&lt;").replace(">", "&gt;");
    }

    public static String getPagebar(int cpage, int limit, int totalContent, String url) {
        StringBuilder pagebar = new StringBuilder(); // 문자열 더하기 연산에 최적화
        int totalPage = (int)Math.ceil((double)totalContent / limit);
        url += "?cpage=";
        int pagebarSize = 5;
        int pageStart = ((cpage -1) / pagebarSize) * pagebarSize + 1;
        int pageEnd = pageStart + pagebarSize -1;
        int pageNo = pageStart;

        // 1. 이전
        if (pageNo == 1) {
            // 이전 버튼 비활성화
        } else {
            pagebar.append("<a href='%s%d'>이전</a>".formatted(url, pageNo-1));
            pagebar.append("\n");
        }
        // 2. 숫자
        while(pageNo <= pageEnd && pageNo <= totalPage) {
            if(pageNo == cpage) {
                // 현재페이지인 경우
                pagebar.append("<span class='cpage'>%s</span>".formatted(pageNo));
                pagebar.append("\n");
            } else {
                pagebar.append("<a href='%s%d'>%d</a>".formatted(url, pageNo, pageNo));
                pagebar.append("\n");
            }
            pageNo++;
        }
        // 3. 다음
        if(pageNo > totalPage) {
            // 마지막페이지가 이미 노출된 경우
        } else {
            pagebar.append("<a href='%s%d'>다음</a>".formatted(url, pageNo));
        }

        return pagebar.toString();
    }
}
