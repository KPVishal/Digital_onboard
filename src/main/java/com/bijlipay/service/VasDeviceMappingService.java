package com.bijlipay.service;

import com.bijlipay.model.Device;
import com.bijlipay.model.VasDeviceMapping;

import java.util.List;
import java.util.Optional;

public interface VasDeviceMappingService {

    List<VasDeviceMapping> getVasMappingByDevice(Device deviceId);

    Optional<VasDeviceMapping> findById(long deviceId);

    VasDeviceMapping createVasDeviceMapping(VasDeviceMapping vasDeviceMapping);

    VasDeviceMapping updateVasDeviceMapping(VasDeviceMapping vasDeviceMapping);
}
