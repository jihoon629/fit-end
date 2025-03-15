package com.example.demo.DTO.RawFoodDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "원재료 음식의 메타데이터 DTO. 데이터 소스, 제조사, 수입 여부, 원산지, 제품 정보 등 다양한 메타 정보를 포함합니다.")
public class MetaDataDto {
    @Schema(description = "출처 코드", example = "SRC001")
    private String srcCd;

    @Schema(description = "출처명", example = "식품안전정보원")
    private String srcNm;

    @Schema(description = "1회 제공량 (serving size)", example = "100.0")
    private Double servSize;

    @Schema(description = "음식 크기 (용량)", example = "200.0")
    private Double foodSize;

    @Schema(description = "품목 제조보고 번호", example = "RPT12345")
    private String itemMnftrRptNo;

    @Schema(description = "제조사 이름", example = "ABC식품")
    private String mfrNm;

    @Schema(description = "수입업체 이름", example = "XYZ수입")
    private String imptNm;

    @Schema(description = "유통 업체 이름", example = "유통업체")
    private String distNm;

    @Schema(description = "수입 여부", example = "true")
    private Boolean imptYn;

    @Schema(description = "원산지 코드", example = "COO001")
    private String cooCd;

    @Schema(description = "원산지 이름", example = "국내산")
    private String cooNm;

    @Schema(description = "데이터 제품 코드", example = "DP001")
    private String dataProdCd;

    @Schema(description = "데이터 생성 방법명", example = "식품데이터")
    private String dataProdNm;

    @Schema(description = "데이터 생성일", example = "2023-03-15")
    private String crtYmd;

    @Schema(description = "데이터 수정일", example = "2023-03-20")
    private String crtrYmd;

    @Schema(description = "기관 코드", example = "INST001")
    private String insttCode;

    @Schema(description = "기관 이름", example = "식품안전기관")
    private String insttNm;

    public MetaDataDto() {
    }

    public MetaDataDto(String srcCd, String srcNm, Double servSize, Double foodSize, String itemMnftrRptNo,
            String mfrNm,
            String imptNm, String distNm, Boolean imptYn, String cooCd, String cooNm, String dataProdCd,
            String dataProdNm, String crtYmd, String crtrYmd, String insttCode, String insttNm) {
        this.srcCd = srcCd;
        this.srcNm = srcNm;
        this.servSize = servSize;
        this.foodSize = foodSize;
        this.itemMnftrRptNo = itemMnftrRptNo;
        this.mfrNm = mfrNm;
        this.imptNm = imptNm;
        this.distNm = distNm;
        this.imptYn = imptYn;
        this.cooCd = cooCd;
        this.cooNm = cooNm;
        this.dataProdCd = dataProdCd;
        this.dataProdNm = dataProdNm;
        this.crtYmd = crtYmd;
        this.crtrYmd = crtrYmd;
        this.insttCode = insttCode;
        this.insttNm = insttNm;
    }

    public String getSrcCd() {
        return srcCd;
    }

    public void setSrcCd(String srcCd) {
        this.srcCd = srcCd;
    }

    public String getSrcNm() {
        return srcNm;
    }

    public void setSrcNm(String srcNm) {
        this.srcNm = srcNm;
    }

    public Double getServSize() {
        return servSize;
    }

    public void setServSize(Double servSize) {
        this.servSize = servSize;
    }

    public Double getFoodSize() {
        return foodSize;
    }

    public void setFoodSize(Double foodSize) {
        this.foodSize = foodSize;
    }

    public String getItemMnftrRptNo() {
        return itemMnftrRptNo;
    }

    public void setItemMnftrRptNo(String itemMnftrRptNo) {
        this.itemMnftrRptNo = itemMnftrRptNo;
    }

    public String getMfrNm() {
        return mfrNm;
    }

    public void setMfrNm(String mfrNm) {
        this.mfrNm = mfrNm;
    }

    public String getImptNm() {
        return imptNm;
    }

    public void setImptNm(String imptNm) {
        this.imptNm = imptNm;
    }

    public String getDistNm() {
        return distNm;
    }

    public void setDistNm(String distNm) {
        this.distNm = distNm;
    }

    public Boolean getImptYn() {
        return imptYn;
    }

    public void setImptYn(Boolean imptYn) {
        this.imptYn = imptYn;
    }

    public String getCooCd() {
        return cooCd;
    }

    public void setCooCd(String cooCd) {
        this.cooCd = cooCd;
    }

    public String getCooNm() {
        return cooNm;
    }

    public void setCooNm(String cooNm) {
        this.cooNm = cooNm;
    }

    public String getDataProdCd() {
        return dataProdCd;
    }

    public void setDataProdCd(String dataProdCd) {
        this.dataProdCd = dataProdCd;
    }

    public String getDataProdNm() {
        return dataProdNm;
    }

    public void setDataProdNm(String dataProdNm) {
        this.dataProdNm = dataProdNm;
    }

    public String getCrtYmd() {
        return crtYmd;
    }

    public void setCrtYmd(String crtYmd) {
        this.crtYmd = crtYmd;
    }

    public String getCrtrYmd() {
        return crtrYmd;
    }

    public void setCrtrYmd(String crtrYmd) {
        this.crtrYmd = crtrYmd;
    }

    public String getInsttCode() {
        return insttCode;
    }

    public void setInsttCode(String insttCode) {
        this.insttCode = insttCode;
    }

    public String getInsttNm() {
        return insttNm;
    }

    public void setInsttNm(String insttNm) {
        this.insttNm = insttNm;
    }

    @Override
    public String toString() {
        return "MetaDataDto [srcCd=" + srcCd + ", srcNm=" + srcNm + ", servSize=" + servSize + ", foodSize=" + foodSize
                + ", itemMnftrRptNo=" + itemMnftrRptNo + ", mfrNm=" + mfrNm + ", imptNm=" + imptNm + ", distNm="
                + distNm + ", imptYn=" + imptYn + ", cooCd=" + cooCd + ", cooNm=" + cooNm + ", dataProdCd=" + dataProdCd
                + ", dataProdNm=" + dataProdNm + ", crtYmd=" + crtYmd + ", crtrYmd=" + crtrYmd + ", insttCode="
                + insttCode + ", insttNm=" + insttNm + "]";
    }

}
