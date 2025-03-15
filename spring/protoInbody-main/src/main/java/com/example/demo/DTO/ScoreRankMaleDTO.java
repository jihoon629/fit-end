package com.example.demo.DTO;

import javax.validation.constraints.*;

import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Service.Utile.HasUserId;

public class ScoreRankMaleDTO implements HasUserId {
    private Long id;
    @Min(value = 20)
    private int sex;
    private int age;
    private double height;
    private double weight;
    private double leanmass;
    private double fatmass;
    private double fatpercentage;
    private int score;
    private String userid;

    public ScoreRankMaleDTO() {
    }

    // ScoreRankMale 객체를 받아들이는 생성자 추가
    public ScoreRankMaleDTO(ScoreRankMale scoreRankMale) {
        this.id = scoreRankMale.getId();
        this.sex = scoreRankMale.getSex();
        this.age = scoreRankMale.getAge();
        this.height = scoreRankMale.getHeight();
        this.weight = scoreRankMale.getWeight();
        this.leanmass = scoreRankMale.getLeanmass();
        this.fatmass = scoreRankMale.getFatmass();
        this.fatpercentage = scoreRankMale.getFatpercentage();
        this.score = scoreRankMale.getScore();
        this.userid = scoreRankMale.getUserInfo().getUserid();

    }

    public ScoreRankMaleDTO(Long id, int sex, int age, double height, double weight, double leanmass, double fatmass,
            double fatpercentage, int score, String userid) {
        this.id = id;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.leanmass = leanmass;
        this.fatmass = fatmass;
        this.fatpercentage = fatpercentage;
        this.score = score;
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getLeanmass() {
        return leanmass;
    }

    public void setLeanmass(double leanmass) {
        this.leanmass = leanmass;
    }

    public double getFatmass() {
        return fatmass;
    }

    public void setFatmass(double fatmass) {
        this.fatmass = fatmass;
    }

    public double getFatpercentage() {
        return fatpercentage;
    }

    public void setFatpercentage(double fatpercentage) {
        this.fatpercentage = fatpercentage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}