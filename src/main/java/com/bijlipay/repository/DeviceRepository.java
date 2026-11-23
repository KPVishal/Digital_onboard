package com.bijlipay.repository;

import com.bijlipay.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {

    @Query("select d from Device d where d.deviceName =?1")
    Device fetchByDeviceName(String deviceName);

    @Query("select d from Device d where d.isActive =?1")
    List<Device> fetchActiveDevices(boolean active_flag);

    @Query("select d from Device d where d.id=?1")
    Device fetchDeviceById(long id);

}
