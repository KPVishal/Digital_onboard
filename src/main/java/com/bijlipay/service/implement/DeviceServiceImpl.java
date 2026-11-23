package com.bijlipay.service.implement;

import com.bijlipay.model.Device;
import com.bijlipay.repository.DeviceRepository;
import com.bijlipay.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device isDeviceExists(Device device){
         return deviceRepository.fetchByDeviceName(device.getDeviceName());
    }

    @Override
    public Device createDevice(Device device) {
        device.setActive(true);
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> listAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> listActiveDevices(boolean is_active) {
        return deviceRepository.fetchActiveDevices(is_active);
    }

    @Override
    public Device getDevice(long id) {
        return deviceRepository.fetchDeviceById(id);
    }

    @Override
    public Device updateDevice(Long id, Device device) {
        Device deviceDetail = deviceRepository.getReferenceById(id);
        if (deviceDetail != null) {
            deviceDetail.setDeviceName(device.getDeviceName());
            deviceDetail.setColorCode(device.getColorCode());
            deviceDetail.setSerialNumberLength(device.getSerialNumberLength());
            deviceRepository.save(deviceDetail);
        }
        return deviceDetail;
    }

    @Override
    public void deactivateDevice(long id) {
        Device deviceDetail = deviceRepository.getReferenceById(id);
        if(deviceDetail != null){
            deviceDetail.setActive(false);
            deviceRepository.save(deviceDetail);
        }
    }

}
