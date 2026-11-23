package com.bijlipay.repository;

import com.bijlipay.model.RegionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface RegionGroupRepository extends JpaRepository<RegionGroup,Integer> {

    @Query(value = "Select * from region_group where region_group_id=?1",nativeQuery = true)
    RegionGroup findRegionById(int id);
}
