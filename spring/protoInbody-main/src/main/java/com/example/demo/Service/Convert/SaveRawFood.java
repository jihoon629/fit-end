package com.example.demo.Service.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RawFoodDto;
import com.example.demo.Repo.RepoRawFood;
import com.example.demo.Entity.RawFood;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

@Service
public class SaveRawFood {

    @Autowired
    RepoRawFood RepoRawFood;
    @Autowired
    EntityConversionService EntityConversionService;

    public void RawFood(RawFoodDto RawFoodDto) {

        RepoRawFood.save(EntityConversionService.convertToEntity(RawFoodDto, RawFood.class));

    }

    String[] a = { "식품코드", "식품명", "데이터구분코드", "데이터구분명", "식품기원코드", "식품기원명", "식품대분류코드", "식품대분류명", "대표식품코드", "대표식품명",
            "식품중분류코드", "식품중분류명", "식품소분류코드", "식품소분류명", "식품세분류코드", "식품세분류명", "영양성분함량기준량", "에너지(kcal)", "수분(g)", "단백질(g)",
            "지방(g)", "회분(g)", "탄수화물(g)", "당류(g)", "식이섬유(g)", "칼슘(mg)", "철(mg)", "인(mg)", "칼륨(mg)", "나트륨(mg)",
            "비타민 A(μg RAE)", "레티놀(μg)", "베타카로틴(μg)", "티아민(mg)", "리보플라빈(mg)", "니아신(mg)", "비타민 C(mg)", "비타민 D(μg)",
            "콜레스테롤(mg)", "포화지방산(g)", "트랜스지방산(g)", "출처코드", "출처명", "1회 섭취참고량", "식품중량", "품목제조보고번호", "제조사명", "수입업체명",
            "유통업체명", "수입여부", "원산지국코드", "원산지국명", "데이터생성방법코드", "데이터생성방법명", "데이터생성일자", "데이터기준일자", "제공기관코드", "제공기관명" };

    public void saveFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/fooddata.csv"), "EUC-KR"))) {
            String[] line;
            List<RawFoodDto> rawFoodDtoList = new ArrayList<>();

            reader.readNext(); // 첫 번째 줄(헤더) 건너뛰기

            while ((line = reader.readNext()) != null) {
                RawFoodDto rawFoodDto = new RawFoodDto();
                rawFoodDto.setFoodNm(line[1]);
                rawFoodDto.setChocdf(parseDoubleOrDefault(line[21], 0.0));
                rawFoodDto.setEnerc(parseDoubleOrDefault(line[17], 0.0));
                rawFoodDto.setFatce(parseDoubleOrDefault(line[19], 0.0));
                rawFoodDto.setProt(parseDoubleOrDefault(line[18], 0.0));
                rawFoodDto.setMfrNm(line[46]);
                // ... 다른 필드 설정 ...
                rawFoodDtoList.add(rawFoodDto);
            }
            for (RawFoodDto rawFoodDto : rawFoodDtoList) {
                RepoRawFood.save(EntityConversionService.convertToEntity(rawFoodDto, RawFood.class));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
