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

@RestController // 공공 api 보내는 컨트롤러
public class TestApi {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RepoRawFood RepoRawFood;
    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @GetMapping("/api/data")
    public ResponseEntity<Map<String, Object>> getData(@RequestParam("jwt") String jwt,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
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
    public ResponseEntity<Map<String, Object>> getBodyData(@RequestParam("jwt") String jwt,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
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