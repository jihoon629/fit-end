package com.example.demo.Service.Utile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RawFoodDto.MetaDataDto;
import com.example.demo.DTO.RawFoodDto.NutrientDto;
import com.example.demo.DTO.RawFoodDto.RawFoodDto;
import com.example.demo.Entity.RawFood.MetaData;
import com.example.demo.Entity.RawFood.Nutrient;
import com.example.demo.Entity.RawFood.RawFood;
import com.example.demo.Repo.RepoMetaData;
import com.example.demo.Repo.RepoNutrient;
import com.example.demo.Repo.RepoRawFood;
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
    @Autowired
    RepoMetaData RepoMetaData;
    @Autowired
    RepoNutrient RepoNutrient;

    public void saveFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/processeddata.csv")))) {
            String[] line;
            List<RawFoodDto> rawFoodDtoList = new ArrayList<>();

            reader.readNext(); // 첫 번째 줄(헤더) 건너뛰기

            while ((line = reader.readNext()) != null) {
                RawFoodDto rawFoodDto = new RawFoodDto();
                // ... existing code ...
                rawFoodDto.setFoodCd(line[0]);
                rawFoodDto.setFoodNm(line[1]);
                rawFoodDto.setDataCd(line[2]);
                rawFoodDto.setTypeNm(line[3]);
                rawFoodDto.setFoodOriginCd(line[4]);
                rawFoodDto.setFoodOriginNm(line[5]);
                rawFoodDto.setFoodLv3Cd(line[6]);
                rawFoodDto.setFoodLv3Nm(line[7]);
                rawFoodDto.setFoodLv4Cd(line[8]);
                rawFoodDto.setFoodLv4Nm(line[9]);
                rawFoodDto.setFoodLv5Cd(line[10]);
                rawFoodDto.setFoodLv5Nm(line[11]);
                rawFoodDto.setFoodLv6Cd(line[12]);
                rawFoodDto.setFoodLv6Nm(line[13]);
                rawFoodDto.setFoodLv7Cd(line[14]);
                rawFoodDto.setFoodLv7Nm(line[15]);

                // NutrientDto 생성 및 설정
                NutrientDto nutrientDto = new NutrientDto(
                        parseDouble(line[16]), parseDouble(line[17]), parseDouble(line[18]), parseDouble(line[19]),
                        parseDouble(line[20]), parseDouble(line[21]), parseDouble(line[22]), parseDouble(line[23]),
                        parseDouble(line[24]), parseDouble(line[25]), parseDouble(line[26]), parseDouble(line[27]),
                        parseDouble(line[28]), parseDouble(line[29]), parseDouble(line[30]), parseDouble(line[31]),
                        parseDouble(line[32]), parseDouble(line[33]), parseDouble(line[34]), parseDouble(line[35]),
                        parseDouble(line[36]), parseDouble(line[37]), parseDouble(line[38]), parseDouble(line[39]),
                        parseDouble(line[40]));

                MetaDataDto metaDataDto = new MetaDataDto(
                        line[41], line[42], parseDouble(line[43]), parseDouble(line[44]), line[45], line[46], line[47],
                        line[48], Boolean.parseBoolean(line[49]), line[50], line[51], line[52], line[53], line[54],
                        line[55], line[56], line[57]);

                rawFoodDto.setNutrient(nutrientDto);
                rawFoodDto.setMetaData(metaDataDto);

                rawFoodDtoList.add(rawFoodDto);
            }
            for (RawFoodDto rawFoodDto : rawFoodDtoList) {
                Nutrient nutrient = EntityConversionService.convertToEntity(rawFoodDto.getNutrient(), Nutrient.class);
                MetaData metaData = EntityConversionService.convertToEntity(rawFoodDto.getMetaData(), MetaData.class);

                // Save Nutrient and MetaData first
                RepoNutrient.save(nutrient);
                RepoMetaData.save(metaData);

                // Convert RawFoodDto to RawFood entity
                RawFood rawFood = EntityConversionService.convertToEntity(rawFoodDto, RawFood.class);
                rawFood.setNutrient(nutrient);
                rawFood.setMetaData(metaData);

                // Save RawFood entity
                RepoRawFood.save(rawFood);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private double parseDouble(String value) {
        String numericValue = value.replaceAll("[^\\d.]", "");
        return numericValue.isEmpty() ? 0.0 : Double.parseDouble(numericValue);
    }
}