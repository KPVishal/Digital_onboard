package com.bijlipay.service.implement;

import com.bijlipay.model.Device;
import com.bijlipay.model.VasDeviceMapping;
import com.bijlipay.repository.VasDeviceMappingRepository;
import com.bijlipay.service.VasDeviceMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VasDeviceMappingServiceImp implements VasDeviceMappingService {

    @Autowired
    VasDeviceMappingRepository vasDeviceMappingRepository;

    @Override
    public List<VasDeviceMapping> getVasMappingByDevice(Device device) {

        return vasDeviceMappingRepository.getAllVasMappingByDevice(device);
    }

    @Override
    public Optional<VasDeviceMapping> findById(long id) {
        return vasDeviceMappingRepository.findById(id);
    }

    @Override
    public VasDeviceMapping createVasDeviceMapping(VasDeviceMapping vasDeviceMapping) {
        return vasDeviceMappingRepository.save(vasDeviceMapping);
    }

    @Override
    public VasDeviceMapping updateVasDeviceMapping(VasDeviceMapping vasDeviceMappingReq) {
        Optional<VasDeviceMapping> vasDeviceMappingDetail = vasDeviceMappingRepository.findById(vasDeviceMappingReq.getId());
        if(vasDeviceMappingDetail.isPresent()) {
            VasDeviceMapping vasDeviceMapping = vasDeviceMappingDetail.get();
            vasDeviceMapping.setVas(vasDeviceMappingReq.getVas());
            vasDeviceMapping.setDevice(vasDeviceMappingReq.getDevice());
            return vasDeviceMappingRepository.save(vasDeviceMapping);
        }
        return null;
    }

}
