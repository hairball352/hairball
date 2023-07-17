package com.sh.hairball.attachment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.cj.protocol.Resultset;
import com.sh.hairball.attachment.model.vo.Attachment;
import com.sh.hairball.board.adoptboard.model.dao.AdoptionDao;

public class AttachmentDao {
	
	Properties prop = new Properties();
	
	public AttachmentDao() {
		String filename = AttachmentDao.class.getResource("/sql/attachment/attach-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Attachment> findAll(Connection conn) {
		List<Attachment> result = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try(
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet resultset = preparedStatement.executeQuery()){
			while(resultset.next()) {
				Attachment attachment = new Attachment();
				attachment.setId(resultset.getInt("id"));
				attachment.setAnimalId(resultset.getInt("animal_id"));
				attachment.setRenamed_filename(resultset.getString("renamed_filename"));
				
				result.add(attachment);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
