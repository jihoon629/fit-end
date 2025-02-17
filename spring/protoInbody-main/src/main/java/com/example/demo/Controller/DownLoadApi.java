package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Entity.ScoreRankFemale;
import com.example.demo.Entity.ScoreRankMale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.Service.ScoreRankService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController // 다운로드 관련 컨트롤러입니다
@RequestMapping("/download")
public class DownLoadApi {
    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @Autowired
    private ScoreRankService ScoreRankService;

    @GetMapping("/recentuserbody/{userid}") // 최근 신체정보 기록 조회 컨트롤러
    public ResponseEntity<List<UserBodyInfo>> getRecentUserBodyRecords(@PathVariable String userid) {
        List<UserBodyInfo> records = UserBodyInfoService.getRecentUserBodyRecords(userid);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/scorerankmale") // 남성 스코어 랭킹 조회
    public ResponseEntity<List<ScoreRankMale>> getScoreRankMale() {
        return ResponseEntity.ok(ScoreRankService.showRankMale());
    }

    @GetMapping("/scorerankfemale") // 여성 스코어 랭킹 조회
    public ResponseEntity<List<ScoreRankFemale>> getScoreRankFemale() {
        return ResponseEntity.ok(ScoreRankService.showRankFemale());
    }

}
