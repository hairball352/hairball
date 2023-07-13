package com.sh.hairball.board.adoptboard.model.vo;

import java.sql.Date;

import com.sh.hairball.animal.model.vo.Animal;

public class AdopBoard extends AdopBoardEntity{
	Animal animal; // 입양신청서에 적어낸 동물

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

	@Override
	public String toString() {
		return super.toString() + " [animal=" + animal + "]";
	}
	

}
