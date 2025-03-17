package com.example.demo.Service.Utile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.RepoRawFood;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RepoRawFood repoRawFood;

    @Autowired
    private SaveRawFood saveRawFood;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("데이터베이스 초기화 검사 시작...");

            // 테이블 존재 여부 확인
            boolean tableExists = checkIfTableExists();
            if (!tableExists) {
                log.info("테이블이 존재하지 않습니다. 테이블이 자동 생성될 때까지 대기합니다.");
                // 테이블이 생성될 때까지 잠시 대기
                Thread.sleep(3000);
            }

            // 데이터 존재 여부 확인
            boolean dataExists = checkIfDataExists();

            if (!dataExists) {
                log.info("데이터베이스에 음식 데이터가 없습니다. CSV 파일에서 데이터를 로드합니다...");

                // 데이터 로드 시작 시간
                long startTime = System.currentTimeMillis();

                // CSV에서 데이터 로드
                saveRawFood.saveFromCsv();

                // 데이터 로드 완료 시간 및 소요 시간 계산
                long endTime = System.currentTimeMillis();
                double totalTimeSeconds = (endTime - startTime) / 1000.0;

                log.info("데이터 로드 완료! 소요 시간: {} 초", totalTimeSeconds);
            } else {
                log.info("데이터베이스에 음식 데이터가 이미 존재합니다. 추가 로드를 건너뜁니다.");
            }

            // 데이터 개수 확인 및 로그 출력
            long count = repoRawFood.count();
            log.info("현재 데이터베이스에 {} 개의 음식 데이터가 있습니다.", count);

        } catch (Exception e) {
            log.error("데이터 초기화 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    private boolean checkIfTableExists() {
        try {
            // 테이블이 존재하는지 확인하기 위해 count() 메소드 호출 시도
            repoRawFood.count();
            return true;
        } catch (Exception e) {
            // 테이블이 없으면 예외 발생
            return false;
        }
    }

    private boolean checkIfDataExists() {
        try {
            long count = repoRawFood.count();
            return count > 0;
        } catch (Exception e) {
            // 테이블이 없거나 접근 불가능한 경우
            return false;
        }
    }
}