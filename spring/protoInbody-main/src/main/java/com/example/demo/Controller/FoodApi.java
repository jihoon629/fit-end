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

@RestController // food에 관련된 컨트롤러
@RequestMapping("/food")
public class FoodApi {
    @Autowired
    FoodService FoodService;
    @Autowired
    SaveRawFood SaveRawFood;

    @GetMapping("/up")
    public String saveCsv() {
        SaveRawFood.saveFromCsv();
        return "절대 2번 누르지 마시오";
    }

    @GetMapping("/foodname/{foodNm}") // 음식 이름으로 검색하는 컨트롤러
    public ResponseEntity<List<FoodDto>> FoodName(@PathVariable String foodNm) {
        System.out.println(foodNm);
        List<FoodDto> foodDetails = FoodService.getFoodDetails(foodNm);
        return ResponseEntity.ok(foodDetails);
    }

    @PostMapping("/saveFoodRecord")
    public ResponseEntity<Map<String, String>> saveFoodRecord(@RequestBody FoodDto foodDto) {
        System.out.println("전송받은 음식 데이터: " + foodDto);

        if (foodDto == null || foodDto.getUserid() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "잘못된 요청: 사용자 ID 누락"));
        }

        FoodService.saveFood(foodDto);
        return ResponseEntity.ok(Map.of("message", "음식 기록이 성공적으로 저장되었습니다!"));
    }

    // 사용자의 diet_record 조회
    @GetMapping("/diet-records/{userid}")
    public ResponseEntity<List<DietRecord>> getUserDietRecords(@PathVariable String userid) {

        // 사용자 ID로 식단 기록 가져오기
        List<DietRecord> dietRecords = FoodService.getDietRecordsByUser(userid);
        return ResponseEntity.ok(dietRecords);

    }
}
