package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.example.demo.DTO.FoodDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodService {
    public List<FoodDto> getFoodDetails(String foodName) {
        // 공공 API 호출을 위한 URL 설정
        System.out.println("받은 음식이름: " + foodName);

        List<FoodDto> foodDetailsList = new ArrayList<>();

        try {
            String encodedFoodName = URLEncoder.encode(foodName, "UTF-8");
            String serviceKey = "7KAPRzfHxTBxel3urs%2BaQcFjVhSaJ%2FN6tXSNlU1ZrJgBEY1XS3lL0fm7sEKuC%2Bb6IchKVajWK0InBgxzVlgnVQ%3D%3D";

            StringBuilder urlBuilder = new StringBuilder(
                    "http://api.data.go.kr/openapi/tn_pubr_public_nutri_process_info_api");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("foodNm", "UTF-8") + "=" + encodedFoodName);

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // JSON 응답을 콘솔에 출력
            // System.out.println("API 응답: " + sb.toString());

            // JSON 응답을 전처리하여 필요한 데이터 추출
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(sb.toString());
            JsonNode itemsNode = rootNode.path("response").path("body").path("items");

            Set<String> seenItems = new HashSet<>();
            for (JsonNode itemNode : itemsNode) {
                String foodNm = itemNode.path("foodNm").asText();
                String mfrNm = itemNode.path("mfrNm").asText();
                String identifier = foodNm + "-" + mfrNm;

                if (!seenItems.contains(identifier)) {
                    seenItems.add(identifier);
                    double enerc = itemNode.path("enerc").asDouble();
                    double prot = itemNode.path("prot").asDouble();
                    double fatce = itemNode.path("fatce").asDouble();
                    double chocdf = itemNode.path("chocdf").asDouble();
                    double foodSize = itemNode.path("foodSize").asDouble();

                    // API에서 음식정보만 가져와 FoodDto 리스트에 반환후 리액트에서 dietMemo와 timestamp을 추가해서 백엔드로 전송함
                    FoodDto foodDto = new FoodDto(foodNm, mfrNm, enerc, prot, fatce, chocdf, foodSize, null, null,
                            null);
                    foodDetailsList.add(foodDto);
                }
            }

        } catch (UnsupportedEncodingException e) {
            System.out.println("URL 인코딩 오류: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("API 호출 오류: " + e.getMessage());
        }

        return foodDetailsList;
    }
}