
package com.sh.hairball.webchat.model;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class WebChatService {

    private final WebChatDao webChatDao = new WebChatDao();

    public int insertWebChat(Chat chat) {
        int result = 0;
        Connection conn = getConnection();
        try {
        	
        	// Chat 테이블의 컨텐츠 추가
            result = webChatDao.insertWebChat(conn, chat);
            
            // memeber.no를 조회
            int memberId = webChatDao.getLastMemberId(conn);
            chat.setId(memberId);
            System.out.println("memberId = " + memberId);
            
            // webChat 테이블 추가
            List<WebChat> webchats = chat.getContents();
            if(webchats != null && !webchats.isEmpty()) {
            	for(WebChat webchat : webchats) {
            		chat.setId(memberId);
            		result = webChatDao.insertWebChat(conn, chat);
            	}
            }
      
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }
    
    public List<WebChat> webChatfindAll() {
        Connection conn = getConnection();
        List<WebChat> webchats = webChatDao.webChatfindAll(conn);
        close(conn);
        return webchats;
    }    
    
    // 채팅 기록 조회
    public List<WebChat> getChatHistory(int memberId) {
        Connection conn = null;
        List<WebChat> chatHistory = new ArrayList<>();

        try {
            conn = getConnection();
            chatHistory = webChatDao.getChatHistory(conn, memberId);
        } catch (SQLException e) {
            // 예외 처리
        } finally {
            close(conn);
        }

        return chatHistory;
    }
}

