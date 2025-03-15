package com.example.demo.DTO;

import java.time.LocalDate;

import com.example.demo.Service.Convert.HasUserId;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보를 담고 있는 DTO")
public class UserInfoDTO implements HasUserId {

    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Schema(description = "사용자 계정 ID", example = "user123")
    private String userid;

    @Schema(description = "사용자 비밀번호 (블로피시 단방향 해시 암호화)", example = "hashed_password")
    private String password;

    @Schema(description = "사용자 이메일", example = "user123@example.com")
    private String email;

    @Schema(description = "성별 (0: 남성, 1: 여성)", example = "1")
    private int sex;

    @Schema(description = "사용자 거주 지역 (대분류)", example = "서울특별시")
    private String region1;

    @Schema(description = "사용자 거주 지역 (소분류)", example = "강남구")
    private String region2;

    @Schema(description = "사용자 생년월일 (YYYY-MM-DD)", example = "1995-08-15")
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
