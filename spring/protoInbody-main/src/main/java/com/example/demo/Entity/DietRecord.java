package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.*;

public class DietRecord {

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

    public DietRecord() {
    }

    public DietRecord(Long id, UserInfo userInfo, Date timestamp, double totalcalori, double totalcarbs,
            double totalprotein, double totalfat) {
        this.id = id;
        this.userInfo = userInfo;
        this.timestamp = timestamp;
        this.totalcalori = totalcalori;
        this.totalcarbs = totalcarbs;
        this.totalprotein = totalprotein;
        this.totalfat = totalfat;
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

}
