package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity // db랑 연동될떄 필요한 클래스랑 어노테이션이에요 연관되는 레포랑 연결해주세요 세터게터 필수
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pri키

    @NotBlank
    @Column(unique = true, nullable = false)
    private String userid; // 유저 아이디

    @NotBlank
    @Column(nullable = false)
    private String password; // 비번

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email; // 이메일

    @NotBlank
    @Column(nullable = false)
    private int sex; // 성별

    @NotBlank
    @Column(nullable = false)
    private String region1; // 도/광역시

    @NotBlank
    @Column(nullable = false)
    private String region2; // 시/구

    @NotBlank
    @Column(nullable = false)
    private LocalDate birth; // 생년 월일

    private String jwt;

    public UserInfo() {
    }

    public UserInfo(Long id, @NotBlank String userid, @NotBlank String password, @NotBlank String email,
            @NotBlank int sex, @NotBlank String region1, @NotBlank String region2, @NotBlank LocalDate birth,
            String jwt) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.region1 = region1;
        this.region2 = region2;
        this.birth = birth;
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}