package com.sh.hairball.webchat.model;


import java.sql.Date;

public class WebChat {
    private int id;
    private int memberId;
    private String content;
    private Date regDate;

    public WebChat() {
        super();
    }

    public WebChat(int id, int memberId, String content, Date regDate) {
        this.id = id;
        this.memberId = memberId;
        this.content = content;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
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

    @Override
    public String toString() {
        return "WebChatVo{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
