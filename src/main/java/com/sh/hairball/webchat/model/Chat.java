package com.sh.hairball.webchat.model;

import java.util.ArrayList;
import java.util.List;

import com.sh.hairball.member.model.vo.Member;

public class Chat {
	
	private List<WebChat> contents = new ArrayList<>();

	public Chat() {

	}

	public Chat(List<WebChat> contents) {
		this.contents = contents;
	}

	public List<WebChat> getContents() {
		return contents;
	}

	public void setContents(List<WebChat> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Chat [contents=" + contents + "]";
	}
	

	
}
