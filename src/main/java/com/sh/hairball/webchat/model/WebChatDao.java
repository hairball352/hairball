package com.sh.hairball.webchat.model;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





public class WebChatDao {
    private Properties prop = new Properties();
    
    public WebChatDao() {
        String filename = WebChatDao.class.getResource("/sql/webChat/webChat-query.properties").getPath();
        try {
            prop.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int insertWebChat(Connection conn, Chat chat) {
        int result = 0;
        String sql = prop.getProperty("insertWebChat");
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, chat.getMemberId());
            pstmt.setString(2, chat.getContent());
            pstmt.setDate(3, (Date) chat.getRegDate());
            result = pstmt.executeUpdate();
        } catch(SQLException e) {
            throw new WebChatException(e);
        }
        return result;
    }
    
	public List<WebChat> webChatfindAll(Connection conn) {
        List<WebChat> webchats = new ArrayList<>();
        String sql = prop.getProperty("webChatfindAll");

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rset = pstmt.executeQuery();
        ) {
            while (rset.next()) {
            	WebChat webChat = handleWebChatResultSet(rset);
            	webchats.add(webChat);
            }
        } catch (SQLException e ) {
            throw new WebChatException(e);
        }
        return webchats;
    }
	
	public int getLastMemberId(Connection conn) {
		int memberId = 0;
		String sql = prop.getProperty("getLastMemberId");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					memberId = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new WebChatException(e);
		}
		
		return memberId;
	}

	
	private WebChat handleWebChatResultSet(ResultSet rset) throws SQLException {
        String memberId = rset.getString("member_id");
        String contents = rset.getString("content");
        Date regDate = rset.getDate("reg_date");
		return new WebChat(0, 0, null, regDate);
	}

	public List<WebChat> getChatHistory(Connection conn, int memberId) throws SQLException {
	    List<WebChat> chatHistory = new ArrayList<>();
	    String sql = prop.getProperty("getChatHistory");

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, memberId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                WebChat webChat = new WebChat();
	                webChat.setId(rs.getInt("id"));
	                webChat.setMemberId(rs.getInt("member_id"));
	                webChat.setContent(rs.getString("content"));
	                webChat.setRegDate(rs.getDate("reg_date"));
	                chatHistory.add(webChat);
	            }
	        }
	    }

	    return chatHistory;
	}
}
    
