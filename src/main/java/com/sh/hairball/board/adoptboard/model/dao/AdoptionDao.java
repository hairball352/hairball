package com.sh.hairball.board.adoptboard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.hairball.board.adoptboard.model.exception.AdopBoardException;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity;

public class AdoptionDao {
	Properties prop = new Properties();
	
	public AdoptionDao() {
		String filename = AdoptionDao.class.getResource("/sql/adoptionBoard/adoptionBoard-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글 작성
	 */
	public int insertBoard(Connection conn, AdopBoardEntity adopBoard) {
		int result = 0;
		// insert into adop_board (id, animal_id, member_id, reg_date, visit_date) values (seq_adopt_board_id.nextval, ?, ?, default, ?)
		String sql = prop.getProperty("insertAdopBoard");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, adopBoard.getAnimalId());
			pstmt.setInt(2, adopBoard.getMemberId());
			pstmt.setDate(3, adopBoard.getVisitDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdopBoardException("입양 게시글 작성 오류", e);
		}
		return result;
	}
	
	/**
	 * 게시글 id(시퀀스) 조회
	 */
	public int getLastBoardNo(Connection conn) {
		int boardNo = 0;
		//select seq_board_no.currval from dual
		String sql = prop.getProperty("getLastBoardNo");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					boardNo = rset.getInt(1); // 첫 번째 컬럼값 가져오기
				}
			}
		} catch(SQLException e) {
			throw new AdopBoardException("입양 게시글 작성 오류, 시퀀스 가져오기 실패", e);
		}
		return boardNo;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		System.out.println(sql);
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw new AdopBoardException("게시글 삭제 실패", e);
		}
		return result;
	}
	public int deleteBoardByAnimalId (Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteBoardByAnimalId");
		System.out.println(sql);
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw new AdopBoardException("게시글 삭제 실패", e);
		}
		return result;
	}

	public List<AdopBoard> findAll(Connection conn, int start, int end) {
		List<AdopBoard> adopBoards = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					AdopBoard adopBoard = handleAdopBoardResultSet(rset);
					adopBoards.add(adopBoard);
				}
			}
			
		} catch (SQLException e) {
			throw new AdopBoardException("전체 게시글 조회 실패", e);
		}
		
		return adopBoards;
	}

	/**
	 * 입양 게시글 조회시 ResultSet 자바 객체로 바꾸는 메소드
	 */
	private AdopBoard handleAdopBoardResultSet(ResultSet rset) throws SQLException {
		AdopBoard adopBoard = new AdopBoard();
		adopBoard.setId(rset.getInt("id"));
		adopBoard.setAnimalId(rset.getInt("animal_id"));
		adopBoard.setMemberId(rset.getInt("member_id"));
		adopBoard.setProcess(rset.getInt("proces"));
		adopBoard.setRegDate(rset.getDate("reg_date"));
		adopBoard.setVisitDate(rset.getDate("visit_date"));
		
		return adopBoard;
	}

	public int getTotalContent(Connection conn) {
		int totalCnt = 0;
		String sql = prop.getProperty("getTotalContent");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			 try (ResultSet rset = pstmt.executeQuery()) {
				 while(rset.next()) {
					 totalCnt = rset.getInt("count(*)");
				 }
			 } 
		} catch (SQLException e) {
			throw new AdopBoardException("전체 게시글 조회 실패", e);
		}
		return totalCnt;
	}

	public AdopBoard findById(Connection conn, int no) {
		AdopBoard adopBoard = null;
		String sql = prop.getProperty("findById");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next())
					adopBoard = handleAdopBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new AdopBoardException("게시글 조회 실패", e);
		}
		
		return adopBoard;
	}

	public boolean findByAnimalId(Connection conn, int animalId) {
		AdopBoard adopBoard = null;
		String sql = prop.getProperty("findByAnimalId");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, animalId);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next())
					adopBoard = handleAdopBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new AdopBoardException("게시글 조회 실패", e);
		}
		
		return adopBoard != null;
	}

}
