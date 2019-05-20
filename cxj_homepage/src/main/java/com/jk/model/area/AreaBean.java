package com.jk.model.area;

public class AreaBean {
    private  Integer id;
    private  String name;
    private  Integer pid;
    private  Integer chinaStart;
    private  Integer regionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getChinaStart() {
        return chinaStart;
    }

    public void setChinaStart(Integer chinaStart) {
        this.chinaStart = chinaStart;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
