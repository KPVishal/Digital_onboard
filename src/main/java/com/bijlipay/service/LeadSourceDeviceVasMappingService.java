package com.bijlipay.service;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.LeadSourceDeviceVasMapping;
import com.bijlipay.model.Vas;
import com.bijlipay.model.dto.LeadSourceDeviceVasMappingDto;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface LeadSourceDeviceVasMappingService {
    LeadSourceDeviceVasMappingDto findLeadSourceDeviceVasList(LeadSource leadSource, Device device);
    List<Vas> getAllVasListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);
    List<LeadSourceDeviceVasMapping> saveLeadSourceDeviceVasMappingList(LeadSourceDeviceVasMappingDto mappingDto);
    List<LeadSourceDeviceVasMapping> getAllByLeadSourceAndDevice(LeadSource leadSource, Device device);
    void deleteExists(List<LeadSourceDeviceVasMapping> leadSourceDeviceVasMappingList);
    List<LeadSourceDeviceVasMappingDto> getAllList();
}
