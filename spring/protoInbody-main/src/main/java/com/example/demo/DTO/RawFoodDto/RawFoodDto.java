package com.example.demo.DTO.RawFoodDto;

public class RawFoodDto {

    private String foodCd;
    private String foodNm;
    private String dataCd;
    private String typeNm;
    private String foodOriginCd;
    private String foodOriginNm;
    private String foodLv3Cd;
    private String foodLv3Nm;
    private String foodLv4Cd;
    private String foodLv4Nm;
    private String foodLv5Cd;
    private String foodLv5Nm;
    private String foodLv6Cd;
    private String foodLv6Nm;
    private String foodLv7Cd;
    private String foodLv7Nm;
    private NutrientDto nutrient;
    private MetaDataDto metaData;

    public RawFoodDto() {
    }

    public RawFoodDto(String foodCd, String foodNm, String dataCd, String typeNm, String foodOriginCd,
            String foodOriginNm, String foodLv3Cd, String foodLv3Nm, String foodLv4Cd, String foodLv4Nm,
            String foodLv5Cd, String foodLv5Nm, String foodLv6Cd, String foodLv6Nm, String foodLv7Cd, String foodLv7Nm,
            NutrientDto nutrient, MetaDataDto metaData) {
        this.foodCd = foodCd;
        this.foodNm = foodNm;
        this.dataCd = dataCd;
        this.typeNm = typeNm;
        this.foodOriginCd = foodOriginCd;
        this.foodOriginNm = foodOriginNm;
        this.foodLv3Cd = foodLv3Cd;
        this.foodLv3Nm = foodLv3Nm;
        this.foodLv4Cd = foodLv4Cd;
        this.foodLv4Nm = foodLv4Nm;
        this.foodLv5Cd = foodLv5Cd;
        this.foodLv5Nm = foodLv5Nm;
        this.foodLv6Cd = foodLv6Cd;
        this.foodLv6Nm = foodLv6Nm;
        this.foodLv7Cd = foodLv7Cd;
        this.foodLv7Nm = foodLv7Nm;
        this.nutrient = nutrient;
        this.metaData = metaData;
    }

    public String getFoodCd() {
        return foodCd;
    }

    public void setFoodCd(String foodCd) {
        this.foodCd = foodCd;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public String getDataCd() {
        return dataCd;
    }

    public void setDataCd(String dataCd) {
        this.dataCd = dataCd;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }

    public String getFoodOriginCd() {
        return foodOriginCd;
    }

    public void setFoodOriginCd(String foodOriginCd) {
        this.foodOriginCd = foodOriginCd;
    }

    public String getFoodOriginNm() {
        return foodOriginNm;
    }

    public void setFoodOriginNm(String foodOriginNm) {
        this.foodOriginNm = foodOriginNm;
    }

    public String getFoodLv3Cd() {
        return foodLv3Cd;
    }

    public void setFoodLv3Cd(String foodLv3Cd) {
        this.foodLv3Cd = foodLv3Cd;
    }

    public String getFoodLv3Nm() {
        return foodLv3Nm;
    }

    public void setFoodLv3Nm(String foodLv3Nm) {
        this.foodLv3Nm = foodLv3Nm;
    }

    public String getFoodLv4Cd() {
        return foodLv4Cd;
    }

    public void setFoodLv4Cd(String foodLv4Cd) {
        this.foodLv4Cd = foodLv4Cd;
    }

    public String getFoodLv4Nm() {
        return foodLv4Nm;
    }

    public void setFoodLv4Nm(String foodLv4Nm) {
        this.foodLv4Nm = foodLv4Nm;
    }

    public String getFoodLv5Cd() {
        return foodLv5Cd;
    }

    public void setFoodLv5Cd(String foodLv5Cd) {
        this.foodLv5Cd = foodLv5Cd;
    }

    public String getFoodLv5Nm() {
        return foodLv5Nm;
    }

    public void setFoodLv5Nm(String foodLv5Nm) {
        this.foodLv5Nm = foodLv5Nm;
    }

    public String getFoodLv6Cd() {
        return foodLv6Cd;
    }

    public void setFoodLv6Cd(String foodLv6Cd) {
        this.foodLv6Cd = foodLv6Cd;
    }

    public String getFoodLv6Nm() {
        return foodLv6Nm;
    }

    public void setFoodLv6Nm(String foodLv6Nm) {
        this.foodLv6Nm = foodLv6Nm;
    }

    public String getFoodLv7Cd() {
        return foodLv7Cd;
    }

    public void setFoodLv7Cd(String foodLv7Cd) {
        this.foodLv7Cd = foodLv7Cd;
    }

    public String getFoodLv7Nm() {
        return foodLv7Nm;
    }

    public void setFoodLv7Nm(String foodLv7Nm) {
        this.foodLv7Nm = foodLv7Nm;
    }

    public NutrientDto getNutrient() {
        return nutrient;
    }

    public void setNutrient(NutrientDto nutrient) {
        this.nutrient = nutrient;
    }

    public MetaDataDto getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaDataDto metaData) {
        this.metaData = metaData;
    }

}