package com.example.demo.DTO.RawFoodDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "원재료 식품 정보를 담고 있는 DTO")
public class RawFoodDto {

    @Schema(description = "식품 코드", example = "F001")
    private String foodCd;

    @Schema(description = "식품명", example = "사과")
    private String foodNm;

    @Schema(description = "데이터 코드", example = "D001")
    private String dataCd;

    @Schema(description = "식품 유형명", example = "과일")
    private String typeNm;

    @Schema(description = "원산지 코드", example = "KR")
    private String foodOriginCd;

    @Schema(description = "원산지명", example = "대한민국")
    private String foodOriginNm;

    @Schema(description = "식품 분류 3단계 코드", example = "FRUIT3")
    private String foodLv3Cd;

    @Schema(description = "식품 분류 3단계명", example = "과일류")
    private String foodLv3Nm;

    @Schema(description = "식품 분류 4단계 코드", example = "APPLE4")
    private String foodLv4Cd;

    @Schema(description = "식품 분류 4단계명", example = "사과류")
    private String foodLv4Nm;

    @Schema(description = "식품 분류 5단계 코드", example = "RED_APPLE5")
    private String foodLv5Cd;

    @Schema(description = "식품 분류 5단계명", example = "홍옥")
    private String foodLv5Nm;

    @Schema(description = "식품 분류 6단계 코드", example = "FUJI_APPLE6")
    private String foodLv6Cd;

    @Schema(description = "식품 분류 6단계명", example = "후지사과")
    private String foodLv6Nm;

    @Schema(description = "식품 분류 7단계 코드", example = "GOLDEN_APPLE7")
    private String foodLv7Cd;

    @Schema(description = "식품 분류 7단계명", example = "골든사과")
    private String foodLv7Nm;

    @Schema(description = "영양소 정보", example = "NutrientDto 객체 포함")
    private NutrientDto nutrient;

    @Schema(description = "메타데이터 정보", example = "MetaDataDto 객체 포함")
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

    @Override
    public String toString() {
        return "RawFoodDto [foodCd=" + foodCd + ", foodNm=" + foodNm + ", dataCd=" + dataCd + ", typeNm=" + typeNm
                + ", foodOriginCd=" + foodOriginCd + ", foodOriginNm=" + foodOriginNm + ", foodLv3Cd=" + foodLv3Cd
                + ", foodLv3Nm=" + foodLv3Nm + ", foodLv4Cd=" + foodLv4Cd + ", foodLv4Nm=" + foodLv4Nm + ", foodLv5Cd="
                + foodLv5Cd + ", foodLv5Nm=" + foodLv5Nm + ", foodLv6Cd=" + foodLv6Cd + ", foodLv6Nm=" + foodLv6Nm
                + ", foodLv7Cd=" + foodLv7Cd + ", foodLv7Nm=" + foodLv7Nm + ", nutrient=" + nutrient + ", metaData="
                + metaData + "]";
    }

}