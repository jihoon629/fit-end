package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserBodyInfoDTO;
import com.example.demo.DTO.RawFoodDto.MetaDataDto;
import com.example.demo.DTO.RawFoodDto.NutrientDto;
import com.example.demo.DTO.RawFoodDto.RawFoodDto;
import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Entity.RawFood.RawFood;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.Repo.RawFoodSpecification;
import com.example.demo.Repo.RepoRawFood;
import com.example.demo.Repo.RepoUserBodyInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController // 공공 api 보내는 컨트롤러
public class TestApi {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RepoRawFood RepoRawFood;
    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @GetMapping("/api/data")
    @Operation(
            summary = "RawFood 데이터 조회",
            description = "JWT를 검증한 후 조건에 맞는 RawFood 데이터를 검색 및 페이징 처리하여 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "데이터 조회 성공",
                            content = @Content(schema = @Schema(implementation = RawFood.class))),
                    @ApiResponse(responseCode = "401", description = "JWT 검증 실패", content = @Content),
                    @ApiResponse(responseCode = "404", description = "검색 결과가 없음", content = @Content)
            }
    )
    public ResponseEntity<Map<String, Object>> getData(
            @Parameter(description = "JWT 토큰", example = "your.jwt.token") @RequestParam("jwt") String jwt,
            @Parameter(description = "페이지 번호", example = "1") @RequestParam("pageNo") int pageNo,
            @Parameter(description = "한 페이지당 행 수", example = "10") @RequestParam("numOfRows") int numOfRows,
            @ModelAttribute RawFoodDto RawFoodDto,
            @ModelAttribute NutrientDto NutrientDto,
            @ModelAttribute MetaDataDto MetaDataDto) {

        String username = jwtUtil.extractUsername(jwt);
        if (username != null && jwtUtil.validateToken(jwt, username)) {
            // JSON 데이터 생성
            List<RawFood> rawFoods = RepoRawFood
                    .findAll(RawFoodSpecification.getRawFoodSpecification(RawFoodDto, NutrientDto, MetaDataDto));
            if (rawFoods.isEmpty()) {
                // 검색 결과가 없을 때
                Map<String, Object> noData = new HashMap<>();
                noData.put("message", "검색 결과가 없습니다.");
                return ResponseEntity.ok(noData);
            }

            // 페이지네이션 적용
            int start = (pageNo - 1) * numOfRows;
            int end = Math.min(start + numOfRows, rawFoods.size());
            List<RawFood> paginatedRawFoods = rawFoods.subList(start, end);

            // JSON 데이터 생성
            Map<String, Object> data = new HashMap<>();
            data.put("username", username);
            data.put("rawFoods", paginatedRawFoods); // 페이징된 RawFood 데이터 추가

            return ResponseEntity.ok(data);
        } else {
            // JWT 검증 실패 시
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("error", "JWT 검증에 실패했습니다.");
            return ResponseEntity.status(401).body(errorData);
        }
    }

    @GetMapping("/api/body")
    @Operation(
            summary = "User Body Info 데이터 조회",
            description = "JWT를 검증한 후 조건에 맞는 사용자 신체 정보 데이터를 검색 및 페이징 처리하여 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "데이터 조회 성공",
                            content = @Content(schema = @Schema(implementation = UserBodyInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "JWT 검증 실패", content = @Content)
            }
    )
    public ResponseEntity<Map<String, Object>> getBodyData(
            @Parameter(description = "JWT 토큰", example = "your.jwt.token") @RequestParam("jwt") String jwt,
            @Parameter(description = "페이지 번호", example = "1") @RequestParam("pageNo") int pageNo,
            @Parameter(description = "한 페이지당 행 수", example = "10") @RequestParam("numOfRows") int numOfRows,
            @ModelAttribute UserBodyInfoDTO UserBodyInfoDTO) {
        String username = jwtUtil.extractUsername(jwt);
        if (username != null && jwtUtil.validateToken(jwt, username)) {
            List<UserBodyInfo> userBodyInfo;
            if (UserBodyInfoDTO.getAge() != 0 && UserBodyInfoDTO.getSex() != 0) {
                userBodyInfo = RepoUserBodyInfo.findLatestUserBodyInfoByAgeAndSex(
                        UserBodyInfoDTO.getAge(), UserBodyInfoDTO.getSex());
            } else if (UserBodyInfoDTO.getAge() != 0) {
                userBodyInfo = RepoUserBodyInfo.findLatestUserBodyInfoByAge(UserBodyInfoDTO.getAge());
            } else if (UserBodyInfoDTO.getSex() != 0) {
                userBodyInfo = RepoUserBodyInfo.findLatestUserBodyInfoBySex(UserBodyInfoDTO.getSex());
            } else {
                userBodyInfo = new ArrayList<>();
            }

            // 페이징 적용
            int start = (pageNo - 1) * numOfRows;
            int end = Math.min(start + numOfRows, userBodyInfo.size());
            List<UserBodyInfo> paginatedUserBodyInfo = userBodyInfo.subList(start, end);

            List<UserBodyInfoDTO> userBodyInfoDTOs = paginatedUserBodyInfo.stream()
                    .map(info -> new UserBodyInfoDTO(
                            info.getHeight(),
                            info.getWeight(),
                            info.getFatpercentage(),
                            info.getLeanmass(),
                            info.getBmi(),
                            info.getInbodyScore(),
                            info.getSex(),
                            info.getAge(),
                            info.getFatMass()))
                    .collect(Collectors.toList());

            Map<String, Object> data = new HashMap<>();
            data.put("username", username);
            data.put("userBodyInfo", userBodyInfoDTOs);

            return ResponseEntity.ok(data);
        } else {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("error", "JWT 검증에 실패했습니다.");
            return ResponseEntity.status(401).body(errorData);
        }
    }
}