package com.bijlipay.service;

import com.bijlipay.model.Region;

import java.util.List;

public interface RegionService {

    Region save(Region region);
    boolean isExistsRegion(String regionAreaName);

    List<Region> getList();

    Region getRegionById(Integer id);

    Region update(int id, Region region);

    Region delete(int id);
}
