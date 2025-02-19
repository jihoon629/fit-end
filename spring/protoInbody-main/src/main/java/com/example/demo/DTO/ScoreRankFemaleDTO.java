package com.example.demo.DTO;

import com.example.demo.Entity.ScoreRankFemale;

public class ScoreRankFemaleDTO {
    private Long id;
    private int sex;
    private int age;
    private double height;
    private double weight;
    private double leanmass;
    private double fatmass;
    private double fatpercentage;
    private int score;
    private String userid;

    public ScoreRankFemaleDTO() {
    }

    // ScoreRankMale 객체를 받아들이는 생성자 추가
    public ScoreRankFemaleDTO(ScoreRankFemale ScoreRankFemale) {
        this.id = ScoreRankFemale.getId();
        this.sex = ScoreRankFemale.getSex();
        this.age = ScoreRankFemale.getAge();
        this.height = ScoreRankFemale.getHeight();
        this.weight = ScoreRankFemale.getWeight();
        this.leanmass = ScoreRankFemale.getLeanmass();
        this.fatmass = ScoreRankFemale.getFatmass();
        this.fatpercentage = ScoreRankFemale.getFatpercentage();
        this.score = ScoreRankFemale.getScore();
        this.userid = ScoreRankFemale.getUserInfo().getUserid();
    }

    public ScoreRankFemaleDTO(Long id, int sex, int age, double height, double weight, double leanmass, double fatmass,
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