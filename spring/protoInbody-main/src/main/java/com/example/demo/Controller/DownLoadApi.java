package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.UserBodyInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Service.UserBodyInfoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.demo.Service.ScoreRankService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController // userinfobody 관련 컨트롤러입니다
@RequestMapping("/userinfobody")
public class DownLoadApi {
    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @Autowired
    private ScoreRankService ScoreRankService;

    @PostMapping("/recorduserbody") // 신체정보 기록 컨트롤러
    public ResponseEntity<UserBodyInfoDTO> recordUserBody(@RequestBody UserBodyInfoDTO UserBodyInfoDTO) {
        System.out.println("[LOG] 데이터 수신: " + UserBodyInfoDTO);
        UserBodyInfoDTO savedRecord = UserBodyInfoService.recordeUserBodyInfo(UserBodyInfoDTO);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping("/recentuserbody/{userid}") // 최근 신체정보 기록 조회 컨트롤러
    public ResponseEntity<List<UserBodyInfoDTO>> getRecentUserBodyRecords(@PathVariable String userid) {

        List<UserBodyInfoDTO> records = UserBodyInfoService.getRecentUserBodyRecords(userid);

        return ResponseEntity.ok(records);
    }

    @GetMapping("/scorerankmale") // 남성 스코어 랭킹 조회
    public ResponseEntity<?> getScoreRankMale() {
        return ResponseEntity.ok(ScoreRankService.showRankMale1());
    }

    @GetMapping("/scorerankfemale") // 여성 스코어 랭킹 조회
    public ResponseEntity<?> getScoreRankFemale() {
        return ResponseEntity.ok(ScoreRankService.showRankFemale());
    }

}
