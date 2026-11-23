package com.bijlipay.service;

import com.bijlipay.model.RegionGroup;

import java.util.List;

public interface RegionGroupService {

    RegionGroup createRegionGroup(RegionGroup regionGroup);

    List <RegionGroup> getAll();

    RegionGroup getById(int id);

    RegionGroup delete(int id);
}
