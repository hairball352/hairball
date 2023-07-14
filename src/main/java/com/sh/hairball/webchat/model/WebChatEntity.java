package com.sh.hairball.webchat.model;

import java.sql.Date;

public class WebChatEntity extends WebChat {
	
	private int webChatCnt;

	public WebChatEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebChatEntity(int id, int memberId, String content, Date regDate) {
		super(id, memberId, content, regDate);
		// TODO Auto-generated constructor stub
	}

	public WebChatEntity(int webChatCnt) {
		super();
		this.webChatCnt = webChatCnt;
	}

	public int getWebChatCnt() {
		return webChatCnt;
	}

	public void setWebChatCnt(int webChatCnt) {
		this.webChatCnt = webChatCnt;
	}

	@Override
	public String toString() {
		return "WebChatEntity [webChatCnt=" + webChatCnt + "]";
	}
	
	
	
}
