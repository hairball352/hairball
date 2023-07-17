package com.sh.hairball.animal.model.vo;

public class Animal extends AnimalEntity{
	String renamedFileName;

	// 기본생성자
	public Animal() {}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	@Override
	public String toString() {
		return "Animal [renamedFileName=" + renamedFileName + "]";
	};
	
	
}
