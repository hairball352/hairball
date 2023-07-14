package com.sh.hairball.animal.model.service;

import static com.sh.hairball.common.JdbcTemplate.close;
import static com.sh.hairball.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class AnimalService {
	private final AnimalDao animalDao = new AnimalDao();

	public List<EnrollBoard> findList(int start, int end) {

		
		List<EnrollBoard> result = animalDao.findList(start, end);
		return result;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int total = animalDao.getTotalContent(conn);
		close(conn);
		return total;
	}

}
