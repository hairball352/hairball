package com.sh.hairball.board.enrollboard.model.service;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sh.hairball.board.enrollboard.model.dao.EnrollBoardDao;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class EnrollBoardService {
	public final EnrollBoardDao enrollBoardDao = new EnrollBoardDao();

	public List<EnrollBoard> findAll() {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		List<EnrollBoard> result = null;
		try {
			result = enrollBoardDao.findAll(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(connection);
		return result;
	}
	
}
