package com.jk.model;

public class Provinces {

    private String  id;

    private String name;

    private String code = "x";

    private Boolean isMunicipality;

    private String municipalityId;

    @Override
    public String toString() {
        return "Provinces{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", isMunicipality=" + isMunicipality +
                ", municipalityId=" + municipalityId +
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

    public Boolean getMunicipality() {
        return isMunicipality;
    }

    public void setMunicipality(Boolean municipality) {
        isMunicipality = municipality;
    }

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Provinces() {
    }

    public Provinces(String id, String name, Boolean isMunicipality, String municipalityId) {
        this.id = id;
        this.name = name;
        this.isMunicipality = isMunicipality;
        this.municipalityId = municipalityId;
    }
}
