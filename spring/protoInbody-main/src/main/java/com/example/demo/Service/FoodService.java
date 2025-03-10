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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.FoodDto;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoDietRecord;
import com.example.demo.Repo.RepoUserInfo;
import com.example.demo.Service.Convert.EntityConversionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

@Service
public class FoodService {

    @Autowired
    RepoUserInfo RepoUserInfo;
    @Autowired
    RepoDietRecord RepoDietRecord;
    @Autowired
    EntityConversionService EntityConversionService;

    public boolean saveFood(FoodDto FoodDto) {
        UserInfo userInfo = RepoUserInfo.findByUserid(FoodDto.getUserid());
        if (userInfo == null) {
            return false;
        }

        System.out.println(EntityConversionService.convertToEntity(FoodDto,
                DietRecord.class));
        RepoDietRecord.save(EntityConversionService.convertToEntity(FoodDto,
                DietRecord.class));
        System.out.println("음식 기록이 성공적으로 저장되었습니다!");
        return true;
    }

    public List<FoodDto> getFoodDetails(String foodName) {
        System.out.println("받은 음식이름: " + foodName);

        List<FoodDto> foodDetailsList = new ArrayList<>();

        try {
            String jsonResponse = callApi(foodName);
            foodDetailsList = parseJsonResponse(jsonResponse);
        } catch (UnsupportedEncodingException e) {
            System.out.println("URL 인코딩 오류: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("API 호출 오류: " + e.getMessage());
        }

        return foodDetailsList;
    }

    private String callApi(String foodName) throws IOException {
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

        return sb.toString();
    }

    private List<FoodDto> parseJsonResponse(String jsonResponse) throws IOException {
        List<FoodDto> foodDetailsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
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

                FoodDto foodDto = new FoodDto(foodNm, mfrNm, enerc, prot, fatce, chocdf, foodSize, null, null, null);
                foodDetailsList.add(foodDto);
            }
        }
        return foodDetailsList;
    }
}
