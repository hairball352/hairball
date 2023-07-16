package com.sh.hairball.qnaboard.model;

import java.sql.Date;
import java.util.List;

public class QuestionVo {
    int id;
    String title;
    String content;
    String memberId;
    Date regDate;
    List<AnswerVo> answerVoList;
    int answerCnt;
    

    public QuestionVo() {}


	public QuestionVo(int id, String title, String content, String memberId, Date regDate, List<AnswerVo> answerVoList,
			int answerCnt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.regDate = regDate;
		this.answerVoList = answerVoList;
		this.answerCnt = answerCnt;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public List<AnswerVo> getAnswerVoList() {
		return answerVoList;
	}


	public void setAnswerVoList(List<AnswerVo> answerVoList) {
		this.answerVoList = answerVoList;
	}


	public int getAnswerCnt() {
		return answerCnt;
	}


	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}


	@Override
	public String toString() {
		return "QuestionVo [id=" + id + ", title=" + title + ", content=" + content + ", memberId=" + memberId
				+ ", regDate=" + regDate + ", answerVoList=" + answerVoList + ", answerCnt=" + answerCnt + "]";
	}

    
}
