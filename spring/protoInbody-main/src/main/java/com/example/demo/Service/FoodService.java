package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.FoodDto;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.RawFood.RawFood;
import com.example.demo.Repo.RepoDietRecord;
import com.example.demo.Repo.RepoRawFood;
import com.example.demo.Repo.RepoUserInfo;
import com.example.demo.Service.Utile.ConversionService;

@Service
public class FoodService {

    @Autowired
    private RepoUserInfo RepoUserInfo;
    @Autowired
    private RepoDietRecord RepoDietRecord;
    @Autowired
    private ConversionService ConversionService;
    @Autowired
    private RepoRawFood RepoRawFood;

    public boolean checkIfDataExists() {
        // 예: 첫 번째 레코드 확인 또는 카운트 확인
        long count = RepoRawFood.count();
        return count > 0;
    }

    public boolean saveFood(FoodDto FoodDto) {
        UserInfo userInfo = RepoUserInfo.findByUserid(FoodDto.getUserid());
        if (userInfo == null) {
            return false;
        }

        // 변환된 DietRecord 확인용 로그 추가
        DietRecord dietRecord = ConversionService.convertToEntity(FoodDto, DietRecord.class);
        System.out.println("변환된 DietRecord: " + dietRecord);

        // 저장하기 전에 필드 값이 비어있는지 확인 후 기본값 설정
        if (dietRecord.getEnerc() == 0.0)
            dietRecord.setEnerc(FoodDto.getEnerc());
        if (dietRecord.getChocdf() == 0.0)
            dietRecord.setChocdf(FoodDto.getChocdf());
        if (dietRecord.getProt() == 0.0)
            dietRecord.setProt(FoodDto.getProt());
        if (dietRecord.getFatce() == 0.0)
            dietRecord.setFatce(FoodDto.getFatce());

        RepoDietRecord.save(dietRecord);
        System.out.println("음식 기록이 성공적으로 저장되었습니다!");

        return true;
    }

    public List<FoodDto> getFoodDetails(String foodNm) {
        System.out.println("받은 음식이름: " + foodNm);

        List<FoodDto> foodDetailsList = new ArrayList<>();

        List<RawFood> rawFoods = RepoRawFood.findByFoodNmContaining(foodNm);
        System.out.println("시발: " + rawFoods);
        for (RawFood rawFood : rawFoods) {

            FoodDto foodDto = ConversionService.convertToDto(rawFood, FoodDto.class);

            foodDetailsList.add(foodDto);
        }
        System.out.println("tlqkf :" + foodDetailsList);

        return foodDetailsList;
    }

    // 로그인 사용자의 diet_record 조회
    public List<DietRecord> getDietRecordsByUser(String userid) {
        return RepoDietRecord.findByUserInfoUserid(userid);
    }
}
