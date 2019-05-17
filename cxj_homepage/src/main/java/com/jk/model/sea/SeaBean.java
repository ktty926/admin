package com.jk.model.sea;

public class SeaBean {
    private   String  id;
    private   String  startHarbor; //起始港
    private   String  destinationHarbor;//目的港
    private   Integer  prescription;//时效
    private   Integer  price;//运输价格  0 面议
    private   String  shipId;//船公司
    private   String  guaranteeId;//保障
    private   String  hotline;//热线

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartHarbor() {
        return startHarbor;
    }

    public void setStartHarbor(String startHarbor) {
        this.startHarbor = startHarbor;
    }

    public String getDestinationHarbor() {
        return destinationHarbor;
    }

    public void setDestinationHarbor(String destinationHarbor) {
        this.destinationHarbor = destinationHarbor;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
}
