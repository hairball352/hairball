package com.sh.hairball.animal.model.vo;

public class Animal extends AnimalEntity{
	String originalFileName;

	// 기본생성자
	public Animal() {};
	
	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	@Override
	public String toString() {
		return super.toString() + "Animal [originalFileName=" + originalFileName + "]";
	}
	
}
