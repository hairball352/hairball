package com.sh.hairball.animal.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.sh.hairball.animal.model.exception.AnimalException;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalType;
import com.sh.hairball.animal.model.vo.Sex;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;
import com.sh.hairball.board.adoptboard.model.exception.AdopBoardException;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;

public class AnimalDao {
	Properties prop = new Properties();
	
	public AnimalDao() {
		String filename = AdoptionDao.class.getResource("/sql/animal/animal-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Animal findById(Connection conn, int animalId) {
		Animal animal = null;
		String sql = prop.getProperty("findById");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, animalId);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next())
					animal = handleAnimalResultSet(rset);
			}
		} catch (SQLException e) {
			throw new AnimalException(e);
		}
		return animal;
	}

	/**
	 * ResultSet으로 조회된 동물을 자바 객체로 반환
	 */
	private Animal handleAnimalResultSet(ResultSet rset) throws SQLException {
		Animal animal = new Animal();
		
		animal.setAge(rset.getInt("age"));
		animal.setAnimalType(AnimalType.valueOf(rset.getString("animal_type")));
		animal.setAttachmentId(rset.getInt("attachment_id"));
		animal.setDiscoveryPlace(rset.getString("discvry_plc"));
		animal.setId(rset.getInt("id"));
		animal.setNeutered(rset.getInt("neutered"));
		animal.setPblId(rset.getString("pbl_id"));
		animal.setSex(Sex.valueOf(rset.getString("sex")));
		animal.setSpecies(rset.getString("species"));
		animal.setState(rset.getString("state"));
		animal.setWeight(rset.getDouble("weight"));
		
		return animal;
	}
	
	public int insertAnimal(Connection conn, Animal animal) {
		int result = 0;
		System.out.println(conn);
		String sql = prop.getProperty("insertAnimal");
		System.out.println(sql);
		System.out.println(animal);
		//insertAnimal = insert into animal (id, age , discvry_plc,animal_type,species,pbl_id , state , sex , neutered) values (seq_animal_id.nextval,?,?,?,?,?,?,?,?,?)
		try(
				PreparedStatement preparedStatement = conn.prepareStatement(sql)){
			preparedStatement.setInt(1, animal.getAge());
			preparedStatement.setString(2, animal.getDiscoveryPlace());
			preparedStatement.setString(3, animal.getAnimalType().name());
			preparedStatement.setString(4, animal.getSpecies());
			preparedStatement.setString(5, animal.getPblId());
			preparedStatement.setString(6, animal.getState());
			preparedStatement.setString(7, animal.getSex().name());
			preparedStatement.setInt(8, animal.getNeutered());
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int getLastAnimalId(Connection conn) throws SQLException {
		int boardNo = 0;
		String sql = prop.getProperty("getLastAnimalId");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					boardNo = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardNo;
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

	public List<EnrollBoard> findList(int start, int end) {
		
		return null;
	}
}
