package com.sh.hairball.animal.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.hairball.animal.model.exception.AnimalException;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalType;
import com.sh.hairball.animal.model.vo.Sex;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;
import com.sh.hairball.board.adoptboard.model.exception.AdopBoardException;
import com.sh.hairball.board.enrollboard.model.vo.EnrollBoardDto;

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
				if (rset.next()) {
					animal = handleAnimalResultSet(rset);
					animal.setRenamedFileName(rset.getString("renamed_filename"));
				}
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
		animal.setDiscoveryPlace(rset.getString("discvry_plc"));
		animal.setId(rset.getInt("id"));
		animal.setNeutered(rset.getInt("neutered"));
		animal.setPblId(rset.getString("pbl_id"));
		animal.setSex(Sex.valueOf(rset.getString("sex")));
		animal.setSpecies(rset.getString("species"));
		animal.setState(Integer.parseInt(rset.getString("state")));
		animal.setWeight(rset.getDouble("weight"));
		
		return animal;
	}
	
	public int insertAnimal(Connection conn, Animal animal) {
		int result = 0;
		String sql = prop.getProperty("insertAnimal");
		//insertAnimal = insert into animal (id, age , discvry_plc , animal_type , species , weight , pbl_id , state , sex , neutered, note ) values (seq_animal_id.nextval , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)
		try(
			PreparedStatement preparedStatement = conn.prepareStatement(sql)){
			preparedStatement.setInt(1, animal.getAge());
			preparedStatement.setString(2, animal.getDiscoveryPlace());
			preparedStatement.setString(3, animal.getAnimalType().name());
			preparedStatement.setString(4, animal.getSpecies());
			preparedStatement.setFloat(5, (float)animal.getWeight());
			preparedStatement.setString(6, animal.getPblId());
			preparedStatement.setInt(7, animal.getState());
			preparedStatement.setString(8, animal.getSex().name());
			preparedStatement.setInt(9, animal.getNeutered());
			preparedStatement.setString(10, animal.getNote());
			
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

	public List<EnrollBoardDto> findList(Connection conn, int start, int end) {
		List<EnrollBoardDto> result = new ArrayList<>();
		String sql = prop.getProperty("findList");
		System.out.println(sql);
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					EnrollBoardDto enrollBoardEntity = handleEnrollBoardResultSet(rset);
					result.add(enrollBoardEntity);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private EnrollBoardDto handleEnrollBoardResultSet(ResultSet rset) throws SQLException {
		EnrollBoardDto enrollBoardDto = new EnrollBoardDto();
		enrollBoardDto.setAnimalId(rset.getInt("animal_id"));
		enrollBoardDto.setOriginalFileName(rset.getString("original_filename"));
		enrollBoardDto.setRenamedFileName(rset.getString("renamed_filename"));
		enrollBoardDto.setRegDate(rset.getDate("reg_date"));
		enrollBoardDto.setAge(rset.getInt("age"));
		enrollBoardDto.setDiscovoeryPlace(rset.getString("discvry_plc"));
		enrollBoardDto.setAnimalType(rset.getString("animal_type").equals("D")?AnimalType.D : AnimalType.C);
		enrollBoardDto.setSpecies(rset.getString("species"));
		enrollBoardDto.setWeight(rset.getDouble("weight"));
		enrollBoardDto.setPbl_id(rset.getString("pbl_id"));
		enrollBoardDto.setState(rset.getString("State"));
		enrollBoardDto.setSex(rset.getString("sex").equals("M")? Sex.M : Sex.F);
		enrollBoardDto.setNeutered(rset.getInt("neutered"));
		
		
		return enrollBoardDto;
	}

	public List<Animal> findAll(Connection conn) {
		List<Animal> animals = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery(); // select 한 행들이 rset에 담김
		) {
			while(rset.next()) { // rset이 다음에도 있으면 true (rset을 모두 순회할 수 있는 반복문임)
				Animal animal = handleAnimalResultSet(rset); // rset을 자바의 member객체로 변환하여 member에 담음
				animals.add(animal);
			}
		} catch (SQLException e) {
			throw new AnimalException(e);
		}
		return animals;
	}

	public Animal findByPblId(Connection conn, String animalPblId) {
		Animal animal = null;
		String sql = prop.getProperty("findByPblId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, animalPblId);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next()) {
					animal = handleAnimalResultSet(rset);
					animal.setRenamedFileName(rset.getString("renamed_filename"));
				}
			}
		} catch (SQLException e) {
			throw new AnimalException(e);
		}
		return animal;
	}

	public int setAttachmentNumber(Connection conn, int attachmentId, int animalId ) {
		int result = 0;
		String sql = prop.getProperty("setAttachmentNumber");
		//update animal set attachment_id = ? where id = ?
		try(
				PreparedStatement preparedStatement = conn.prepareStatement(sql)){
			preparedStatement.setInt(1, attachmentId);
			preparedStatement.setInt(2, animalId);
			
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
