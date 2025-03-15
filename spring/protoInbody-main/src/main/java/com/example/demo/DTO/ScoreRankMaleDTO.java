package com.example.demo.DTO;

import javax.validation.constraints.*;

import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Service.Convert.HasUserId;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "남성 점수 랭킹 정보를 담고 있는 DTO")
public class ScoreRankMaleDTO implements HasUserId {

    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "성별 (0: 남성, 1: 여성)", example = "0")
    @Min(value = 0, message = "성별 값은 0(남성) 또는 1(여성)이어야 합니다.")
    private int sex;

    @Schema(description = "나이", example = "30")
    private int age;

    @Schema(description = "키 (cm)", example = "175.0")
    private double height;

    @Schema(description = "몸무게 (kg)", example = "70.5")
    private double weight;

    @Schema(description = "제지방량 (kg)", example = "55.0")
    private double leanmass;

    @Schema(description = "체지방량 (kg)", example = "15.5")
    private double fatmass;

    @Schema(description = "체지방률 (%)", example = "22.0")
    private double fatpercentage;

    @Schema(description = "점수", example = "90")
    private int score;

    @Schema(description = "사용자 ID", example = "user456")
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