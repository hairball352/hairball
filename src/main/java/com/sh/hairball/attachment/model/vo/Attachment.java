package com.sh.hairball.attachment.model.vo;

import java.sql.Date;


public class Attachment {
	private int id;
	private int enrollBoardid;
	private int animalId;
	private String original_filename;
	private String renamed_filename;
	private Date reg_date;
	
	
	public Attachment(int id, int enrollBoardid, int animalId, String original_filename, String renamed_filename,
			Date reg_date) {
		super();
		this.id = id;
		this.enrollBoardid = enrollBoardid;
		this.animalId = animalId;
		this.original_filename = original_filename;
		this.renamed_filename = renamed_filename;
		this.reg_date = reg_date;
	}
	
	public Attachment() {
		super();
	}
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
