package com.sh.hairball.board.enrollboard.model.vo;

import java.sql.Date;

import com.sh.hairball.animal.model.vo.AnimalType;
import com.sh.hairball.animal.model.vo.Sex;

public class EnrollBoardDto {
	private int animalId;
	private String originalFileName;
	private String renamedFileName;
	private Date regDate;
	private int age;
	private String discovoeryPlace;
	private AnimalType animalType;
	private String species;
	private double weight;
	private String pbl_id;
	private String state;
	private Sex sex;
	private int Neutered;
	
	
	public EnrollBoardDto() {
		super();
	}


	public EnrollBoardDto(int animalId, String originalFileName, String renamedFileName, Date regDate, int age,
			String discovoeryPlace, AnimalType animalType, String species, double weight, String pbl_id, String state,
			Sex sex, int neutered) {
		super();
		this.animalId = animalId;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.regDate = regDate;
		this.age = age;
		this.discovoeryPlace = discovoeryPlace;
		this.animalType = animalType;
		this.species = species;
		this.weight = weight;
		this.pbl_id = pbl_id;
		this.state = state;
		this.sex = sex;
		Neutered = neutered;
	}


	public int getAnimalId() {
		return animalId;
	}


	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}


	public String getOriginalFileName() {
		return originalFileName;
	}


	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}


	public String getRenamedFileName() {
		return renamedFileName;
	}


	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getDiscovoeryPlace() {
		return discovoeryPlace;
	}


	public void setDiscovoeryPlace(String discovoeryPlace) {
		this.discovoeryPlace = discovoeryPlace;
	}


	public AnimalType getAnimalType() {
		return animalType;
	}


	public void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public String getPbl_id() {
		return pbl_id;
	}


	public void setPbl_id(String pbl_id) {
		this.pbl_id = pbl_id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Sex getSex() {
		return sex;
	}


	public void setSex(Sex sex) {
		this.sex = sex;
	}


	public int getNeutered() {
		return Neutered;
	}


	public void setNeutered(int neutered) {
		Neutered = neutered;
	}


	@Override
	public String toString() {
		return "EnrollBoardDto [animalId=" + animalId + ", originalFileName=" + originalFileName + ", renamedFileName="
				+ renamedFileName + ", regDate=" + regDate + ", age=" + age + ", discovoeryPlace=" + discovoeryPlace
				+ ", animalType=" + animalType + ", species=" + species + ", weight=" + weight + ", pbl_id=" + pbl_id
				+ ", state=" + state + ", sex=" + sex + ", Neutered=" + Neutered + "]";
	}
	
	
}
