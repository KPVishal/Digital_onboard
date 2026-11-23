package com.bijlipay.model.dto;

import com.bijlipay.model.LeadSourceDeviceVasMapping;
import com.bijlipay.model.Vas;

import java.util.List;

public class LeadSourceDeviceVasMappingDto {

    private LeadSourceDeviceVasMapping leadSourceDeviceVasMapping;
    private List<Vas> vasList;

    public LeadSourceDeviceVasMapping getLeadSourceDeviceVasMapping() {
        return leadSourceDeviceVasMapping;
    }

    public void setLeadSourceDeviceVasMapping(LeadSourceDeviceVasMapping leadSourceDeviceVasMapping) {
        this.leadSourceDeviceVasMapping = leadSourceDeviceVasMapping;
    }

    public List<Vas> getVasList() {
        return vasList;
    }

    public void setVasList(List<Vas> vasList) {
        this.vasList = vasList;
    }
}
