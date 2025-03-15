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
import com.example.demo.Service.Utile.EntityConversionService;

@Service
public class FoodService {

    @Autowired
    RepoUserInfo RepoUserInfo;
    @Autowired
    RepoDietRecord RepoDietRecord;
    @Autowired
    EntityConversionService EntityConversionService;
    @Autowired
    RepoRawFood RepoRawFood;

    public boolean saveFood(FoodDto FoodDto) {
        UserInfo userInfo = RepoUserInfo.findByUserid(FoodDto.getUserid());
        if (userInfo == null) {
            return false;
        }

        // 변환된 DietRecord 확인용 로그 추가
        DietRecord dietRecord = EntityConversionService.convertToEntity(FoodDto, DietRecord.class);
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
            FoodDto foodDto = new FoodDto();
            // rawFood 객체의 필드를 foodDto에 수동으로 매핑합니다.
            foodDto.setFoodNm(rawFood.getFoodNm());
            foodDto.setMfrNm(rawFood.getMetaData().getMfrNm());
            foodDto.setEnerc(rawFood.getNutrient().getEnerc());
            foodDto.setProt(rawFood.getNutrient().getProt()); // 단백질 설정
            foodDto.setFatce(rawFood.getNutrient().getFatce()); // 지방 설정
            foodDto.setChocdf(rawFood.getNutrient().getChocdf());
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
