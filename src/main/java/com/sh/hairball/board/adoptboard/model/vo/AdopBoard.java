package com.sh.hairball.board.adoptboard.model.vo;

import java.sql.Date;

public class AdopBoard { // 입양 게시글 VO
	int id;
	int animalId;
	int memberId;
	int process; // 입양 진행 상태
	Date regDate;
	Date visitDate;
	
	public AdopBoard() {	super();	}

	public AdopBoard(int id, int animalId, int memberId, int process, Date regDate, Date visitDate) {
		super();
		this.id = id;
		this.animalId = animalId;
		this.memberId = memberId;
		this.process = process;
		this.regDate = regDate;
		this.visitDate = visitDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	@Override
	public String toString() {
		return "AdopBoard [id=" + id + ", animalId=" + animalId + ", memberId=" + memberId + ", process=" + process
				+ ", regDate=" + regDate + ", visitDate=" + visitDate + "]";
	}

	
	
}
