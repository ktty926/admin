package com.jk.model;

public class Districts {

    private String id;

    private String name;

    private String code = "x";

    private String cityId;

    public Districts() {
    }

    public Districts(String id, String name, String cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Districts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cityId=" + cityId +
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
