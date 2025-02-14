package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserInfoService;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.Service.ScoreRankMaleService;
import com.example.demo.Service.ScoreRankFemaleService;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Entity.ScoreRankFemale;

@RestController // 업로드 관련 컨트롤러 입니다
@RequestMapping("/upload")
public class UpLoadApi {

    @Autowired
    private UserInfoService UserInfoService;

    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @Autowired
    private ScoreRankMaleService scoreRankMaleService;

    @Autowired
    private ScoreRankFemaleService scoreRankFemaleService;

    @PostMapping("/register") // 회원가입 기능
    public UserInfo registerUser(@RequestBody UserInfo userInfo) {
        return UserInfoService.registerUser(userInfo);
    }

    @PostMapping("/recorduserbody") // 신체정보 기록 컨트롤러
    public ResponseEntity<UserBodyInfo> recordUserBody(@RequestBody UserBodyInfo userBodyInfo) {
        System.out.println("[LOG] 데이터 수신: " + userBodyInfo);
        UserBodyInfo savedRecord = UserBodyInfoService.recordeUserBodyInfo(userBodyInfo);
        return ResponseEntity.ok(savedRecord);
    }


    @PostMapping("/scorerankmale")
    public ResponseEntity<ScoreRankMale> saveScoreRankMale(@RequestBody ScoreRankMale scoreRankMale) {
        ScoreRankMale savedRank = scoreRankMaleService.save(scoreRankMale);
        return ResponseEntity.ok(savedRank);
    }

    @PostMapping("/scorerankfemale")
    public ResponseEntity<ScoreRankFemale> saveScoreRankFemale(@RequestBody ScoreRankFemale scoreRankFemale) {
        ScoreRankFemale savedRank = scoreRankFemaleService.save(scoreRankFemale);
        return ResponseEntity.ok(savedRank);
    }
}
