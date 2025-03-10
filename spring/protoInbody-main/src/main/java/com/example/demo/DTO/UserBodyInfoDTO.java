package com.example.demo.DTO;

import java.util.Date;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Service.Convert.HasUserId;

public class UserBodyInfoDTO implements HasUserId {
    private Long id;
    private String userid; // 추가된 필드
    private double height;
    private double weight;
    private double fatpercentage;
    private double fatmass;
    private double leanmass;
    private double bmi;
    private double inbodyScore;
    private Date date;
    private int sex;
    private int age;

    public UserBodyInfoDTO() {
    }

    public UserBodyInfoDTO(UserBodyInfo UserBodyInfo) {
        this.id = UserBodyInfo.getId();
        this.userid = UserBodyInfo.getUserInfo().getUserid(); // 추가된 필드 초기화
        this.height = UserBodyInfo.getHeight();
        this.weight = UserBodyInfo.getWeight();
        this.fatpercentage = UserBodyInfo.getFatpercentage();
        this.fatmass = UserBodyInfo.getFatMass();
        this.leanmass = UserBodyInfo.getLeanmass();
        this.bmi = UserBodyInfo.getBmi();
        this.inbodyScore = UserBodyInfo.getInbodyScore();
        this.date = UserBodyInfo.getDate();
        this.sex = UserBodyInfo.getSex();
        this.age = UserBodyInfo.getAge();
    }

    public UserBodyInfoDTO(Long id, String userid, double height, double weight, double fatpercentage,
            double fatmass, double leanmass, double bmi, double inbodyScore, Date date, int sex, int age) {
        this.id = id;
        this.userid = userid;
        this.height = height;
        this.weight = weight;
        this.fatpercentage = fatpercentage;
        this.fatmass = fatmass;
        this.leanmass = leanmass;
        this.bmi = bmi;
        this.inbodyScore = inbodyScore;
        this.date = date;
        this.sex = sex;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() { // 추가된 메서드
        return userid;
    }

    public void setUserid(String userid) { // 추가된 메서드
        this.userid = userid;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFatpercentage() {
        return fatpercentage;
    }

    public void setFatpercentage(double fatpercentage) {
        this.fatpercentage = fatpercentage;
    }

    public double getFatmass() {
        return fatmass;
    }

    public void setFatmass(double fatmass) {
        this.fatmass = fatmass;
    }

    public double getLeanmass() {
        return leanmass;
    }

    public void setLeanmass(double leanmass) {
        this.leanmass = leanmass;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getInbodyScore() {
        return inbodyScore;
    }

    public void setInbodyScore(double inbodyScore) {
        this.inbodyScore = inbodyScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}