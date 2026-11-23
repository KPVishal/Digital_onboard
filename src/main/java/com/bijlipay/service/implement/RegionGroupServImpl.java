package com.bijlipay.service.implement;

import com.bijlipay.model.RegionGroup;
import com.bijlipay.repository.RegionGroupRepository;
import com.bijlipay.service.RegionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionGroupServImpl implements RegionGroupService {

    @Autowired
    private RegionGroupRepository regionGroupRepository;

    @Override
    public RegionGroup createRegionGroup(RegionGroup regionGroup) {
        regionGroup.setActive(true);
        return regionGroupRepository.save(regionGroup);
    }

    @Override
    public List<RegionGroup> getAll() {
        return regionGroupRepository.findAll();
    }

    @Override
    public RegionGroup getById(int id) {
        Optional<RegionGroup> regionGroup = regionGroupRepository.findById(id);
        if (regionGroup.isPresent()) {
            return regionGroup.get();
        }
        return null;
    }

    @Override
    public RegionGroup delete(int id) {
        RegionGroup regionGroup = regionGroupRepository.findRegionById(id);
        if (regionGroup != null) {
            regionGroup.setActive(false);
            return regionGroupRepository.save(regionGroup);
        }
        return null;

    }
}
