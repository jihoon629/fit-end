package com.example.demo.DTO;

import java.util.Date;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Service.Convert.HasUserId;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자의 신체 정보를 담고 있는 DTO")
public class UserBodyInfoDTO implements HasUserId {

    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "사용자 ID", example = "user789")
    private String userid;

    @Schema(description = "키 (cm)", example = "175.0")
    private double height;

    @Schema(description = "몸무게 (kg)", example = "72.5")
    private double weight;

    @Schema(description = "체지방률 (%)", example = "22.5")
    private double fatpercentage;

    @Schema(description = "체지방량 (kg)", example = "16.3")
    private double fatmass;

    @Schema(description = "제지방량 (kg)", example = "56.2")
    private double leanmass;

    @Schema(description = "BMI (체질량지수)", example = "24.1")
    private double bmi;

    @Schema(description = "인바디 점수", example = "80.5")
    private double inbodyScore;

    @Schema(description = "측정 날짜", example = "2025-03-15T10:30:00")
    private Date date;

    @Schema(description = "성별 (0: 남성, 1: 여성)", example = "0")
    private int sex;

    @Schema(description = "나이", example = "30")
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

    public UserBodyInfoDTO(double height, double weight, double fatpercentage, double leanmass, double bmi,
            double inbodyScore, int sex, int age, double fatMass) {
        this.height = height;
        this.weight = weight;
        this.fatpercentage = fatpercentage;
        this.leanmass = leanmass;
        this.bmi = bmi;
        this.inbodyScore = inbodyScore;
        this.sex = sex;
        this.age = age;
        this.fatmass = fatMass;
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

    @Override
    public String toString() {
        return "UserBodyInfoDTO [id=" + id + ", userid=" + userid + ", height=" + height + ", weight=" + weight
                + ", fatpercentage=" + fatpercentage + ", fatmass=" + fatmass + ", leanmass=" + leanmass + ", bmi="
                + bmi + ", inbodyScore=" + inbodyScore + ", date=" + date + ", sex=" + sex + ", age=" + age + "]";
    }

}