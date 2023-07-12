package com.sh.hairball.board.enrollboard.model.vo;

import java.sql.Date;

public class EnrollBoard { // 등록 게시글 VO
	int id;
	Date regDate;
	int animalId;
	int attachmentId;
	
	
	public EnrollBoard() {}


	public EnrollBoard(int id, Date regDate, int animalId, int attachmentId) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.animalId = animalId;
		this.attachmentId = attachmentId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public int getAnimalId() {
		return animalId;
	}


	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}


	public int getAttachmentId() {
		return attachmentId;
	}


	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}


	@Override
	public String toString() {
		return "EnrollBoard [id=" + id + ", regDate=" + regDate + ", animalId=" + animalId + ", attachmentId="
				+ attachmentId + "]";
	}

	
}
