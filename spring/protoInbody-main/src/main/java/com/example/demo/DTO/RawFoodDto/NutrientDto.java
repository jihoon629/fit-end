package com.example.demo.DTO.RawFoodDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "영양소 정보를 담고 있는 DTO")
public class NutrientDto {

    @Schema(description = "영양소 구성 기준량 (g)", example = "100.0")
    private Double nutConSrtrQua;

    @Schema(description = "에너지 (kcal)", example = "200.5")
    private Double enerc;

    @Schema(description = "수분 함량 (%)", example = "10.2")
    private Double water;

    @Schema(description = "단백질 함량 (g)", example = "5.3")
    private Double prot;

    @Schema(description = "지방 함량 (g)", example = "3.1")
    private Double fatce;

    @Schema(description = "회분 함량 (g)", example = "0.7")
    private Double ash;

    @Schema(description = "탄수화물 함량 (g)", example = "25.0")
    private Double chocdf;

    @Schema(description = "당류 함량 (g)", example = "15.5")
    private Double sugar;

    @Schema(description = "식이섬유 함량 (g)", example = "2.3")
    private Double fibtg;

    @Schema(description = "칼슘 함량 (mg)", example = "120.0")
    private Double ca;

    @Schema(description = "철 함량 (mg)", example = "2.1")
    private Double fe;

    @Schema(description = "인 함량 (mg)", example = "95.0")
    private Double p;

    @Schema(description = "칼륨 함량 (mg)", example = "320.0")
    private Double k;

    @Schema(description = "나트륨 함량 (mg)", example = "250.0")
    private Double nat;

    @Schema(description = "비타민 A (RAE, µg)", example = "450.0")
    private Double vitaRae;

    @Schema(description = "레티놀 함량 (µg)", example = "350.0")
    private Double retol;

    @Schema(description = "베타카로틴 함량 (µg)", example = "1200.0")
    private Double cartb;

    @Schema(description = "비타민 B1 (티아민, mg)", example = "0.6")
    private Double thia;

    @Schema(description = "비타민 B2 (리보플라빈, mg)", example = "0.8")
    private Double ribf;

    @Schema(description = "니아신 (비타민 B3, mg)", example = "9.2")
    private Double nia;

    @Schema(description = "비타민 C (mg)", example = "60.0")
    private Double vitc;

    @Schema(description = "비타민 D (µg)", example = "10.0")
    private Double vitd;

    @Schema(description = "콜레스테롤 함량 (mg)", example = "50.0")
    private Double chole;

    @Schema(description = "포화지방산 함량 (g)", example = "1.5")
    private Double fasat;

    @Schema(description = "트랜스지방산 함량 (g)", example = "0.1")
    private Double fatrn;

    public NutrientDto() {
    }

    public NutrientDto(Double nutConSrtrQua, Double enerc, Double water, Double prot, Double fatce, Double ash,
            Double chocdf, Double sugar, Double fibtg, Double ca, Double fe, Double p, Double k, Double nat,
            Double vitaRae, Double retol, Double cartb, Double thia, Double ribf, Double nia, Double vitc, Double vitd,
            Double chole, Double fasat, Double fatrn) {
        this.nutConSrtrQua = nutConSrtrQua;
        this.enerc = enerc;
        this.water = water;
        this.prot = prot;
        this.fatce = fatce;
        this.ash = ash;
        this.chocdf = chocdf;
        this.sugar = sugar;
        this.fibtg = fibtg;
        this.ca = ca;
        this.fe = fe;
        this.p = p;
        this.k = k;
        this.nat = nat;
        this.vitaRae = vitaRae;
        this.retol = retol;
        this.cartb = cartb;
        this.thia = thia;
        this.ribf = ribf;
        this.nia = nia;
        this.vitc = vitc;
        this.vitd = vitd;
        this.chole = chole;
        this.fasat = fasat;
        this.fatrn = fatrn;
    }

    public Double getNutConSrtrQua() {
        return nutConSrtrQua;
    }

    public void setNutConSrtrQua(Double nutConSrtrQua) {
        this.nutConSrtrQua = nutConSrtrQua;
    }

    public Double getEnerc() {
        return enerc;
    }

    public void setEnerc(Double enerc) {
        this.enerc = enerc;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getProt() {
        return prot;
    }

    public void setProt(Double prot) {
        this.prot = prot;
    }

    public Double getFatce() {
        return fatce;
    }

    public void setFatce(Double fatce) {
        this.fatce = fatce;
    }

    public Double getAsh() {
        return ash;
    }

    public void setAsh(Double ash) {
        this.ash = ash;
    }

    public Double getChocdf() {
        return chocdf;
    }

    public void setChocdf(Double chocdf) {
        this.chocdf = chocdf;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getFibtg() {
        return fibtg;
    }

    public void setFibtg(Double fibtg) {
        this.fibtg = fibtg;
    }

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getFe() {
        return fe;
    }

    public void setFe(Double fe) {
        this.fe = fe;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getNat() {
        return nat;
    }

    public void setNat(Double nat) {
        this.nat = nat;
    }

    public Double getVitaRae() {
        return vitaRae;
    }

    public void setVitaRae(Double vitaRae) {
        this.vitaRae = vitaRae;
    }

    public Double getRetol() {
        return retol;
    }

    public void setRetol(Double retol) {
        this.retol = retol;
    }

    public Double getCartb() {
        return cartb;
    }

    public void setCartb(Double cartb) {
        this.cartb = cartb;
    }

    public Double getThia() {
        return thia;
    }

    public void setThia(Double thia) {
        this.thia = thia;
    }

    public Double getRibf() {
        return ribf;
    }

    public void setRibf(Double ribf) {
        this.ribf = ribf;
    }

    public Double getNia() {
        return nia;
    }

    public void setNia(Double nia) {
        this.nia = nia;
    }

    public Double getVitc() {
        return vitc;
    }

    public void setVitc(Double vitc) {
        this.vitc = vitc;
    }

    public Double getVitd() {
        return vitd;
    }

    public void setVitd(Double vitd) {
        this.vitd = vitd;
    }

    public Double getChole() {
        return chole;
    }

    public void setChole(Double chole) {
        this.chole = chole;
    }

    public Double getFasat() {
        return fasat;
    }

    public void setFasat(Double fasat) {
        this.fasat = fasat;
    }

    public Double getFatrn() {
        return fatrn;
    }

    public void setFatrn(Double fatrn) {
        this.fatrn = fatrn;
    }

    @Override
    public String toString() {
        return "NutrientDto [nutConSrtrQua=" + nutConSrtrQua + ", enerc=" + enerc + ", water=" + water + ", prot="
                + prot + ", fatce=" + fatce + ", ash=" + ash + ", chocdf=" + chocdf + ", sugar=" + sugar + ", fibtg="
                + fibtg + ", ca=" + ca + ", fe=" + fe + ", p=" + p + ", k=" + k + ", nat=" + nat + ", vitaRae="
                + vitaRae + ", retol=" + retol + ", cartb=" + cartb + ", thia=" + thia + ", ribf=" + ribf + ", nia="
                + nia + ", vitc=" + vitc + ", vitd=" + vitd + ", chole=" + chole + ", fasat=" + fasat + ", fatrn="
                + fatrn + "]";
    }

}
