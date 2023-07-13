package com.sh.hairball.admin.model.vo;

import com.sh.hairball.webchat.model.WebChat;

public class AdminWebChatBoard extends AdminWebChatBoardEntity {
	
	WebChat webchat; // 채팅

	public AdminWebChatBoard() {

	}

	public AdminWebChatBoard(WebChat webchat) {
		this.webchat = webchat;
	}

	public WebChat getWebchat() {
		return webchat;
	}

	public void setWebchat(WebChat webchat) {
		this.webchat = webchat;
	}

	@Override
	public String toString() {
		return "AdminWebChatBoard [webchat=" + webchat + "]";
	}

}
