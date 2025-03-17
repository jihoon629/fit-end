package com.example.demo.Service.Utile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RawFoodDto.MetaDataDto;
import com.example.demo.DTO.RawFoodDto.NutrientDto;
import com.example.demo.DTO.RawFoodDto.RawFoodDto;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.RawFood.MetaData;
import com.example.demo.Entity.RawFood.Nutrient;
import com.example.demo.Entity.RawFood.RawFood;
import com.example.demo.Repo.RepoMetaData;
import com.example.demo.Repo.RepoNutrient;
import com.example.demo.Repo.RepoRawFood;
import com.example.demo.Repo.RepoUserBodyInfo;
import com.example.demo.Repo.RepoUserInfo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;
import com.example.demo.Entity.UserBodyInfo;
import java.time.LocalDate;
import java.util.Date;

@Service
public class SaveRawFood {

    @Autowired
    private RepoRawFood RepoRawFood;
    @Autowired
    private ConversionService EntityConversionService;
    @Autowired
    private RepoMetaData RepoMetaData;
    @Autowired
    private RepoNutrient RepoNutrient;

    // 정적 참조를 위한 정적 인스턴스
    private static RepoUserBodyInfo repoUserBodyInfoStatic;
    private static RepoUserInfo repoUserInfoStatic;

    // 생성자를 통한 의존성 주입 및 정적 필드 초기화
    @Autowired
    public SaveRawFood(RepoUserBodyInfo repoUserBodyInfo, RepoUserInfo repoUserInfo) {
        SaveRawFood.repoUserBodyInfoStatic = repoUserBodyInfo;
        SaveRawFood.repoUserInfoStatic = repoUserInfo;
    }

    @Transactional
    public void saveFromCsv() {
        int totalCount = 0;
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/processeddata.csv")))) {

            reader.readNext(); // 첫 번째 줄(헤더) 건너뛰기
            String[] line;
            List<RawFoodDto> rawFoodDtoList = new ArrayList<>();

            // 배치 처리를 위한 변수 - 배치 크기 증가
            final int BATCH_SIZE = 1000;
            int batchCount = 0;
            long startTime = System.currentTimeMillis();

            while ((line = reader.readNext()) != null) {
                RawFoodDto rawFoodDto = createRawFoodDtoFromLine(line);
                rawFoodDtoList.add(rawFoodDto);
                totalCount++;

                // 배치 크기에 도달하면 저장 처리
                if (rawFoodDtoList.size() >= BATCH_SIZE) {
                    saveRawFoodBatch(rawFoodDtoList);
                    batchCount++;
                    rawFoodDtoList.clear();

                    // 진행 상황 및 성능 정보 출력
                    long currentTime = System.currentTimeMillis();
                    double elapsedSeconds = (currentTime - startTime) / 1000.0;
                    double recordsPerSecond = totalCount / elapsedSeconds;

                    System.out.printf("처리 진행: %d개 레코드 완료 (배치 %d개) - %.2f초 경과 (%.2f 레코드/초)%n",
                            totalCount, batchCount, elapsedSeconds, recordsPerSecond);
                }
            }

            // 남은 데이터 처리
            if (!rawFoodDtoList.isEmpty()) {
                saveRawFoodBatch(rawFoodDtoList);
                batchCount++;
            }

            long endTime = System.currentTimeMillis();
            double totalTimeSeconds = (endTime - startTime) / 1000.0;
            System.out.printf("총 %d개 레코드 처리 완료 (배치 %d개) - 총 소요시간: %.2f초%n",
                    totalCount, batchCount, totalTimeSeconds);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    // 배치 저장 처리 메소드 - JPA 배치 처리 최적화
    @Transactional
    private void saveRawFoodBatch(List<RawFoodDto> rawFoodDtoList) {
        List<Nutrient> nutrients = new ArrayList<>();
        List<MetaData> metaDataList = new ArrayList<>();
        List<RawFood> rawFoods = new ArrayList<>();

        // 엔티티 변환 단계
        for (RawFoodDto rawFoodDto : rawFoodDtoList) {
            Nutrient nutrient = EntityConversionService.convertToEntity(rawFoodDto.getNutrient(), Nutrient.class);
            MetaData metaData = EntityConversionService.convertToEntity(rawFoodDto.getMetaData(), MetaData.class);

            nutrients.add(nutrient);
            metaDataList.add(metaData);

            RawFood rawFood = EntityConversionService.convertToEntity(rawFoodDto, RawFood.class);
            rawFood.setNutrient(nutrient);
            rawFood.setMetaData(metaData);
            rawFoods.add(rawFood);
        }

        // 벌크 저장 - 각 리포지토리에 saveAll 메소드 사용
        RepoNutrient.saveAll(nutrients);
        RepoMetaData.saveAll(metaDataList);
        RepoRawFood.saveAll(rawFoods);

        // 영속성 컨텍스트 초기화로 메모리 관리
        EntityManager entityManager = getEntityManager();
        if (entityManager != null) {
            entityManager.flush();
            entityManager.clear();
        }
    }

    // EntityManager 주입
    @PersistenceContext
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 데이터베이스에 음식 데이터가 이미 존재하는지 확인합니다.
     * 
     * @return 데이터가 존재하면 true, 아니면 false
     */
    public boolean checkIfDataExists() {
        // 예: 첫 번째 레코드 확인 또는 카운트 확인
        long count = RepoRawFood.count();
        return count > 0;
    }

    private RawFoodDto createRawFoodDtoFromLine(String[] line) {
        RawFoodDto rawFoodDto = new RawFoodDto();
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

        return rawFoodDto;
    }

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            String numericValue = value.replaceAll("[^\\d.]", "");
            return numericValue.isEmpty() ? 0.0 : Double.parseDouble(numericValue);
        } catch (NumberFormatException e) {
            System.err.println("숫자 변환 오류: " + value);
            return 0.0;
        }
    }

    @Transactional
    public static void importFemaleScoreRankData(String csvFilePath) throws IOException, CsvValidationException {
        try (Reader reader = new FileReader(csvFilePath);
                CSVReader csvReader = new CSVReader(reader)) {

            // 헤더 건너뛰기
            String[] header = csvReader.readNext();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                // CSV 데이터 파싱
                int sex = Integer.parseInt(line[0]);
                int age = Integer.parseInt(line[1]);
                double height = Double.parseDouble(line[2]);
                double weight = Double.parseDouble(line[3]);
                double leanMass = Double.parseDouble(line[4]);
                double fatMass = Double.parseDouble(line[5]);
                double fatPercentage = Double.parseDouble(line[6]);
                double inbodyScore = Double.parseDouble(line[7]);
                String userId = line[8];

                // 사용자 정보 찾기 또는 생성
                UserInfo userInfo = repoUserInfoStatic.findByUserid(userId);
                if (userInfo == null) {
                    userInfo = new UserInfo();
                    userInfo.setUserid(userId);

                    // 필수 필드에 기본값 설정
                    userInfo.setPassword("defaultPassword"); // 기본 비밀번호
                    userInfo.setEmail(userId + "@example.com"); // 기본 이메일
                    userInfo.setSex(sex); // CSV에서 가져온 성별
                    userInfo.setRegion1("기본지역1"); // 기본 지역1
                    userInfo.setRegion2("기본지역2"); // 기본 지역2
                    userInfo.setBirth(LocalDate.now()); // 현재 날짜를 기본 생일로

                    userInfo = repoUserInfoStatic.save(userInfo);
                    System.out.println("새 사용자 생성: " + userInfo.getUserid());
                }

                // BMI 계산
                double bmiRaw = weight / ((height / 100) * (height / 100));
                double bmi = Math.round(bmiRaw * 100.0) / 100.0;
                // UserBodyInfo 객체 생성 및 저장
                UserBodyInfo bodyInfo = new UserBodyInfo();
                bodyInfo.setUserInfo(userInfo); // 중요: userInfo 설정
                bodyInfo.setHeight(height);
                bodyInfo.setWeight(weight);
                bodyInfo.setFatpercentage(fatPercentage);
                bodyInfo.setFatMass(fatMass);
                bodyInfo.setLeanmass(leanMass);
                bodyInfo.setBmi(bmi);
                bodyInfo.setInbodyScore(inbodyScore);
                bodyInfo.setDate(new Date()); // 현재 날짜 설정
                bodyInfo.setSex(sex);
                bodyInfo.setAge(age);

                repoUserBodyInfoStatic.save(bodyInfo);
                System.out.println("신체 정보 저장 완료: " + bodyInfo);
            }

            System.out.println("CSV 데이터 가져오기 완료!");
        }
    }

}