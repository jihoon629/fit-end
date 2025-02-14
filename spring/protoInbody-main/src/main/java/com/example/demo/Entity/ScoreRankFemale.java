package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "score_rank_female", indexes = { @Index(name = "idx_score_female", columnList = "score DESC") })
public class ScoreRankFemale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer sex;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double leanBodyMass;

    @Column(nullable = false)
    private Double fatMass;

    @Column(nullable = false)
    private Float fatPercentage;

    @Column(nullable = false)
    private Integer score;

    @Column(length = 50, nullable = false, unique = true)
    private String userId;

    public ScoreRankFemale() {}

    public ScoreRankFemale(Integer sex, Integer age, Double height, Double weight,
                           Double leanBodyMass, Double fatMass, Float fatPercentage, Integer score, String userId) {
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.leanBodyMass = leanBodyMass;
        this.fatMass = fatMass;
        this.fatPercentage = fatPercentage;
        this.score = score;
        this.userId = userId;
    }

    // Getter & Setter (Male과 동일)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getLeanBodyMass() { return leanBodyMass; }
    public void setLeanBodyMass(Double leanBodyMass) { this.leanBodyMass = leanBodyMass; }

    public Double getFatMass() { return fatMass; }
    public void setFatMass(Double fatMass) { this.fatMass = fatMass; }

    public Float getFatPercentage() { return fatPercentage; }
    public void setFatPercentage(Float fatPercentage) { this.fatPercentage = fatPercentage; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "ScoreRankFemale{" +
                "id=" + id +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", leanBodyMass=" + leanBodyMass +
                ", fatMass=" + fatMass +
                ", fatPercentage=" + fatPercentage +
                ", score=" + score +
                ", userId='" + userId + '\'' +
                '}';
    }
}
