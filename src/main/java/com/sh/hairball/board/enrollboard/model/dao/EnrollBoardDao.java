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
		board.setAttachmentId(resultSet.getInt("attachment_id"));
		board.setAnimalId(resultSet.getInt("animal_id"));
		return board;
	}

}
