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
    
    public int insertWebChat(Connection conn, WebChat webchat) {
        int result = 0;
        String sql = prop.getProperty("insertWebChat");
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, webchat.getMemberId());
            pstmt.setString(2, webchat.getContent());
            pstmt.setDate(3, (Date) webchat.getRegDate());
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

	
	private WebChat handleWebChatResultSet(ResultSet rset) throws SQLException {
	    WebChat webchat = new WebChat();
	    webchat.setId(rset.getInt("chat_id"));
	    webchat.setMemberId(rset.getInt("member_id"));
	    webchat.setContent(rset.getString("content"));
	    webchat.setRegDate(rset.getDate("reg_date"));
	    
	    return webchat;
	}
	
	private WebChat handleWebChatListResultSet(ResultSet rset) throws SQLException {
	    WebChat webchat = new WebChat();
	    webchat.setId(rset.getInt("id"));
	    webchat.setMemberId(rset.getInt("member_id"));
	    webchat.setContent(rset.getString("content"));
	    webchat.setRegDate(rset.getDate("reg_date"));
	    
	    return webchat;
	}


	public List<WebChat> findChatByMemberId(Connection conn, int memberId) {
		List<WebChat> chatHistory = new ArrayList<>();
		String sql = prop.getProperty("getLastMemberId");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					WebChat webChat = new WebChat();
					webChat.setId(rset.getInt("id"));
					webChat.setMemberId(rset.getInt("member_id"));
					webChat.setContent(rset.getString("content"));
					
					chatHistory.add(webChat);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatHistory;
	}
}
    
