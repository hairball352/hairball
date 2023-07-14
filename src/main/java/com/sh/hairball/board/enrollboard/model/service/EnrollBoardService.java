package com.sh.hairball.board.enrollboard.model.service;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.animal.model.service.AnimalService;
import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.enrollboard.model.dao.EnrollBoardDao;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class EnrollBoardService {
	private final EnrollBoardDao enrollBoardDao = new EnrollBoardDao();
	private final AnimalDao animalDao = new AnimalDao();
	
	public List<EnrollBoard> findAll() {
		Connection connection = getConnection();
		List<EnrollBoard> result = null;
		try {
			result = enrollBoardDao.findAll(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(connection);
		return result;
	}

	public int insertEnrollBoard(EnrollBoard enrollBoard) {
		int result = 0;
		Connection conn = getConnection();
		try {
			System.out.println(enrollBoard.getAnimal());
			result = animalDao.insertAnimal(conn, enrollBoard.getAnimal());
			int animalId = animalDao.getLastAnimalId(conn);
			System.out.println("animalId : "+animalId);
			result = enrollBoardDao.insertEnrollBoard(conn, animalId);
			int enrollBoardId = enrollBoardDao.getLastEnrollBoardNo(conn);
			System.out.println("enrollboardId : "+enrollBoardId);
			
			
			enrollBoard.setId(enrollBoardId); 
			
			System.out.println("enrollBoardId = " + enrollBoardId);
			
			Attachment attach = enrollBoard.getAttachment();
			attach.setEnrollBoardid(enrollBoardId);
			attach.setAnimalId(animalId);
			result = enrollBoardDao.insertAttachment(conn, attach);					
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}
	
}
