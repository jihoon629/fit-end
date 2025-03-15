package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.FoodDto;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Service.FoodService;
import com.example.demo.Service.Utile.SaveRawFood;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController // food에 관련된 컨트롤러
@RequestMapping("/food")
@Tag(name = "Food API", description = "음식 관련 API (검색, 저장, 조회)")
public class FoodApi {
    @Autowired
    private FoodService FoodService;
    @Autowired
    private SaveRawFood SaveRawFood;

    @GetMapping("/up")
    @Operation(
            summary = "CSV 데이터 저장",
            description = "내부적으로 CSV에서 음식 데이터를 읽고 저장하는 API입니다. 주의: 여러 번 실행하면 중복 저장될 수 있습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "CSV 데이터 저장 성공"),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류")
            }
    )
    public String saveCsv() {
        SaveRawFood.saveFromCsv();
        return "절대 2번 누르지 마시오";
    }

    @GetMapping("/foodname/{foodNm}") // 음식 이름으로 검색하는 컨트롤러
    @Operation(
            summary = "음식 이름으로 검색",
            description = "사용자가 입력한 음식 이름을 기반으로 영양 정보를 검색합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "음식 정보 조회 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = FoodDto.class)))),
                    @ApiResponse(responseCode = "404", description = "해당 음식 정보 없음")
            }
    )
    public ResponseEntity<List<FoodDto>> FoodName(
            @Parameter(description = "검색할 음식 이름", example = "김치")
            @PathVariable String foodNm
    ) {
        System.out.println(foodNm);
        List<FoodDto> foodDetails = FoodService.getFoodDetails(foodNm);
        return ResponseEntity.ok(foodDetails);
    }

    @PostMapping("/saveFoodRecord")
    @Operation(
            summary = "음식 기록 저장",
            description = "사용자의 음식 섭취 기록을 저장합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "음식 기록 저장 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청: 사용자 ID 누락"),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류")
            }
    )
    public ResponseEntity<Map<String, String>> saveFoodRecord(
            @RequestBody FoodDto foodDto //Spring의 @RequestBody만 유지해야함 스웨거랑 충돌남
    ) {
        System.out.println("전송받은 음식 데이터: " + foodDto);

        if (foodDto == null || foodDto.getUserid() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "잘못된 요청: 사용자 ID 누락"));
        }

        FoodService.saveFood(foodDto);
        return ResponseEntity.ok(Map.of("message", "음식 기록이 성공적으로 저장되었습니다!"));
    }

    @GetMapping("/diet-records/{userid}")
    @Operation(
            summary = "사용자 식단 기록 조회",
            description = "사용자의 식단 기록을 조회하는 API입니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "식단 기록 조회 성공",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = DietRecord.class)))),
                    @ApiResponse(responseCode = "404", description = "사용자 식단 기록 없음")
            }
    )
    public ResponseEntity<List<DietRecord>> getUserDietRecords(
            @Parameter(description = "사용자 ID", example = "testuser")
            @PathVariable String userid
    ) {
        List<DietRecord> dietRecords = FoodService.getDietRecordsByUser(userid);
        return ResponseEntity.ok(dietRecords);
    }
}
