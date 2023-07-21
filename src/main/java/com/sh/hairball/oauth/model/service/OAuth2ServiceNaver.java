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

import com.sh.hairball.common.util.AnimalUtil;
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

        String reqURL = "https://nid.naver.com/oauth2.0/token"; // 접근 토큰 발급을 요청할 주소
        Map<String, String> requestHeaders = new HashMap<>();
        
        // 보내는 자원을 명시함 (데이터를 일련의 이름과 값의 쌍으로 인코딩)
        requestHeaders.put("Content-type", "application/x-www-form-urlencoded"); 
        
        
        // API 이용 인증 성공시에 네이버에서 받은 인증 토큰을 String 형태로 저장
        String responseBody = get(reqURL, requestHeaders, code); // @@@@@@@@@@focus@@@@@@@@@@@@@
        System.out.println("Naver responseBody : " + responseBody);
        
        int result = 0;

        JSONParser parsing = new JSONParser();
        JSONObject jsonObj = null;
        // 발급받은 토큰을 JSON 객체로 변환(json-simple 라이브러리 사용)
        try {
            jsonObj = (JSONObject) parsing.parse(responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
        // 접근 토큰 발급 요청 API를 통해 받은 접근 토큰(access token) 값은 회원 프로필 조회를 비롯하여 여러가지 로그인 오픈 API를 호출하는데 사용
        String access_token = (String) jsonObj.get("access_token");

        // Bearer : 클라이언트가 서버에게 자원에 접근할 권한이 있다는 것을 인증하기 위해 사용되는 타입 중 하나
        // 접속 토큰을 요청 헤더에 넣음
        requestHeaders.put("Authorization", "Bearer "+access_token);

        // 사용자 정보를 받아 저장
        String responseUserInfo = getUserInfo("https://openapi.naver.com/v1/nid/me",requestHeaders); // @@@@@@@@@@focus@@@@@@@@@@@@@
        System.out.println("responseUserInfo : " + responseUserInfo);

        
        // 받은 정보 String을 json으로 변경
        Object obj = parsing.parse(responseUserInfo);            // @@@@@@@@@@focus@@@@@@@@@@@@@
        JSONObject _jsonObj = (JSONObject)obj;
        JSONObject resObj = (JSONObject)_jsonObj.get("response");

        String email = (String) resObj.get("email");
        String phone = (String) resObj.get("mobile");
        String name = (String) resObj.get("name");

        member = memberService.findByEmail(email);
        
        // db에 저장된 회원이 아닐 경우
        if(member == null) {
           member = new Member();
           member.setMemberId("Naver@"+name);
           member.setName(name);
           member.setEmail(email);
           member.setPhone(phone.replace("-", ""));
           member.setProvider(Provider.N);
           member.setPassword(AnimalUtil.getEncryptedPassword(name, "Naver"));
           result = memberService.insertMember(member);
        }
        return member;
    }

    
    private String getUserInfo(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection conn = connect(apiUrl);
        System.out.println("requestHeaders : " + requestHeaders);
        try {
            conn.setRequestMethod("POST");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 연결에 성공하면
                return readBody(conn.getInputStream()); // 네이버한테 사용자 정보를 받음
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
        HttpURLConnection conn = connect(apiUrl); // 네이버 서버 접속을 위한 URL 연결
        
        // 네이버 로그인 인증 요청 재료
        // 네이버에 애플리케이션 등록할 때 발급 받은 아이디 및 비밀번호
        String grant_type = "authorization_code";
        String client_id = "9kBGa_4PSPHg5IPpNrhO";
        String client_secret = "9C1xlV1x7y";
        String redirect_uri = "http://localhost:8080/hairball/oauth/naver"; // 요청 성공 시 콜백페이지
        String postData = "grant_type=" + grant_type + "&client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&client_secret=" + client_secret + "&code=" + code;

        
        try {
            conn.setRequestMethod("GET");
            
            // 요청 헤더 설정 (값 세팅)
            // 권한 인증을 위한 (Authorization)과 전송 방식(content-type)이 들어있음
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            
            conn.setDoOutput(true); // 출력 스트림 사용
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "Utf-8")) {
                writer.write(postData);
                writer.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 인증을 성공적으로 통과했다면
                return readBody(conn.getInputStream()); // 받은 값을 읽어오기
            } else {
                return readBody(conn.getErrorStream()); // 에러 읽어오기
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            conn.disconnect();
        }
    }

    
    /**
     * 전달받은 apiUrl을 사용하여 URL 객체를 생성하고, URL 연결 후 HttpURLConnection 객체 반환
     */
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
    

    /**
     * 응답 성공 시에 받은 정보값을 String으로 반환
     */
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