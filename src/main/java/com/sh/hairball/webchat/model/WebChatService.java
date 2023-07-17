
package com.sh.hairball.webchat.model;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class WebChatService {

    private final WebChatDao webChatDao = new WebChatDao();

    public int insertWebChat(WebChat webchat) {
        int result = 0;
        Connection conn = getConnection();
        try {
        	result = webChatDao.insertWebChat(conn, webchat);
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
        Connection conn = getConnection();
        List<WebChat> webchats = webChatDao.findChatByMemberId(conn, memberId);
        close(conn);
		return webchats;
	}

}

