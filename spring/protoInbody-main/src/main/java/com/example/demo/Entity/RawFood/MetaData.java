package com.example.demo.Entity.RawFood;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String srcCd;
    private String srcNm;
    private Double servSize;
    private Double foodSize;
    private String itemMnftrRptNo;
    private String mfrNm;
    private String imptNm;
    private String distNm;
    private Boolean imptYn;
    private String cooCd;
    private String cooNm;
    private String dataProdCd;
    private String dataProdNm;
    private String crtYmd;
    private String crtrYmd;
    private String insttCode;
    private String insttNm;

    public MetaData() {
    }

    public MetaData(Long id, String srcCd, String srcNm, Double servSize, Double foodSize, String itemMnftrRptNo,
            String mfrNm, String imptNm, String distNm, Boolean imptYn, String cooCd, String cooNm, String dataProdCd,
            String dataProdNm, String crtYmd, String crtrYmd, String insttCode, String insttNm) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
