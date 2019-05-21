package com.jk.model.invoice;

public class InvoicBeane {
    private String companyId;
    private String companyPhone;
    private String companyCircuitId;
    private  String  lineId;
    private  String  startName;
    private  String  endName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyCircuitId() {
        return companyCircuitId;
    }

    public void setCompanyCircuitId(String companyCircuitId) {
        this.companyCircuitId = companyCircuitId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }
}
