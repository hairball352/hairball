package com.sh.hairball.animal.model;

import java.sql.Date;

public class AnimalVo {

    private String imageURL;
    private String DISCVRY_PLC_INFO;
    private String AnimalType;
    private String Species;
    private double weight;
    private String PblancId;
    private Date PblancBeginDate;
    private Date PblancEndDate;
    private String State;
    private Sex Sex;
    private Boolean IsNeutered;
    private String Note;

    public AnimalVo() {
    }

    public AnimalVo(String imageURL, String DISCVRY_PLC_INFO, String animalType, String species, double weight, String pblancId, Date pblancBeginDate, Date pblancEndDate, String state, Sex sex, Boolean isNeutered, String note) {
        this.imageURL = imageURL;
        this.DISCVRY_PLC_INFO = DISCVRY_PLC_INFO;
        AnimalType = animalType;
        Species = species;
        this.weight = weight;
        PblancId = pblancId;
        PblancBeginDate = pblancBeginDate;
        PblancEndDate = pblancEndDate;
        State = state;
        Sex = sex;
        IsNeutered = isNeutered;
        Note = note;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDISCVRY_PLC_INFO() {
        return DISCVRY_PLC_INFO;
    }

    public void setDISCVRY_PLC_INFO(String DISCVRY_PLC_INFO) {
        this.DISCVRY_PLC_INFO = DISCVRY_PLC_INFO;
    }

    public String getAnimalType() {
        return AnimalType;
    }

    public void setAnimalType(String animalType) {
        AnimalType = animalType;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPblancId() {
        return PblancId;
    }

    public void setPblancId(String pblancId) {
        PblancId = pblancId;
    }

    public Date getPblancBeginDate() {
        return PblancBeginDate;
    }

    public void setPblancBeginDate(Date pblancBeginDate) {
        PblancBeginDate = pblancBeginDate;
    }

    public java.util.Date getPblancEndDate() {
        return PblancEndDate;
    }

    public void setPblancEndDate(Date pblancEndDate) {
        PblancEndDate = pblancEndDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Sex getSex() {
        return Sex;
    }

    public void setSex(Sex sex) {
        Sex = sex;
    }

    public Boolean getNeutered() {
        return IsNeutered;
    }

    public void setNeutered(Boolean neutered) {
        IsNeutered = neutered;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "AnimalVo{" +
                "imageURL='" + imageURL + '\'' +
                ", DISCVRY_PLC_INFO='" + DISCVRY_PLC_INFO + '\'' +
                ", AnimalType='" + AnimalType + '\'' +
                ", Species='" + Species + '\'' +
                ", weight='" + weight + '\'' +
                ", PblancId='" + PblancId + '\'' +
                ", PblancBeginDate=" + PblancBeginDate +
                ", PblancEndDate=" + PblancEndDate +
                ", State='" + State + '\'' +
                ", Sex=" + Sex +
                ", IsNeutered=" + IsNeutered +
                ", Note='" + Note + '\'' +
                '}';
    }

}
