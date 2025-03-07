package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodDto {

    private String foodNm;
    private double enerc;
    private double prot;
    private double fatce;
    private double chocdf;
    private double foodSize;

    public FoodDto() {
    }

    public FoodDto(String foodNm, double enerc, double prot, double fatce, double chocdf, double foodSize) {
        this.foodNm = foodNm;
        this.enerc = enerc;
        this.prot = prot;
        this.fatce = fatce;
        this.chocdf = chocdf;
        this.foodSize = foodSize;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public double getEnerc() {
        return enerc;
    }

    public void setEnerc(double enerc) {
        this.enerc = enerc;
    }

    public double getProt() {
        return prot;
    }

    public void setProt(double prot) {
        this.prot = prot;
    }

    public double getFatce() {
        return fatce;
    }

    public void setFatce(double fatce) {
        this.fatce = fatce;
    }

    public double getChocdf() {
        return chocdf;
    }

    public void setChocdf(double chocdf) {
        this.chocdf = chocdf;
    }

    public double getFoodSize() {
        return foodSize;
    }

    public void setFoodSize(double foodSize) {
        this.foodSize = foodSize;
    }

    @Override
    public String toString() {
        return "FoodDto [foodNm=" + foodNm + ", enerc=" + enerc + ", prot=" + prot + ", fatce=" + fatce + ", chocdf="
                + chocdf + ", foodSize=" + foodSize + "]";
    }

}
