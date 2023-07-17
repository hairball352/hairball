package com.sh.hairball.animal.model.service;

import static com.sh.hairball.common.JdbcTemplate.close;
import static com.sh.hairball.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sh.hairball.animal.model.dao.AnimalDao;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalEntity;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
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

	public Animal findById(int animalId) {
		Connection conn = getConnection();
		Animal animal = animalDao.findById(conn, animalId);
		close(conn);
		return animal;
	}

	public List<Animal> ListByPblId(String term) {
		Connection conn = getConnection();
		List<Animal> animals = animalDao.findAll(conn); //  findAll메소드를 사용해서 전체 조회된 값을 classmates변수에 담음
		List<Animal> result = new ArrayList<>(); // 검색 결과값을 저장할 새로운 ArrayList 객체 생성
		
		// findAll 메소드를 통해서 가져온 classmates 배열 순회
		for(Animal animal : animals) {
			// String#contains : boolean 문자열 포함 여부
			if(animal.getPblId().contains(term)) { // classmate에서 이름을 가져오고, 검색한 문자열(term)을 포함하고 있으면
				result.add(animal); // 위에 선언간 결과값 List에 담기
			}
		}
		return result;
	}
	
	public Animal findByPblId(String animalPblId) {
		Connection conn = getConnection();
		Animal animal = animalDao.findByPblId(conn, animalPblId); //  findAll메소드를 사용해서 전체 조회된 값을 classmates변수에 담음
		close(conn);
		return animal;
	}

}
