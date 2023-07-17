 package com.sh.hairball.board.enrollboard.model.dao;

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

import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.adoptboard.model.exception.AdopBoardException;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class EnrollBoardDao {
	
	private Properties prop = new Properties();
	
	public EnrollBoardDao() {
		String filename = 
				EnrollBoardDao.class.getResource("/sql/enrollBoard/EnrollBoard-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<EnrollBoard> findAll(Connection connection) throws SQLException {
		List<EnrollBoard> result = new ArrayList<>();
		String sql = "select * from enroll_board";
		try(
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					EnrollBoard board = handleEnrollBoardResultSet(resultSet);
					result.add(board);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return result;
	}


	private EnrollBoard handleEnrollBoardResultSet(ResultSet resultSet) throws SQLException {
		EnrollBoard board = new EnrollBoard();
		board.setId(resultSet.getInt("id"));
		board.setRegDate(resultSet.getDate("reg_date"));
		return board;
	}
	
	public int insertEnrollBoard(Connection conn, int animalId) throws SQLException {
		int result = 0;
		String sql = prop.getProperty("insertEnrollBoard");
//		insertEnrollBoard = insert into enroll_board (id, animal_id, reg_date) values (seq_board_no.nextval, ?, default)
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, animalId);
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment attach) throws SQLException {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		// insertAttachment = insert into attachment values(seq_attachment_no.nextval, ?, ?, ? , ? ,default)
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attach.getEnrollBoardid());
			pstmt.setInt(2, attach.getAnimalId());
			pstmt.setString(3, attach.getOriginal_filename());
			pstmt.setString(4, attach.getRenamed_filename());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int getLastEnrollBoardNo(Connection conn) throws SQLException {
		int boardNo = 0;
		String sql = prop.getProperty("getLastEnrollBoardNo");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					boardNo = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardNo;
	}


	public Attachment findById(Connection conn, int attachmentId) {
		Attachment attach = null;
		String sql = prop.getProperty("findById");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attachmentId);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next())
					attach = handleAttachmentResultSet(rset);
			}
		} catch (SQLException e) {
			throw new AdopBoardException("게시글 조회 실패", e);
		}
		
		return attach;
	}


	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		
		int id = rset.getInt("id");
		int enrollBoardid = rset.getInt("enroll_board_id");
		String originFileName = rset.getString("original_filename");
		String renamedFileName = rset.getString("renamed_filename");
		Date regDate = rset.getDate("reg_date");
		
		attach.setEnrollBoardid(enrollBoardid);
		attach.setId(id);
		attach.setOriginal_filename(originFileName);
		attach.setRenamed_filename(renamedFileName);
		attach.setReg_date(regDate);
		
		return attach;
	}


	public int deleteBoard(Connection connection, int enrollBoardId) {
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		try(
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setInt(1, enrollBoardId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
