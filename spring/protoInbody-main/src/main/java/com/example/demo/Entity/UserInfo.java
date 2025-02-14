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
    private String location; // 활동 지역

    @NotBlank
    @Column(nullable = false)
    private LocalDate birth; // 생년 월일

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

}
