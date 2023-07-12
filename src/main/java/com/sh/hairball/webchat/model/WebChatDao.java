package com.sh.hairball.webchat.model;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;



public class WebChatDao {
    private Properties prop = new Properties();
    
    public WebChatDao() {
        String filename = WebChatDao.class.getResource("/sql/webchat/web-chat.properties").getPath();
        try {
            prop.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int insertWebChat(Connection conn, WebChat webchatVo) {
        int result = 0;
        String sql = prop.getProperty("insertWebChat");
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, webchatVo.getMemberId());
            pstmt.setString(2, webchatVo.getContent());
            pstmt.setDate(3, (Date) webchatVo.getRegDate());
            result = pstmt.executeUpdate();
        } catch(SQLException e) {
            throw new WebChatException(e);
        }
        return result;
    }
    

}
