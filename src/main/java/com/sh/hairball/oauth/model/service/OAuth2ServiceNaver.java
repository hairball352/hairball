package com.sh.hairball.oauth.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.Provider;


public class OAuth2ServiceNaver {
    private final MemberService memberService = new MemberService();
    Member member = null;

    public OAuth2ServiceNaver() {
        super();
    }

    public Member naverLogin(String code, String state) throws ParseException {

        String reqURL = "https://nid.naver.com/oauth2.0/token";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-type", "application/x-www-form-urlencoded");
        String responseBody = get(reqURL, requestHeaders, code);

        int result = 0;

        JSONParser parsing = new JSONParser();
        JSONObject jsonObj = null;
        // 발급받은 토큰을 JSON 객체로 변환해서
        try {
            jsonObj = (JSONObject) parsing.parse(responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String access_token = (String) jsonObj.get("access_token");
        String refresh_token = (String) jsonObj.get("refresh_token");
        System.out.println("jsonObj : "+jsonObj);

        requestHeaders.put("Authorization", "Bearer "+access_token);

        String responseUserInfo = getUserInfo("https://openapi.naver.com/v1/nid/me",requestHeaders);
        System.out.println("responseUserInfo : " + responseUserInfo);

        // responseBody에 담긴 값을 바탕으로 JSON 파싱하기


        Object obj = parsing.parse(responseUserInfo);
        JSONObject _jsonObj = (JSONObject)obj;
        JSONObject resObj = (JSONObject)_jsonObj.get("response");

        String email = (String) resObj.get("email");
        String phone = (String) resObj.get("mobile");

        System.out.println("email : " + email);
        System.out.println("phone : " + phone);

        member = new Member();
        member.setEmail(email);
        member.setPhone(phone);
        member.setProvider(Provider.N);

        result = memberService.insertMember(member);
//                MemberVo loginMember =  memberService.findById(member.getMemberId());

        // 세션 객체에 저장

        return member;
    }

    private String getUserInfo(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection conn = connect(apiUrl);
        try {
            conn.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(conn.getInputStream());
            } else {
                return readBody(conn.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            conn.disconnect();
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders, String code) {
        HttpURLConnection conn = connect(apiUrl);
        String grant_type = "authorization_code";
        String client_id = "9kBGa_4PSPHg5IPpNrhO";
        String client_secret = "9C1xlV1x7y";
        String redirect_uri = "http://localhost:8080/hairball/oauth/naver"; // 콜백페이지
        String postData = "grant_type=" + grant_type + "&client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&client_secret=" + client_secret + "&code=" + code;

        try {
            conn.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            conn.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(postData);
                writer.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(conn.getInputStream());
            } else {
                return readBody(conn.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            conn.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결 실패 : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답 실패", e);
        }
    }

}