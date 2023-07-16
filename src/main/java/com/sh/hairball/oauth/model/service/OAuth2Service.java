package com.sh.hairball.oauth.model.service;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sh.hairball.member.model.service.MemberService;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.Provider;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OAuth2Service {
    private final MemberService memberService = new MemberService();
    public Member kakaoLogin(String code) {
        System.out.println(code);
        String reqURL = "https://kauth.kakao.com/oauth/token";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-type", "application/x-www-form-urlencoded");
        String responseBody = get(reqURL,requestHeaders , code);
        System.out.println("respBody"+responseBody);

        JSONParser parsing = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject)parsing.parse(responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String access_token = (String)jsonObj.get("access_token");
        String refresh_token = (String)jsonObj.get("refresh_token");
        String scope = (String)jsonObj.get("scope");

        System.out.println("accToken"+access_token);
        System.out.println("refresh_token"+refresh_token);
        System.out.println("scope"+scope);

        requestHeaders.put("Authorization", "Bearer "+access_token);

        String responseUserInfo = getUserInfo("https://kapi.kakao.com/v2/user/me", requestHeaders );
        System.out.println("Resp User Info @"+responseUserInfo);


        Object obj = null;
        try {
            obj = parsing.parse(responseUserInfo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject _jsonObj = (JSONObject)obj;
        System.out.println(_jsonObj);
        JSONObject resObj = (JSONObject)_jsonObj.get("kakao_account");
        JSONObject UserInfo = (JSONObject) resObj.get("profile");
        System.out.println(UserInfo);

        System.out.println(UserInfo.get("nickname"));
        System.out.println(resObj.get("email"));

        Member member = memberService.findById("KaKao"+(String)UserInfo.get("nickname"));
        if(member == null) {
	        member.setMemberId("KaKao"+(String)UserInfo.get("nickname"));
	        member.setPassword("KaKao"+(String)UserInfo.get("nickname"));
	        member.setName((String)UserInfo.get("nickname"));
	        member.setEmail((String)resObj.get("email"));
	        member.setPhone("카카오회원");
	        member.setProvider(Provider.K);
	        
	        memberService.insertMember(member);
        }

        return member;
    }

    private static String getUserInfo(String apiUrl, Map<String, String> requestHeaders ){
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders, String code){
        HttpURLConnection con = connect(apiUrl);
        String grant_type = "authorization_code";
        String client_id = "a7b86ff96d50db1785b75938758aeb44";
        String redirect_uri= "http://localhost:8080/hairball/oauth/kakao";
        String client_secret = "vU2DG59HgaZ8s6nmIf7kkfzWnWYCkqmX";
        String postData="grant_type="+grant_type+"&client_id="+client_id+"&redirect_uri="+redirect_uri+"&client_secret="+client_secret+"&code="+code;

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            con.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
                writer.write(postData);
                writer.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
