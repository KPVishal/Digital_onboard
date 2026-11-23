package com.bijlipay.model;

import java.util.List;

public class MdrValidateDto {

    private int leadSource;
    private List<Long> device;

    private String mcc;

    private int type;

    private long leadId;


    public int getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(int leadSource) {
        this.leadSource = leadSource;
    }

    public List<Long> getDevice() {
        return device;
    }

    public void setDevice(List<Long> device) {
        this.device = device;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }
}
