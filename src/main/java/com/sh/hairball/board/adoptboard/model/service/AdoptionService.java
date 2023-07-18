package com.sh.hairball.board.adoptboard.model.service;

import static com.sh.hairball.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalEntity;
import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity;
import com.sh.hairball.board.enrollboard.model.dao.EnrollBoardDao;

public class AdoptionService {
	private final AdoptionDao adoptionDao = new AdoptionDao();
	private final AnimalDao animalDao = new AnimalDao();
	private final EnrollBoardDao enrollBoardDao = new EnrollBoardDao();
	
	/**
	 * 입양 게시글 추가 (INSERT)
	 */
	public int insertBoard(AdopBoard adopBoard) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adoptionDao.insertBoard(conn, adopBoard);
			int boardId = adoptionDao.getLastBoardNo(conn); 
			adopBoard.setId(boardId); 
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * 
	 * 입양 게시글 삭제 (DELETE)
	 */
	public int deleteBoard(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adoptionDao.deleteBoard(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}  finally {
			close(conn);
		}
		return result;
	}

	public List<AdopBoardEntity> findAll(int start, int end) {
		Connection conn = getConnection();
		List<AdopBoardEntity> boardList = adoptionDao.findAll(conn, start, end);
		close(conn);
		return boardList;
	}

	/**
	 * 모든 게시글 수 조회하기
	 */
	public int getTotalContent() {
		Connection conn = getConnection();
		int total = adoptionDao.getTotalContent(conn);
		close(conn);
		return total;
	}

	public AdopBoard findById(int no) {
		Connection conn = getConnection();
		AdopBoard adopBoard = adoptionDao.findById(conn, no);
		
		System.out.println(adopBoard);
		
		Animal animal = animalDao.findById(conn, adopBoard.getAnimalId()); // 입양 동물 정보 추가
		//Attachment attach = enrollBoardDao.findById(conn, animal.getAttachmentId());
		
		adopBoard.setAnimal(animal);
		//attach.setAnimalId(no);
		System.out.println("Adopservice animal : " + animal);
		close(conn);
		return adopBoard;
	}
}
