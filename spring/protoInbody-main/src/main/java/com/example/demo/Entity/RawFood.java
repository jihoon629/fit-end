package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pri키 private String foodNm;
    private String mfrNm;
    private String foodNm;
    private double enerc;
    private double prot;
    private double fatce;
    private double chocdf;
    private double foodSize;

    public RawFood() {
    }

    public RawFood(Long id, String mfrNm, String foodNm, double enerc, double prot, double fatce, double chocdf,
            double foodSize) {
        this.id = id;
        this.mfrNm = mfrNm;
        this.foodNm = foodNm;
        this.enerc = enerc;
        this.prot = prot;
        this.fatce = fatce;
        this.chocdf = chocdf;
        this.foodSize = foodSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMfrNm() {
        return mfrNm;
    }

    public void setMfrNm(String mfrNm) {
        this.mfrNm = mfrNm;
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

}
