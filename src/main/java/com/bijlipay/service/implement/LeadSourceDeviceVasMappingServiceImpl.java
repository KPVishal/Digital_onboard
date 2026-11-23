package com.bijlipay.service.implement;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.LeadSourceDeviceVasMapping;
import com.bijlipay.model.Vas;
import com.bijlipay.model.dto.LeadSourceDeviceVasMappingDto;
import com.bijlipay.repository.DeviceRepository;
import com.bijlipay.repository.LeadSourceDeviceVasMappingRepository;
import com.bijlipay.repository.LeadSourceRepo;
import com.bijlipay.service.LeadSourceDeviceVasMappingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LeadSourceDeviceVasMappingServiceImpl implements LeadSourceDeviceVasMappingService {
    @Autowired
    private LeadSourceDeviceVasMappingRepository leadSourceDeviceVasMappingRepository;
    @Autowired
    private LeadSourceRepo leadSourceRepo;
    @Autowired
    private DeviceRepository deviceRepository;
    @Override
    public LeadSourceDeviceVasMappingDto findLeadSourceDeviceVasList(LeadSource leadSource, Device device) {

        LeadSourceDeviceVasMappingDto vasMappingDto=new LeadSourceDeviceVasMappingDto();

        LeadSource source = leadSourceRepo.getReferenceById(leadSource.getId());
        Device device1 = deviceRepository.getReferenceById(device.getId());

        LeadSourceDeviceVasMapping result = leadSourceDeviceVasMappingRepository.getAllByLeadSourceAndDevice(source,device1).get(0);
        vasMappingDto.setLeadSourceDeviceVasMapping(result);
        vasMappingDto.setVasList(this.getAllVasListBasedOnLeadSourceAndDevice(result.getLeadSource(),result.getDevice()));
        return vasMappingDto;
    }
    public List<Vas> getAllVasListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device){
        return leadSourceDeviceVasMappingRepository.allVasListBasedOnLeadSourceAndDevice(leadSource,device);
    }

    @Override
    public List<LeadSourceDeviceVasMapping> saveLeadSourceDeviceVasMappingList(LeadSourceDeviceVasMappingDto mappingDto) {
        List<Vas> vasList = mappingDto.getVasList();
        List<LeadSourceDeviceVasMapping> result =new ArrayList<>();
        vasList.forEach(vas -> {
            LeadSourceDeviceVasMapping leadSourceDeviceVasMapping=new LeadSourceDeviceVasMapping();
            leadSourceDeviceVasMapping.setLeadSource(mappingDto.getLeadSourceDeviceVasMapping().getLeadSource());
            leadSourceDeviceVasMapping.setDevice(mappingDto.getLeadSourceDeviceVasMapping().getDevice());
            leadSourceDeviceVasMapping.setVas(vas);
            result.add(leadSourceDeviceVasMapping);
        });
        return leadSourceDeviceVasMappingRepository.saveAll(result);
    }

    @Override
    public List<LeadSourceDeviceVasMapping> getAllByLeadSourceAndDevice(LeadSource leadSource, Device device) {
        return leadSourceDeviceVasMappingRepository.getAllByLeadSourceAndDevice(leadSource,device);
    }

    @Override
    public void deleteExists(List<LeadSourceDeviceVasMapping> leadSourceDeviceVasMappingList) {
        leadSourceDeviceVasMappingRepository.deleteAllInBatch(leadSourceDeviceVasMappingList);
    }

    @Override
    public List<LeadSourceDeviceVasMappingDto> getAllList() {
        List<LeadSourceDeviceVasMappingDto> result = new ArrayList<>();
        List<HashMap<String,String>> hashMapList = leadSourceDeviceVasMappingRepository.groupByLeadSourceAndDevice();
        ObjectMapper objectMapper = new ObjectMapper();
        hashMapList.forEach(leadSourceDeviceVas -> {
            LeadSourceDeviceVasMappingDto mappingDto = new LeadSourceDeviceVasMappingDto();
            LeadSource source=objectMapper.convertValue(leadSourceDeviceVas.get("leadSource"),LeadSource.class);
            Device device = objectMapper.convertValue(leadSourceDeviceVas.get("device"),Device.class);
            mappingDto.setLeadSourceDeviceVasMapping(this.getAllByLeadSourceAndDevice(source,device).get(0));
            mappingDto.setVasList(getAllVasListBasedOnLeadSourceAndDevice(source,device));
            result.add(mappingDto);
        });
        return result;
    }
}
