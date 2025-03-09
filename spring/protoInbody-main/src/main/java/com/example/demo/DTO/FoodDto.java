package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodDto {

    private String foodNm;
    private String mfrNm;
    private double enerc;
    private double prot;
    private double fatce;
    private double chocdf;
    private double foodSize;
    private String userid;
    private String dietMemo;  // 추가된 필드
    private Date timestamp;   // 추가된 필드

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
