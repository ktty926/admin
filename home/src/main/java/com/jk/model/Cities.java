package com.jk.model;

public class Cities {

    private String id;

    private String name;

    private String code = "x";

    private Boolean isGeneral = false;

    private String provinceId;

    private String areaCode;

    public Cities(String id, String name, Boolean isGeneral, String provinceId, String areaCode) {
        this.id = id;
        this.name = name;
        this.isGeneral = isGeneral;
        this.provinceId = provinceId;
        this.areaCode = areaCode;
    }
    public Cities() {
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", isGeneral=" + isGeneral +
                ", provinceId=" + provinceId +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getGeneral() {
        return isGeneral;
    }

    public void setGeneral(Boolean general) {
        isGeneral = general;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
