package com.example.demo.Entity;

import com.example.demo.Service.Utile.HasUserInfo;

import jakarta.persistence.*;

@Entity
@Table(name = "score_rank_female", indexes = { @Index(name = "idx_score_female", columnList = "score DESC") })
public class ScoreRankFemale implements HasUserInfo {

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private UserInfo userInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int sex;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double leanmass;

    @Column(nullable = false)
    private double fatmass;

    @Column(nullable = false)
    private double fatpercentage;

    @Column(nullable = false)
    private int score;

    public ScoreRankFemale() {
    }

    public ScoreRankFemale(UserInfo userInfo, Long id, int sex, int age, double height, double weight, double leanmass,
            double fatmass, double fatpercentage, int score) {
        this.userInfo = userInfo;
        this.id = id;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.leanmass = leanmass;
        this.fatmass = fatmass;
        this.fatpercentage = fatpercentage;
        this.score = score;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

}
