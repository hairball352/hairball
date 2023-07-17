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

	public List<Animal> findByPblId(String term) {
		Connection conn = getConnection();
		List<Animal> animals = animalDao.findAll(conn); 
		
		List<Animal> result = new ArrayList<>(); 
		
		for(Animal animal : animals) {
			if(animal.getPblId().contains(term)) { 
				result.add(animal); 
			}
		}
		return result;
	}

}
