package com.example.demo.Repo;

import com.example.demo.DTO.RawFoodDto.MetaDataDto;
import com.example.demo.DTO.RawFoodDto.NutrientDto;
import com.example.demo.DTO.RawFoodDto.RawFoodDto;
import com.example.demo.Entity.RawFood.RawFood;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RawFoodSpecification {

    public static Specification<RawFood> getRawFoodSpecification(RawFoodDto rawFoodDto, NutrientDto nutrientDto,
            MetaDataDto metaDataDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (rawFoodDto.getFoodCd() != null && !rawFoodDto.getFoodCd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodCd"), rawFoodDto.getFoodCd()));
            }
            if (rawFoodDto.getFoodNm() != null && !rawFoodDto.getFoodNm().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("foodNm"), "%" + rawFoodDto.getFoodNm() + "%"));
            }
            if (rawFoodDto.getDataCd() != null && !rawFoodDto.getDataCd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("dataCd"), rawFoodDto.getDataCd()));
            }
            if (rawFoodDto.getTypeNm() != null && !rawFoodDto.getTypeNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("typeNm"), rawFoodDto.getTypeNm()));
            }
            if (rawFoodDto.getFoodOriginCd() != null && !rawFoodDto.getFoodOriginCd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodOriginCd"), rawFoodDto.getFoodOriginCd()));
            }
            if (rawFoodDto.getFoodOriginNm() != null && !rawFoodDto.getFoodOriginNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodOriginNm"), rawFoodDto.getFoodOriginNm()));
            }
            if (rawFoodDto.getFoodLv3Cd() != null && !rawFoodDto.getFoodLv3Cd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv3Cd"), rawFoodDto.getFoodLv3Cd()));
            }
            if (rawFoodDto.getFoodLv3Nm() != null && !rawFoodDto.getFoodLv3Nm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv3Nm"), rawFoodDto.getFoodLv3Nm()));
            }
            if (rawFoodDto.getFoodLv4Cd() != null && !rawFoodDto.getFoodLv4Cd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv4Cd"), rawFoodDto.getFoodLv4Cd()));
            }
            if (rawFoodDto.getFoodLv4Nm() != null && !rawFoodDto.getFoodLv4Nm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv4Nm"), rawFoodDto.getFoodLv4Nm()));
            }
            if (rawFoodDto.getFoodLv5Cd() != null && !rawFoodDto.getFoodLv5Cd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv5Cd"), rawFoodDto.getFoodLv5Cd()));
            }
            if (rawFoodDto.getFoodLv5Nm() != null && !rawFoodDto.getFoodLv5Nm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv5Nm"), rawFoodDto.getFoodLv5Nm()));
            }
            if (rawFoodDto.getFoodLv6Cd() != null && !rawFoodDto.getFoodLv6Cd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv6Cd"), rawFoodDto.getFoodLv6Cd()));
            }
            if (rawFoodDto.getFoodLv6Nm() != null && !rawFoodDto.getFoodLv6Nm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv6Nm"), rawFoodDto.getFoodLv6Nm()));
            }
            if (rawFoodDto.getFoodLv7Cd() != null && !rawFoodDto.getFoodLv7Cd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv7Cd"), rawFoodDto.getFoodLv7Cd()));
            }
            if (rawFoodDto.getFoodLv7Nm() != null && !rawFoodDto.getFoodLv7Nm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("foodLv7Nm"), rawFoodDto.getFoodLv7Nm()));
            }

            if (nutrientDto.getNutConSrtrQua() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("nutConSrtrQua"),
                        nutrientDto.getNutConSrtrQua()));
            }
            if (nutrientDto.getEnerc() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("enerc"), nutrientDto.getEnerc()));
            }
            if (nutrientDto.getWater() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("water"), nutrientDto.getWater()));
            }
            if (nutrientDto.getProt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("prot"), nutrientDto.getProt()));
            }
            if (nutrientDto.getFatce() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("fatce"), nutrientDto.getFatce()));
            }
            if (nutrientDto.getAsh() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("ash"), nutrientDto.getAsh()));
            }
            if (nutrientDto.getChocdf() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("chocdf"), nutrientDto.getChocdf()));
            }
            if (nutrientDto.getSugar() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("sugar"), nutrientDto.getSugar()));
            }
            if (nutrientDto.getFibtg() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("fibtg"), nutrientDto.getFibtg()));
            }
            if (nutrientDto.getCa() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("ca"), nutrientDto.getCa()));
            }
            if (nutrientDto.getFe() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("fe"), nutrientDto.getFe()));
            }
            if (nutrientDto.getP() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("p"), nutrientDto.getP()));
            }
            if (nutrientDto.getK() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("k"), nutrientDto.getK()));
            }
            if (nutrientDto.getNat() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("nat"), nutrientDto.getNat()));
            }
            if (nutrientDto.getVitaRae() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("vitaRae"), nutrientDto.getVitaRae()));
            }
            if (nutrientDto.getRetol() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("retol"), nutrientDto.getRetol()));
            }
            if (nutrientDto.getCartb() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("cartb"), nutrientDto.getCartb()));
            }
            if (nutrientDto.getThia() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("thia"), nutrientDto.getThia()));
            }
            if (nutrientDto.getRibf() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("ribf"), nutrientDto.getRibf()));
            }
            if (nutrientDto.getNia() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("nia"), nutrientDto.getNia()));
            }
            if (nutrientDto.getVitc() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("vitc"), nutrientDto.getVitc()));
            }
            if (nutrientDto.getVitd() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("vitd"), nutrientDto.getVitd()));
            }
            if (nutrientDto.getChole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("chole"), nutrientDto.getChole()));
            }
            if (nutrientDto.getFasat() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("fasat"), nutrientDto.getFasat()));
            }
            if (nutrientDto.getFatrn() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nutrient").get("fatrn"), nutrientDto.getFatrn()));
            }

            if (metaDataDto.getSrcCd() != null && !metaDataDto.getSrcCd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("srcCd"), metaDataDto.getSrcCd()));
            }
            if (metaDataDto.getSrcNm() != null && !metaDataDto.getSrcNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("srcNm"), metaDataDto.getSrcNm()));
            }
            if (metaDataDto.getServSize() != null) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("servSize"), metaDataDto.getServSize()));
            }
            if (metaDataDto.getFoodSize() != null) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("foodSize"), metaDataDto.getFoodSize()));
            }
            if (metaDataDto.getItemMnftrRptNo() != null && !metaDataDto.getItemMnftrRptNo().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("itemMnftrRptNo"),
                        metaDataDto.getItemMnftrRptNo()));
            }
            if (metaDataDto.getMfrNm() != null && !metaDataDto.getMfrNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("mfrNm"), metaDataDto.getMfrNm()));
            }
            if (metaDataDto.getImptNm() != null && !metaDataDto.getImptNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("imptNm"), metaDataDto.getImptNm()));
            }
            if (metaDataDto.getDistNm() != null && !metaDataDto.getDistNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("distNm"), metaDataDto.getDistNm()));
            }
            if (metaDataDto.getImptYn() != null) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("imptYn"), metaDataDto.getImptYn()));
            }
            if (metaDataDto.getCooCd() != null && !metaDataDto.getCooCd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("cooCd"), metaDataDto.getCooCd()));
            }
            if (metaDataDto.getCooNm() != null && !metaDataDto.getCooNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("cooNm"), metaDataDto.getCooNm()));
            }
            if (metaDataDto.getDataProdCd() != null && !metaDataDto.getDataProdCd().isEmpty()) {
                predicates.add(
                        criteriaBuilder.equal(root.get("metaData").get("dataProdCd"), metaDataDto.getDataProdCd()));
            }
            if (metaDataDto.getDataProdNm() != null && !metaDataDto.getDataProdNm().isEmpty()) {
                predicates.add(
                        criteriaBuilder.equal(root.get("metaData").get("dataProdNm"), metaDataDto.getDataProdNm()));
            }
            if (metaDataDto.getCrtYmd() != null && !metaDataDto.getCrtYmd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("crtYmd"), metaDataDto.getCrtYmd()));
            }
            if (metaDataDto.getCrtrYmd() != null && !metaDataDto.getCrtrYmd().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("crtrYmd"), metaDataDto.getCrtrYmd()));
            }
            if (metaDataDto.getInsttCode() != null && !metaDataDto.getInsttCode().isEmpty()) {
                predicates
                        .add(criteriaBuilder.equal(root.get("metaData").get("insttCode"), metaDataDto.getInsttCode()));
            }
            if (metaDataDto.getInsttNm() != null && !metaDataDto.getInsttNm().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("metaData").get("insttNm"), metaDataDto.getInsttNm()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}