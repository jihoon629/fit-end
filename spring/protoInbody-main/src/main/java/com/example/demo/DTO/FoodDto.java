package com.example.demo.DTO;

import com.example.demo.Service.Utile.HasUserId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "음식 정보 DTO. 영양 정보, 사용자 정보 및 추가 메타데이터를 포함합니다.")
public class FoodDto implements HasUserId {

    @Schema(description = "음식 이름", example = "김치")
    private String foodNm;

    @Schema(description = "제조사 이름", example = "한국식품")
    private String mfrNm;

    @Schema(description = "에너지 (kcal)", example = "100.0")
    private double enerc;

    @Schema(description = "단백질 (g)", example = "5.0")
    private double prot;

    @Schema(description = "지방 (g)", example = "2.0")
    private double fatce;

    @Schema(description = "탄수화물 (g)", example = "15.0")
    private double chocdf;

    @Schema(description = "음식 크기 또는 용량", example = "200.0")
    private double foodSize;

    @Schema(description = "사용자 ID", example = "user123")
    private String userid;

    @Schema(description = "식단 메모", example = "저염식 옵션")
    private String dietMemo;

    @Schema(description = "기록 생성 시각")
    private Date timestamp;

    public FoodDto() {
    }

    public FoodDto(String foodNm, String mfrNm, double enerc, double prot, double fatce, double chocdf,
            double foodSize, String userid, String dietMemo, Date timestamp) {
        this.foodNm = foodNm;
        this.mfrNm = mfrNm;
        this.enerc = enerc;
        this.prot = prot;
        this.fatce = fatce;
        this.chocdf = chocdf;
        this.foodSize = foodSize;
        this.userid = userid;
        this.dietMemo = dietMemo;
        this.timestamp = timestamp;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public String getMfrNm() {
        return mfrNm;
    }

    public void setMfrNm(String mfrNm) {
        this.mfrNm = mfrNm;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDietMemo() {
        return dietMemo;
    }

    public void setDietMemo(String dietMemo) {
        this.dietMemo = dietMemo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FoodDto [foodNm=" + foodNm + ", mfrNm=" + mfrNm + ", enerc=" + enerc + ", prot=" + prot +
                ", fatce=" + fatce + ", chocdf=" + chocdf + ", foodSize=" + foodSize +
                ", userid=" + userid + ", dietMemo=" + dietMemo + ", timestamp=" + timestamp + "]";
    }

}
