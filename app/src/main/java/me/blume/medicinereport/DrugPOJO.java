package me.blume.medicinereport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugPOJO {

    @SerializedName("imgsrc")
    @Expose
    private String imgsrc;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("strength")
    @Expose
    private String strength;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("rec_dosage")
    @Expose
    private String recDosage;
    @SerializedName("Possible side-effects")
    @Expose
    private List<String> possibleSideEffects = null;
    @SerializedName("lot")
    @Expose
    private String lot;
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("link")
    @Expose
    private String link;

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getRecDosage() {
        return recDosage;
    }

    public void setRecDosage(String recDosage) {
        this.recDosage = recDosage;
    }

    public List<String> getPossibleSideEffects() {
        return possibleSideEffects;
    }

    public void setPossibleSideEffects(List<String> possibleSideEffects) {
        this.possibleSideEffects = possibleSideEffects;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
