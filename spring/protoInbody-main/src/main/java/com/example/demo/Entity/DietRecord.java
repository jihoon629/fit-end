package com.example.demo.Entity;

import java.util.Date;

import com.example.demo.Service.Convert.HasUserInfo;

import jakarta.persistence.*;

@Entity
public class DietRecord implements HasUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private UserInfo userInfo;

    @Column(nullable = false)
    private Date timestamp;

    @Column(nullable = false)
    private double totalcalori;

    @Column(nullable = false)
    private double totalcarbs;

    @Column(nullable = false)
    private double totalprotein;

    @Column(nullable = false)
    private double totalfat;

    @Column(nullable = true)
    private String dietMemo; // diet_memo 필드 추가

    @Column(nullable = true)
    private String foodName;

    public DietRecord() {
    }

    public DietRecord(Long id, UserInfo userInfo, Date timestamp, double totalcalori, double totalcarbs,
            double totalprotein, double totalfat, String dietMemo, String foodName) {
        this.id = id;
        this.userInfo = userInfo;
        this.timestamp = timestamp;
        this.totalcalori = totalcalori;
        this.totalcarbs = totalcarbs;
        this.totalprotein = totalprotein;
        this.totalfat = totalfat;
        this.dietMemo = dietMemo;
        this.foodName = foodName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalcalori() {
        return totalcalori;
    }

    public void setTotalcalori(double totalcalori) {
        this.totalcalori = totalcalori;
    }

    public double getTotalcarbs() {
        return totalcarbs;
    }

    public void setTotalcarbs(double totalcarbs) {
        this.totalcarbs = totalcarbs;
    }

    public double getTotalprotein() {
        return totalprotein;
    }

    public void setTotalprotein(double totalprotein) {
        this.totalprotein = totalprotein;
    }

    public double getTotalfat() {
        return totalfat;
    }

    public void setTotalfat(double totalfat) {
        this.totalfat = totalfat;
    }

    public String getDietMemo() {
        return dietMemo;
    }

    public void setDietMemo(String dietMemo) {
        this.dietMemo = dietMemo;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
