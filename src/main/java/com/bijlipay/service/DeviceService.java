package com.bijlipay.service;

import com.bijlipay.model.Device;

import java.util.List;

public interface DeviceService {

    Device isDeviceExists(Device device);

    Device createDevice(Device device);

    List<Device> listAllDevices();

    List<Device> listActiveDevices(boolean is_active);

    Device getDevice(long id);

    Device updateDevice(Long id, Device device);

    void deactivateDevice(long id);


}
