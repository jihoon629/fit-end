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
                rawFoodDto.setNutConSrtrQua(parseDouble(line[16]));
                rawFoodDto.setEnerc(parseDouble(line[17]));
                rawFoodDto.setWater(parseDouble(line[18]));
                rawFoodDto.setProt(parseDouble(line[19]));
                rawFoodDto.setFatce(parseDouble(line[20]));
                rawFoodDto.setAsh(parseDouble(line[21]));
                rawFoodDto.setChocdf(parseDouble(line[22]));
                rawFoodDto.setSugar(parseDouble(line[23]));
                rawFoodDto.setFibtg(parseDouble(line[24]));
                rawFoodDto.setCa(parseDouble(line[25]));
                rawFoodDto.setFe(parseDouble(line[26]));
                rawFoodDto.setP(parseDouble(line[27]));
                rawFoodDto.setK(parseDouble(line[28]));
                rawFoodDto.setNat(parseDouble(line[29]));
                rawFoodDto.setVitaRae(parseDouble(line[30]));
                rawFoodDto.setRetol(parseDouble(line[31]));
                rawFoodDto.setCartb(parseDouble(line[32]));
                rawFoodDto.setThia(parseDouble(line[33]));
                rawFoodDto.setRibf(parseDouble(line[34]));
                rawFoodDto.setNia(parseDouble(line[35]));
                rawFoodDto.setVitc(parseDouble(line[36]));
                rawFoodDto.setVitd(parseDouble(line[37]));
                rawFoodDto.setChole(parseDouble(line[38]));
                rawFoodDto.setFasat(parseDouble(line[39]));
                rawFoodDto.setFatrn(parseDouble(line[40]));
                rawFoodDto.setSrcCd(line[41]);
                rawFoodDto.setSrcNm(line[42]);
                rawFoodDto.setServSize(parseDouble(line[43]));
                rawFoodDto.setFoodSize(parseDouble(line[44]));
                rawFoodDto.setItemMnftrRptNo(line[45]);
                rawFoodDto.setMfrNm(line[46]);
                rawFoodDto.setImptNm(line[47]);
                rawFoodDto.setDistNm(line[48]);
                rawFoodDto.setImptYn(Boolean.parseBoolean(line[49]));
                rawFoodDto.setCooCd(line[50]);
                rawFoodDto.setCooNm(line[51]);
                rawFoodDto.setDataProdCd(line[52]);
                rawFoodDto.setDataProdNm(line[53]);
                rawFoodDto.setCrtYmd(line[54]);
                rawFoodDto.setCrtrYmd(line[55]);
                rawFoodDto.setInsttCode(line[56]);
                rawFoodDto.setInsttNm(line[57]);
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

    private double parseDouble(String value) {
        String numericValue = value.replaceAll("[^\\d.]", "");
        return numericValue.isEmpty() ? 0.0 : Double.parseDouble(numericValue);
    }
}
