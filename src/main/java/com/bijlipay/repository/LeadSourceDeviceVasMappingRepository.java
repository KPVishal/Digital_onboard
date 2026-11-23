package com.bijlipay.repository;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.LeadSourceDeviceVasMapping;
import com.bijlipay.model.Vas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface LeadSourceDeviceVasMappingRepository extends JpaRepository<LeadSourceDeviceVasMapping, Long> {
    @Query("SELECT lsd FROM LeadSourceDeviceVasMapping lsd WHERE lsd.leadSource = ?1 AND lsd.device = ?2 ")
    List<LeadSourceDeviceVasMapping> getAllByLeadSourceAndDevice(LeadSource source, Device device1);
    @Query("SELECT lsd.vas FROM LeadSourceDeviceVasMapping lsd WHERE lsd.leadSource =?1 AND lsd.device = ?2")
    List<Vas> allVasListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);
    @Query("SELECT new map(lsd.leadSource as leadSource , lsd.device as device) FROM LeadSourceDeviceVasMapping lsd GROUP BY lsd.leadSource ,lsd.device ")
    List<HashMap<String, String>> groupByLeadSourceAndDevice();
}
