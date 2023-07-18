package com.sh.hairball.animal.model.vo;

public class AnimalEntity {
	int id;
	int age; // 년생
	int attachmentId; // 첨부파일 아이디 (이미지 사진)
	String discoveryPlace; // 발견장소
	AnimalType animalType; // 개/고양이
	String species; // 품종
	double weight; // 몸무게
	String pblId; // 등록 고유 번호
	int state; // 보호중인지 입양완료인지 상태
	Sex sex;
	int neutered; // 중성화유무 (0-안됨 / 1-됨)
	String note;

	
	// 기본 생성자
	public AnimalEntity() {	super();	}
	
	
	// 매개변수 생성자
	public AnimalEntity(int id, int age, int attachmentId, String discoveryPlace, AnimalType animalType, String species,
			double weight, String pblId, int state, Sex sex, int neutered) {
		super();
		this.id = id;
		this.age = age;
		this.attachmentId = attachmentId;
		this.discoveryPlace = discoveryPlace;
		this.animalType = animalType;
		this.species = species;
		this.weight = weight;
		this.pblId = pblId;
		this.state = state;
		this.sex = sex;
		this.neutered = neutered;
	}
	
	// getter / setter
	public int getId() {
		return id;
	}
	
	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getDiscoveryPlace() {
		return discoveryPlace;
	}
	public void setDiscoveryPlace(String discoveryPlace) {
		this.discoveryPlace = discoveryPlace;
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
	public String getPblId() {
		return pblId;
	}
	public void setPblId(String pblId) {
		this.pblId = pblId;
	}
	
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getNeutered() {
		return neutered;
	}
	public void setNeutered(int neutered) {
		this.neutered = neutered;
	}
	
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", age=" + age + ", attachmentId=" + attachmentId + ", discoveryPlace="
				+ discoveryPlace + ", animalType=" + animalType + ", species=" + species + ", weight=" + weight
				+ ", pblId=" + pblId + ", state=" + state + ", sex=" + sex + ", neutered=" + neutered + "]";
	}
	
	
}
