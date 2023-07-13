package com.sh.hairball.attachment;

import java.sql.Date;

import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;



public class Attachment {
	private int id;
	private int enrollBoardid;
	private int animalId;
	private String original_filename;
	private String renamed_filename;
	private Date reg_date;
	
	public int getId() {
		return id;
	}
	public void setId (int id) {
		this.id = id;
	}
	
	
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	public int getEnrollBoardid() {
		return enrollBoardid;
	}
	public void setEnrollBoardid(int enrollBoardid) {
		this.enrollBoardid = enrollBoardid;
	}
	public String getOriginal_filename() {
		return original_filename;
	}
	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}
	public String getRenamed_filename() {
		return renamed_filename;
	}
	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
