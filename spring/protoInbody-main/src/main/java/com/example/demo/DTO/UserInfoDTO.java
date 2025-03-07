package com.example.demo.DTO;

import java.time.LocalDate;

public class UserInfoDTO {
    private Long id;
    private String userid;
    private String password;
    private String email;
    private int sex;
    private String region1;
    private String region2;
    private LocalDate birth;

    public UserInfoDTO() {

    }

    public UserInfoDTO(Long id, String userid, String password, String email, int sex, String region1, String region2,
            LocalDate birth) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.region1 = region1;
        this.region2 = region2;
        this.birth = birth;
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

}
