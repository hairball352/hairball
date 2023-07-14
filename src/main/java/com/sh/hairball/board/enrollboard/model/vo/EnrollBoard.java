package com.sh.hairball.board.enrollboard.model.vo;

import java.sql.Date;

import com.sh.hairball.animal.model.vo.Animal;
import com.sh.hairball.attachment.model.vo.Attachment;

public class EnrollBoard { // 등록 게시글 VO
	int id;
	Date regDate;
	Animal animal;
	Attachment attachment;
	
	public EnrollBoard() {
		super();
	}

	public EnrollBoard(int id, Date regDate, Animal animal, Attachment attachment) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.animal = animal;
		this.attachment = attachment;
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

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void addAttachment(Attachment attach) {
		if(attach != null)
			this.attachment=attach;
	}

	
}
