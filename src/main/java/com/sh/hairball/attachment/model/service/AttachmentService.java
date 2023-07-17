package com.sh.hairball.attachment.model.service;


import static com.sh.hairball.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import com.sh.hairball.attachment.model.dao.AttachmentDao;
import com.sh.hairball.attachment.model.vo.Attachment;

public class AttachmentService {
	private final AttachmentDao attachmentDao = new AttachmentDao(); 

	public List<Attachment> findAll() {
		Connection conn = getConnection();
		List<Attachment> result = attachmentDao.findAll(conn);
		close(conn);
		return result;
	}

}
