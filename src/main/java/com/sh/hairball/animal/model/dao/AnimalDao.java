package com.sh.hairball.animal.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.sh.hairball.animal.model.exception.AnimalException;
import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.animal.model.vo.AnimalType;
import com.sh.hairball.animal.model.vo.Sex;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;

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
}
