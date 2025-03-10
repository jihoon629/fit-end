package com.example.demo.Service.Convert;

import com.example.demo.DTO.FoodDto;
import com.example.demo.Entity.DietRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class EntityConversionService {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RepoUserInfo repoUserInfo;

    public <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        ModelMapper modelMapper = new ModelMapper();

        // 필드 매핑 추가 (FoodDto → DietRecord)
        modelMapper.typeMap(FoodDto.class, DietRecord.class).addMappings(mapper -> {
            mapper.map(FoodDto::getEnerc, DietRecord::setTotalcalori);
            mapper.map(FoodDto::getChocdf, DietRecord::setTotalcarbs);
            mapper.map(FoodDto::getProt, DietRecord::setTotalprotein);
            mapper.map(FoodDto::getFatce, DietRecord::setTotalfat);
            mapper.map(FoodDto::getFoodNm, DietRecord::setFoodName);
        });

        // DTO를 엔티티로 변환합니다.
        E entity = modelMapper.map(dto, entityClass);

        // DTO가 HasUserId 인터페이스를 구현하는지 확인합니다.
        if (dto instanceof HasUserId) {
            // UserId를 가져옵니다.
            String userId = ((HasUserId) dto).getUserid();
            // UserInfo를 데이터베이스에서 가져옵니다.
            UserInfo userInfo = repoUserInfo.findByUserid(userId);

            // 엔티티가 HasUserInfo 인터페이스를 구현하는지 확인합니다.
            if (entity instanceof HasUserInfo) {
                // UserInfo를 엔티티에 설정합니다.
                ((HasUserInfo) entity).setUserInfo(userInfo);
            }
        }
        return entity;
    }
}
