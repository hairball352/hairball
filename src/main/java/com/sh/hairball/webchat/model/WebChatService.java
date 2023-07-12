
package com.sh.hairball.webchat.model;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;


public class WebChatService {

    private final WebChatDao webChatDao = new WebChatDao();

    public int insertWebChat(WebChat webchatVo) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = webChatDao.insertWebChat(conn, webchatVo);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }
}

