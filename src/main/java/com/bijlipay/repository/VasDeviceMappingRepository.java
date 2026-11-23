package com.bijlipay.repository;

import com.bijlipay.model.Device;
import com.bijlipay.model.VasDeviceMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VasDeviceMappingRepository extends JpaRepository<VasDeviceMapping, Long> {

    @Query("SELECT v FROM VasDeviceMapping v")
    List<VasDeviceMapping> getAllVasDeviceMapping();

    @Query("SELECT v FROM VasDeviceMapping v WHERE v.device = ?1")
    List<VasDeviceMapping> getAllVasMappingByDevice(Device device);
}
