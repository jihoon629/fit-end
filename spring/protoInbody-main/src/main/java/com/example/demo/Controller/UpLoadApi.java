package com.example.demo.Controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserInfoService;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserBodyInfoDTO;
import com.example.demo.DTO.UserInfoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 업로드 관련 컨트롤러 입니다
@RequestMapping("/upload")
@Tag(name = "업로드 API", description = "사용자의 정보 및 데이터를 업로드하는 API")
public class UpLoadApi {

    @Autowired
    private UserInfoService UserInfoService;

    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @PostMapping("/register") // 회원가입 기능
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "등록할 사용자 정보",
            required = true,
            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))
    ))
    public UserInfoDTO registerUser(
            @RequestBody UserInfoDTO UserInfoDTO) {
        return UserInfoService.registerUser(UserInfoDTO);
    }

    @PostMapping("/recorduserbody") // 신체정보 기록 컨트롤러
    @Operation(summary = "신체정보 기록", description = "사용자의 신체 정보를 기록합니다.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "기록할 신체 정보",
                required = true,
                content = @Content(schema = @Schema(implementation = UserBodyInfoDTO.class))
        ))
    public ResponseEntity<UserBodyInfoDTO> recordUserBody(@RequestBody UserBodyInfoDTO UserBodyInfoDTO) {
        System.out.println("[LOG] 데이터 수신: " + UserBodyInfoDTO);
        UserBodyInfoDTO savedRecord = UserBodyInfoService.recordeUserBodyInfo(UserBodyInfoDTO);
        return ResponseEntity.ok(savedRecord);
    }

    @PostMapping("/recordfood") // 신체정보 기록 컨트롤러
    @Operation(summary = "음식 정보 기록", description = "사용자의 음식 섭취 정보를 기록합니다.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "기록할 음식 정보",
                required = true,
                content = @Content(schema = @Schema(implementation = FoodDto.class))
        ))
    public ResponseEntity<FoodDto> recordfood(@RequestBody FoodDto FoodDto) {
        System.out.println("[LOG] 데이터 수신: " + FoodDto);
        return ResponseEntity.ok(FoodDto);
    }

}
