package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.UserBodyInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Service.UserBodyInfoService;

import com.example.demo.Service.ScoreRankService;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController // userinfobody 관련 컨트롤러입니다
@RequestMapping("/userinfobody")
@Tag(name = "User Body Info API", description = "사용자 신체 정보 및 스코어 랭킹 관련 API")
public class UserInfoBodyApi {
    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @Autowired
    private ScoreRankService ScoreRankService;


    @Operation(
            summary = "신체 정보 기록",
            description = "사용자의 신체 정보를 기록하고 저장된 정보를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "신체 정보 기록 성공",
                            content = @Content(schema = @Schema(implementation = UserBodyInfoDTO.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    @PostMapping("/recorduserbody") // 신체정보 기록 컨트롤러
    public ResponseEntity<UserBodyInfoDTO> recordUserBody(@RequestBody UserBodyInfoDTO userBodyInfoDTO) {

        System.out.println(userBodyInfoDTO);

        UserBodyInfoDTO savedRecord = UserBodyInfoService.recordeUserBodyInfo(userBodyInfoDTO);

        return ResponseEntity.ok(savedRecord);
    }


    @Operation(
            summary = "최근 신체 정보 기록 조회",
            description = "사용자 ID를 기반으로 최근 신체 정보 기록들을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "최근 신체 정보 기록 조회 성공",
                            content = @Content(schema = @Schema(implementation = UserBodyInfoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "해당 사용자의 신체 정보 기록이 없음")
            }
    )
    @GetMapping("/recentuserbody/{userid}") // 최근 신체정보 기록 조회 컨트롤러
    public ResponseEntity<List<UserBodyInfoDTO>> getRecentUserBodyRecords(@PathVariable String userid) {

        List<UserBodyInfoDTO> records = UserBodyInfoService.getRecentUserBodyRecords(userid);

        return ResponseEntity.ok(records);
    }


    @Operation(
            summary = "남성 스코어 랭킹 조회",
            description = "남성 사용자의 스코어 랭킹 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "남성 스코어 랭킹 조회 성공")
            }
    )
    @GetMapping("/scorerankmale") // 남성 스코어 랭킹 조회
    public ResponseEntity<?> getScoreRankMale() {
        return ResponseEntity.ok(ScoreRankService.showRankMale1());
    }


    @Operation(
            summary = "여성 스코어 랭킹 조회",
            description = "여성 사용자의 스코어 랭킹 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "여성 스코어 랭킹 조회 성공")
            }
    )
    @GetMapping("/scorerankfemale") // 여성 스코어 랭킹 조회
    public ResponseEntity<?> getScoreRankFemale() {
        return ResponseEntity.ok(ScoreRankService.showRankFemale());
    }
}
