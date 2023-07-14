package com.sh.hairball.animal.model.service;

import static com.sh.hairball.common.JdbcTemplate.close;
import static com.sh.hairball.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto;

public class AnimalService {
	private final AnimalDao animalDao = new AnimalDao();

	public List<EnrollBoardDto> findList(int start, int end) {
		Connection conn = getConnection();
		List<EnrollBoardDto> result = animalDao.findList(conn,start, end);
		return result;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int total = animalDao.getTotalContent(conn);
		close(conn);
		return total;
	}

}
