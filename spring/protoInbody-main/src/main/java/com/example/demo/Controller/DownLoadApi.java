package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.ScoreRankFemaleDTO;
import com.example.demo.DTO.ScoreRankMaleDTO;
import com.example.demo.DTO.UserBodyInfoDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.Service.ScoreRankService;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 다운로드 관련 컨트롤러입니다
@RequestMapping("/download")
@Tag(name = "다운로드 API", description = "사용자의 기록 및 스코어 랭킹을 다운로드하는 API")
public class DownLoadApi {
    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @Autowired
    private ScoreRankService ScoreRankService;

    @GetMapping("/recentuserbody/{userid}") // 최근 신체정보 기록 조회 컨트롤러
    @Operation(summary = "최근 신체 정보 기록 조회", description = "특정 사용자의 최근 신체 정보 기록을 반환합니다.")
    public ResponseEntity<List<UserBodyInfoDTO>> getRecentUserBodyRecords(
            @Parameter(description = "조회할 사용자 ID", example = "user123")
            @PathVariable String userid) {

        List<UserBodyInfoDTO> records = UserBodyInfoService.getRecentUserBodyRecords(userid);

        return ResponseEntity.ok(records);
    }

    @GetMapping("/scorerankmale") // 남성 스코어 랭킹 조회
    @Operation(summary = "남성 스코어 랭킹 조회", description = "남성 사용자의 스코어 랭킹 정보를 반환합니다.")
    public ResponseEntity<List<ScoreRankMaleDTO>> getScoreRankMale() {
        return ResponseEntity.ok(ScoreRankService.showRankMale());
    }

    @GetMapping("/scorerankfemale") // 여성 스코어 랭킹 조회
    @Operation(summary = "여성 스코어 랭킹 조회", description = "여성 사용자의 스코어 랭킹 정보를 반환합니다.")
    public ResponseEntity<List<ScoreRankFemaleDTO>> getScoreRankFemale() {
        return ResponseEntity.ok(ScoreRankService.showRankFemale());
    }

}
