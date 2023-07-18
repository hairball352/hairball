package com.sh.hairball.board.enrollboard.model.service;

import static com.sh.hairball.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.attachment.model.dao.AttachmentDao;
import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;
import com.sh.hairball.board.enrollboard.model.dao.EnrollBoardDao;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class EnrollBoardService {
	private final EnrollBoardDao enrollBoardDao = new EnrollBoardDao();
	private final AnimalDao animalDao = new AnimalDao();
	private final AttachmentDao attachmentDao = new AttachmentDao();
	private final AdoptionDao adoptionDao = new AdoptionDao();
	
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
			result = animalDao.insertAnimal(conn, enrollBoard.getAnimal());
			int animalId = animalDao.getLastAnimalId(conn);
			result = enrollBoardDao.insertEnrollBoard(conn, animalId);
			int enrollBoardId = enrollBoardDao.getLastEnrollBoardNo(conn);
			
			enrollBoard.setId(enrollBoardId); 
			
			
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
	public int deleteBoard(int animalId) { 
		Connection conn = getConnection();
		int result = 0;
		try {
			result = enrollBoardDao.deleteBoard(conn, animalId);
			if(adoptionDao.findByAnimalId(conn, animalId))
			result = adoptionDao.deleteBoardByAnimalId(conn, animalId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}
	
}
