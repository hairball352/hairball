package com.sh.hairball.qnaboard.model;

import java.sql.Date;
import java.util.List;

public class AnswerVo {
    int id;
    String memberId;
    String content;
    Date regDate;
    int questionId;

    public AnswerVo() {}

    public AnswerVo(int id, String memberId, String content, Date regDate, int questionId) {
        this.id = id;
        this.memberId = memberId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
        return "AnswerVo{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", questionId=" + questionId +
                '}';
    }
}
