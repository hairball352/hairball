package com.sh.hairball.attachment;

import java.sql.Date;

import com.sh.hairball.board.enrollboard.model.vo.EnrollBoard;



public class AttachmentVo {
	private int id;
	private EnrollBoard enrollBoard;
	private String original_filename;
	private String renamed_filename;
	private Date reg_date;
	
	public int getId() {
		return id;
	}
	public void setId (int id) {
		this.id = id;
	}
	public EnrollBoard getEnrollBoardVo() {
		return enrollBoard;
	}
	public void setEnrollBoardVo(EnrollBoard enrollBoardVo) {
		this.enrollBoard = enrollBoardVo;
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
