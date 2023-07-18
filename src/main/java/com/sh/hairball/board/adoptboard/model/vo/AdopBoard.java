package com.sh.hairball.board.adoptboard.model.vo;


import com.sh.hairball.animal.model.vo.Animal;

public class AdopBoard extends AdopBoardEntity{
	Animal animal; // 입양신청서에 적어낸 동물
	String memberName; // 게시글 리스트에 나타날 이름
	
	public AdopBoard() {
		super();
	}

	// getter / setter
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "AdopBoard [animal=" + animal + ", memberName=" + memberName + "]";
	}

	

}
