package com.sh.hairball.qnaboard.model;

import java.sql.Date;
import java.util.List;

public class AnswerVo {
    int id;
    String adminName;
    String content;
    Date regDate;
    int questionId;

    public AnswerVo() {}

	public AnswerVo(int id, String adminName, String content, Date regDate, int questionId) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.content = content;
		this.regDate = regDate;
		this.questionId = questionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "AnswerVo [id=" + id + ", adminName=" + adminName + ", content=" + content + ", regDate=" + regDate
				+ ", questionId=" + questionId + "]";
	}

    
    
}
