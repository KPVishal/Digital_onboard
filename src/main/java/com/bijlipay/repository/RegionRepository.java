package com.bijlipay.repository;

import com.bijlipay.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

    Region findByRegionAreaName(String region);

    @Query(value = "Select * from region where region_id=?1",nativeQuery = true)
    Region findRegionById(Integer id);


    @Query("Select r from Region r where r.regionAreaName=?1")
    Region getRegionByName(String region);
}
