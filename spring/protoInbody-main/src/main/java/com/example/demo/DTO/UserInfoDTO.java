package com.example.demo.DTO;

import java.time.LocalDate;

import com.example.demo.Service.Utile.HasUserId;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 DTO. 사용자 ID, 비밀번호, 이메일, 성별, 지역 및 생년월일 등의 정보를 포함합니다.")
public class UserInfoDTO implements HasUserId {
    @Schema(description = "고유 식별자", example = "1")
    private Long id;

    @Schema(description = "사용자 ID", example = "johnDoe")
    private String userid;

    @Schema(description = "비밀번호", example = "password123")
    private String password;

    @Schema(description = "이메일 주소", example = "john@example.com")
    private String email;

    @Schema(description = "성별 (2: 여성, 1: 남성)", example = "1")
    private int sex;

    @Schema(description = "기본 지역", example = "Seoul")
    private String region1;

    @Schema(description = "상세 지역", example = "Gangnam")
    private String region2;

    @Schema(description = "생년월일", example = "1990-01-01")
    private LocalDate birth;

    @Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String jwt;

    public UserInfoDTO() {

    }

    public UserInfoDTO(Long id, String userid, String password, String email, int sex, String region1, String region2,
            LocalDate birth, String jwt) {
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
